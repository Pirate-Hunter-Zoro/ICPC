import java.io.*;
import java.util.*;

public class JavaTemplate {

    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        System.out.println(n);
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(stdin.readLine());
    }

    static String nextLine() throws IOException {
        return stdin.readLine();
    }

    static int[] nextList() throws IOException {
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