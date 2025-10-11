import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CreativeAccounting {
    
    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    static int intervalSum(int[] prefixSums, int start, int end) {
        if (start == 0) {
            return prefixSums[end];
        }
        return prefixSums[end] - prefixSums[start-1];
    }

    public static void main(String[] args) throws IOException {
        int[] daysLH = nextIntArray();
        int days = daysLH[0];
        int l = daysLH[1];
        int r = daysLH[2];
        int[] profits = new int[days];
        for (int i=0; i<days; i++) {
            profits[i] = nextInt();
        }

        int[] prefixSums = new int[days];
        prefixSums[0] = profits[0];
        for (int i=1; i<days; i++) {
            prefixSums[i] = prefixSums[i-1] + profits[i];
        }

        // Now try all possible intervals widths with all their possible starting points
        int maxProfitable = Integer.MIN_VALUE;
        int minProfitable = Integer.MAX_VALUE;
        for (int width=l; width<=r; width++) {
            for (int firstEnd=0; firstEnd<width; firstEnd++) {
                // Try this width/first-ending-day pair
                int start = 0;
                int end = firstEnd;
                int profitable = 0;
                while (start < days) {
                    int profit = intervalSum(prefixSums, start, end);
                    if (profit > 0) {
                        profitable++;
                    }
                    start = end+1;
                    end = start + width - 1;
                    end = Math.min(end, days-1);
                }
                maxProfitable = Math.max(maxProfitable, profitable);
                minProfitable = Math.min(minProfitable, profitable);
            }
        }

        System.out.print(minProfitable);
        System.out.print(" ");
        System.out.println(maxProfitable);
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(stdin.readLine());
    }

    static String nextLine() throws IOException {
        return stdin.readLine();
    }

    static int[] nextIntArray() throws IOException {
        // Read a line, split it on the space, and for each resulting element turn it into an integer, then turn that into an array
        // The JVM will give you an array of new strings that are actually not real - they're just slices of the original one
        // .stream is how you do SOMETHING to all things in an ArrayList
        return Arrays.stream(
                        stdin.readLine().split(" ")
                        ).mapToInt(
                            Integer::parseInt
                            ).toArray(); // This allows the JVM to pop off
    }

}
