import java.util.*;
import java.io.*;


public class H{
    public static void solve(Scanner in) {
        int N = 5000_000;
        int p[] = new int[N];
        int ans[] = new int[N];
        int n = in.nextInt();
        int mx = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();            
            sum += a;
            mx = Math.max(mx, a);
            p[sum] = sum;
        }
        int q = in.nextInt();
        int c = 0;
        for (int i = 0; i < N; i++) {
            if (c <= p[i]) {
                c = p[i];
            } else {
                p[i] = c;
            }             
        }
        for (int i = mx; i < N; i++) {
            int cc = 0;
            int r = 0;            
            while (cc < sum) {
                cc = p[cc + i];
                r++;
            }
            ans[i] = r;
        }
        for (int i = 0; i < q; i++) {
            int x = in.nextInt();
            if (x >= mx) {
                System.out.println(ans[x]);
            } else {
                System.out.println("Impossible");
            }
        }
    }
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        solve(in);
    }
}
