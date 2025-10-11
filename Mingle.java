import java.io.*;
import java.util.*;

public class Mingle {

    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    static final long MOD = 998244353l;

    static long modAdd(long a, long b) {
        return ((a % MOD) + (b % MOD)) % MOD;
    }

    static long modSub(long a, long b) {
        return ((a % MOD) + MOD - (b % MOD)) % MOD;
    }

    static long modMul(long a, long b) {
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    static long modPow(long a, long p) {
        if (p == 0) {
            return 1;
        } else if (p == 1) {
            return a % MOD;
        } else {
            long first = modPow(a, p/2);
            if (p % 2 == 1) {
                return modMul(first, modMul(first, a));
            } else {
                return modMul(first, first);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int[] nk = nextList();
        long n = nk[0];
        long k = nk[1];
        long a = modMul(n, modPow(2*k, 2*k));
        long b = modPow(2*k+1, 2*k);
        long b_inv = modPow(b, MOD-2);
        long res = modMul(a, b_inv);
        System.out.println(res);
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