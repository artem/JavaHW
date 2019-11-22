import java.io.*;
import java.util.*;

public class I {    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int h[] = new int[n];
        int x[] = new int[n];
        int y[] = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
            h[i] = in.nextInt();
        }
        int xl = x[0] - h[0];
        int xr = x[0] + h[0];
        int yl = y[0] - h[0];
        int yr = y[0] + h[0];
        for (int i = 0; i < n; i++) {
            xl = Math.min(xl, x[i] - h[i]);
            xr = Math.max(xr, x[i] + h[i]);
            yl = Math.min(yl, y[i] - h[i]);
            yr = Math.max(yr, y[i] + h[i]);
        }
        int xx = (xl + xr) / 2;
        int yy = (yl + yr) / 2;
        int hh = (Math.max(xr - xl, yr - yl) + 1) / 2;
        System.out.print(xx);
        System.out.print(" ");
        System.out.print(yy);
        System.out.print(" ");
        System.out.print(hh);
    }
}
