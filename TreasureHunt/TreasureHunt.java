import java.io.*;
import java.util.*;

public class TreasureHunt {

    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // Start with guessing the following cells until one is right
        int[] rc = findThreeByThree();

        // Once we have our 3x3 portion with a center
        rc = pinPointTreasure(rc[0], rc[1]);
        System.out.println("! " + rc[0] + " " + rc[1]);

        System.exit(0);
    }

    static int[] findThreeByThree() throws IOException {
        // (2,2), (2,4), (4,2), (4,4) until one is right or three are wrong
        int[][] cellsToQuery = new int[4][2];
        cellsToQuery[0] = new int[] { 2, 2 };
        cellsToQuery[1] = new int[] { 2, 4 };
        cellsToQuery[2] = new int[] { 4, 2 };
        cellsToQuery[3] = new int[] { 4, 4 };
        for (int[] query : cellsToQuery) {
            if (query[0] == 4 && query[1] == 4) {
                // Process of elimination - no need to query here
                return query;
            }
            System.out.println("? " + query[0] + " " + query[1]);
            ;
            int response = nextInt();
            if (response == 1) {
                return query;
            }
        }
        return new int[]{-1,-1};
    }

    static int[] pinPointTreasure(int row, int col) throws IOException {
        // Ask center left
        // If yes, ask top left
        // If no, ask top middle
        System.out.println("? " + row + " " + (col - 1));
        ;
        int response = nextInt();
        if (response == 1) {
            // Top left
            System.out.println("? " + (row-1) + " " + (col-1));
            ;
            response = nextInt();
            if (response == 1) {
                return new int[]{row-1,col-1};
            } else {
                return new int[]{row+1,col-1};
            }
        } else {
            // Top middle
            System.out.println("? " + (row-1) + " " + col);
            ;
            response = nextInt();
            if (response == 1) {
                return new int[]{row-1, col};
            } else {
                return new int[]{row,col};
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