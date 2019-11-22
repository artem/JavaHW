import java.util.*;
import java.io.*;


public class D{
    public static void solve(Scanner in) {
        int n = in.nextInt(), k = in.nextInt();
        if (n == 1) {
            System.out.println(k);
        } else {
            int p[] = new int[n + 1];
            int M = 998244353;
            p[0] = 1;
            p[1] = k;
            for (int i = 2; i < p.size(); i++) {
                p[i] = (k * p[i - 2]) % M;
            }
            int pp[] = new int[n + 1];
            pp[0] = 1;
            for (int i = 1; i < pref.size(); i++) {
                pp
            }
        }
    }
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        solve(in);
    }
}
