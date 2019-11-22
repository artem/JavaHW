import java.util.*;
import java.io.*;


public class B{
    public static void solve(Scanner in) {
        int n = in.nextInt();
        int a[] = new int[n];
        int x = -710 * 25000;
        a[0] = x;
        for (int i = 1; i < n; i++) {
            x += 710;
            a[i] = x;
        }
        for (int i = 0; i < n; i++) {
            System.out.println(a[i]);
        }
    }
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        solve(in);
    }
}
