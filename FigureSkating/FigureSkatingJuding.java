import java.io.*;
import java.util.*;

public class FigureSkatingJuding {

    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] nk = nextList();
        int n = nk[0];
        int k = nk[1];
        int[] values = new int[n];
        for (int i=0; i<n; i++) {
            values[i] = nextInt();
        }
        Arrays.sort(values);
        // Find the consecutive slice that has the lowest "badness"
        int left = 0;
        int right = k-1;
        double record = Integer.MAX_VALUE;
        double sum = 0;
        double sumSquared = 0;
        for (int i=0; i<k; i++) {
            sum += (double)values[i];
            sumSquared += (double)(values[i]*values[i]);
        }
        // Check for 0 badness - before calculating badness check is sum and sumSquared is 0 - also if sum*sum is exactly k times sumSquared
        // Everything is a long except for badness which is a double
        while (right < n) {
            // Slick math trick
            double badness = -(sum*sum)/((double)k) + sumSquared;
            if (badness < record) {
                record = badness;
            }
            sum -= (double)values[left];
            sumSquared -= (double)(values[left]*values[left]);
            left++;
            right++;
            if (right < n) {
                sum += (double)values[right];
                sumSquared += (double)(values[right]*values[right]);
            }
        }

        System.out.println(record);
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(stdin.readLine());
    }

    static String nextLine() throws IOException {
        return stdin.readLine();
    }

    static int[] nextList() throws IOException {
        // Read a line, split it on the space, and for each resulting element turn it
        // into an integer, then turn that into an array
        // The JVM will give you an array of new strings that are actually not real -
        // they're just slices of the original one
        // .stream is how you do SOMETHING to all things in an ArrayList
        return Arrays.stream(
                stdin.readLine().split(" ")).mapToInt(
                        Integer::parseInt)
                .toArray(); // This allows the JVM to pop off
    }

}