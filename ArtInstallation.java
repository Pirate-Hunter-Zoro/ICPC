import java.io.*;
import java.util.*;

public class ArtInstallation {

    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] rgbNeed = nextList();
        int[] rgbHave = nextList();
        int[] available = nextList();

        int[] stillNeed = new int[3];
        for (int i=0; i<3; i++) {
            stillNeed[i] = Math.max(0, rgbNeed[i]-rgbHave[i]);
        }

        int rgCount = available[0];
        int gbCount = available[1];
        if (stillNeed[0] > rgCount) {
            // Red
            System.out.println(-1);
        } else if (stillNeed[2] > gbCount) {
            // Blue
            System.out.println(-1);
        } else {
            rgCount -= stillNeed[0];
            gbCount -= stillNeed[2];
            if (stillNeed[1] > rgCount + gbCount) {
                System.out.println(-1);
            } else {
                System.out.println(Arrays.stream(stillNeed).sum());
            }
        }
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