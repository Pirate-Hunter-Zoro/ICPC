import java.io.*;
import java.util.*;

public class Alchemy {

    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    static final int MATCH = 0;
    static final int MISMATCH = 1;

    public static void main(String[] args) throws IOException {
        String string = nextLine();
        int[][] sols;
        if (string.length() % 2 == 0) {
            sols = new int[2][string.length()/2];
        } else {
            sols = new int[2][(string.length()-1)/2 + 1];
        }
        Arrays.fill(sols[MATCH], -1);
        Arrays.fill(sols[MISMATCH], -1);
        if (string.length() % 2 == 1) {
            // Odd number of characters total, so middle is single character and already a palindrome
            sols[MATCH][sols[MATCH].length-1] = 0;
            sols[MISMATCH][sols[MISMATCH].length-1] = 0;
        } else {
            // Even number of characters total, so middle is two characters
            sols[MATCH][sols[MATCH].length-1] = 0;
            // If the two middle characters start off mismatching, we will need to change
            // them to match, but then we are done
            sols[MISMATCH][sols[MISMATCH].length-1] = 1; 
        }
        // Fill our array with the values we need
        topDownCountPalindrome(string, 0, string.length()-1, sols);
        if (string.charAt(string.length()-1) == string.charAt(0)) {
            System.out.println(sols[MATCH][0]);
        } else {
            System.out.println(sols[MISMATCH][0]);
        }
    }

    static void topDownCountPalindrome(String string, int left, int right, int[][] sols) {
        if (sols[MATCH][left] == -1) {
            // Need to solve this problem - start with the subproblems
            topDownCountPalindrome(string, left+1, right-1, sols);

            // Suppose we start off with a mismatch between left and right
            // We change l to match r, but then left+1 may not match right-1
            if (string.charAt(left+1)==string.charAt(right-1)) {
                // Then they don't match anymore
                sols[MISMATCH][left] = 1 + sols[MISMATCH][left+1];
                // BUT we can make them match if we do two switches
                sols[MISMATCH][left] = Math.min(sols[MISMATCH][left], 2 + sols[MATCH][left+1]);
            } else {
                // We could make left + 1 match right - 1, or make them still not match
                sols[MISMATCH][left] = 1 + Math.min(sols[MISMATCH][left+1], sols[MATCH][left+1]);
            }

            // Suppose we start off with a match between left and right
            // We could leave it and go straight to the subproblem - that's one option
            if (string.charAt(left+1) == string.charAt(right-1)) {
                sols[MATCH][left] = sols[MATCH][left+1];
            } else {
                sols[MATCH][left] = sols[MISMATCH][left+1];
            }
            // OR we could change left paired with left+1 twice, allowing us to either match or mismatch between left+1 and right-1
            sols[MATCH][left] = Math.min(sols[MATCH][left], 2 + Math.min(sols[MATCH][left+1],sols[MISMATCH][left+1]));
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