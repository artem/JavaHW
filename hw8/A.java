import java.io.*;
import java.util.*;

public class A {
    public static int flor (int a, int b) {
        return (a + b - 1) / b;
    }
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt(), b = in.nextInt(), n = in.nextInt();
        int ans = 2 * flor(n - b, b - a) + 1;
        System.out.println(ans);
    }
}
