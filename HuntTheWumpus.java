import java.io.*;
import java.util.*;

public class HuntTheWumpus {

    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int s = nextInt();

        boolean[][] grid = new boolean[10][10];
        int[] wumpuses = new int[4];
        int wumpusesLeft = 0;

        for (s += s/13 + 15; wumpusesLeft < 4; s += s/13+15) {
            int x = (s % 100) / 10;
            int y = s % 10;

            if (grid[x][y]) continue;
            wumpuses[wumpusesLeft++] = s % 100; // post-use incrementing
            grid[x][y] = true;
        }

        int m = 0;
        int guessRow;
        int guessCol;
        while (wumpusesLeft > 0) {
            int guess = nextInt();
            m++;
            guessRow = guess / 10;
            guessCol = guess % 10;

            if (grid[guessRow][guessCol]) {
                System.out.println("You hit a wumpus!");
                grid[guessRow][guessCol] = false;
                wumpusesLeft--;
            }

            if (wumpusesLeft == 0) break;

            int minDist = Integer.MAX_VALUE;
            int wumpusRow;
            int wumpusCol;
            for (int i=0; i<wumpuses.length; i++) {
                wumpusRow = wumpuses[i] / 10;
                wumpusCol = wumpuses[i] % 10;
                if (!grid[wumpusRow][wumpusCol]) continue; // Already hit wumpus

                int dist = Math.abs(wumpusRow - guessRow);
                dist += Math.abs(wumpusCol - guessCol);
                minDist = Math.min(dist, minDist);
            }

            System.out.println(minDist);
            System.out.println(m);
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