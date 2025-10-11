import java.io.*;
import java.util.*;

public class BackupTowers {

    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] rct = nextList();
        int r = rct[0];
        int c = rct[1];
        int t = rct[2];
        int[][] towers = new int[t][3];
        for (int i=0; i<t; i++) {
            int[] tower = nextList();
            towers[i][0] = i+1; // ID
            towers[i][1] = tower[0]-1; // Row
            towers[i][2] = tower[1]-1; // Column
        }
        Queue<int[]> bfsQueue = new LinkedList<>();
        for (int[] tower : towers) {
            int[] element = new int[]{tower[0],tower[1],tower[2]}; // ID, row, column
            bfsQueue.add(element);
        }
        int[][] firstTower = new int[r][c];
        int[][] secondTower = new int[r][c];

        // BFS algorithm
        while (!bfsQueue.isEmpty()) {
            int emptyThisMany = bfsQueue.size();
            for (int j=0; j<emptyThisMany; j++) {
                int[] next = bfsQueue.poll();
                int towerId = next[0];
                int cellRow = next[1];
                int cellCol = next[2];
                if (firstTower[cellRow][cellCol] == 0) {
                    firstTower[cellRow][cellCol] = towerId;
                } else if (firstTower[cellRow][cellCol] != towerId && secondTower[cellRow][cellCol] == 0) {
                    secondTower[cellRow][cellCol] = towerId;
                } else {
                    continue;
                }
                // Add top
                if (cellRow > 0) {
                    int[] element = new int[] { towerId, cellRow - 1, cellCol };
                    bfsQueue.add(element);
                }
                // Add bottom
                if (cellRow < r - 1) {
                    int[] element = new int[] { towerId, cellRow + 1, cellCol };
                    bfsQueue.add(element);
                }
                // Add left
                if (cellCol > 0) {
                    int[] element = new int[] { towerId, cellRow, cellCol - 1 };
                    bfsQueue.add(element);
                }
                // Add right
                if (cellCol < c - 1) {
                    int[] element = new int[] { towerId, cellRow, cellCol + 1 };
                    bfsQueue.add(element);
                }
            }
        }

        for (int i=0; i<r; i++) {
            for (int j=0; j<c; j++) {
                if (j < c-1) {
                    System.out.print(firstTower[i][j]);
                    System.out.print(" ");
                } else {
                    System.out.println(firstTower[i][j]);
                }
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (j < c - 1) {
                    System.out.print(secondTower[i][j]);
                    System.out.print(" ");
                } else {
                    System.out.println(secondTower[i][j]);
                }
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