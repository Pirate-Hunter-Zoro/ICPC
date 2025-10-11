import java.io.*;
import java.util.*;

public class CallForProblems {

    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] nk = nextList();
        int n = nk[0];
        int k = nk[1];
        Set<Integer> difficulties = new HashSet<>();
        for (int i=0; i<n; i++) {
            difficulties.add(nextInt());
        }
        System.out.println(Math.min(k, difficulties.size()));
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