import java.util.*;
import java.io.*;


public class J{
    public static void solve(Scanner in) {
        int n = in.nextInt();
        String s[] = new String[n];
        int a[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            s[i] = in.next();     
            for (int j = 0; j < n; j++) {
                char c = s[i].charAt(j);
                a[i][j] = c - '0';
            }
        }
        int ans[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                ans[i][j] = a[i][j];            
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (ans[i][j] > 0) {
                    for (int k = j + 1; k < n; k++) {
                        ans[i][k] = (ans[i][k] - a[j][k] + 10) % 10;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(ans[i][j]);                
            }
            System.out.println();
        }
    }
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        solve(in);
    }
}
