import java.io.*;
import java.util.*;

public class Alchemy {

    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    static final int MATCH = 0;
    static final int MISMATCH = 1;

    public static void main(String[] args) throws IOException {
        String string = nextLine();
        int[][] sols = new int[2][Math.round((string.length()-1)/2) + 1]; // Half the number of characters in the string, rounded up
        Arrays.fill(sols, -1);
        if (string.length() % 2 == 1) {
            // Odd number of characters total, so middle is single character and already a palindrome
            sols[MATCH][sols.length-1] = 0;
            sols[MISMATCH][sols.length-1] = 1;
        } else {
            // Even number of characters total, so middle is two characters
            
        }
        System.out.println(topDownCountPalindrome(string, 0, string.length()-1, sols));
    }

    static int topDownCountPalindrome(String string, int left, int right, int[][] sols) {
        return 0;
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