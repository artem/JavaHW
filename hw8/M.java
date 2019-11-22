import java.util.*;
import java.io.*;

public class M {
    public static void solve(Scanner in) { // ak = 2aj - ai , i < j < k     ///    2aj - ak = ai   ///   ak = 2aj - ai
        int n = in.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = in.nextInt();
        int ans = 0;
        Map kek = new HashMap<Integer, Integer>();
        kek.put(a[0], 1);
        for (int j = 1; j < n - 1; j++) {
            for (int k = j + 1; k < n; k++) {
                if (kek.get(2 * a[j] - a[k]) != null) {
                    ans += (Integer)kek.get(2 * a[j] - a[k]);
                }
            }
            if (kek.get((Integer)a[j]) != null) {
                Integer value = (Integer) kek.get(a[j]);
                kek.put(a[j], value + 1);
            } else {
                kek.put((Integer)a[j], 1);
            }
        }
        System.out.println(ans);
    }
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++)
            solve(in);
    }
}
