import java.io.*;
import java.util.*;

public class TripleJump {

    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int[] jumps = nextList();
        int lowestJump = jumps[0] / 3;
        int highestJump = jumps[jumps.length-1] / 3;
        Set<Integer> candidates = new HashSet<>();
        for (int i=1; i<jumps.length-1; i++) {
            if (jumps[i] % 3 == 0) {
                candidates.add(jumps[i] / 3);
            }
        }
        // Test each candidate
        for (int candidate : candidates) {
            int[][] sols = new int[1001][4];
            for (int i=0; i<sols.length; i++) {
                for (int j=0; j<sols[i].length; j++) {
                    sols[i][j] = -1;
                }
            }
            int[] theseJumps = new int[]{lowestJump, candidate, highestJump};
            boolean allWork = true;
            for (int i=1; i<jumps.length-1; i++) {
                allWork = allWork && canTotal(jumps[i], 3, sols, theseJumps);
                if (!allWork) {
                    break;
                }
            }
            if (allWork) {
                System.out.print(lowestJump + " ");
                System.out.print(candidate + " ");
                System.out.println(highestJump);
                return;
            }
        }
    }

    static boolean canTotal(int targetTotal, int numJumps, int[][] sols, int[] jumps) {
        if (sols[targetTotal][numJumps] != -1) {
            return sols[targetTotal][numJumps] == 1;
        } else {
            // Have to solve this problem
            if (numJumps == 1) {
                if (jumps[0] == targetTotal || jumps[1] == targetTotal || jumps[2] == targetTotal) {
                    sols[targetTotal][numJumps] = 1;
                } else {
                    sols[targetTotal][numJumps] = -1;
                }
            } else {
                sols[targetTotal][numJumps] = 0;
                for (int jump : jumps) {
                    if (jump < targetTotal) {
                        if (canTotal(targetTotal-jump, numJumps-1, sols, jumps)) {
                            sols[targetTotal][numJumps] = 1;
                            break;
                        }
                    }
                }
            }
            return sols[targetTotal][numJumps] == 1;
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