package com.company;

import java.util.*;
import java.io.*;

class IntList {
    private int pos = 0;
    private int[] mas = new int[1];
    IntList(int x) {
        add(x);
    }
    IntList(int a[]) {
        for (int i = 0; i < a.length; i++) {
            add(a[i]);
        }
    }
    public void add(int x) {
        if (mas.length == pos) {
            mas = Arrays.copyOf(mas, 2 * mas.length);
        }
        mas[pos] = x;
        pos++;
    }
    public int size() {
        return pos;
    }
    public int get(int x) {
        return mas[x];
    }
    public void reputLastElement(int x) {
        mas[pos - 1] = x;
    }
    public void set(int x, int val) {
        mas[x] = val;
    }
    public String toStr() {
        StringBuilder res = new StringBuilder();
        if (pos > 0) {
            res.append(Integer.toString(mas[0]));
        }
        for (int i = 1; i < pos; i++) {
            res.append(" ");
            res.append(Integer.toString(mas[i]));
        }
        return res.toString();
    }
    public int getLastElement() {
        return mas[pos - 1];
    }
}


public class E{
    public static List g;

    public static void bfs(IntList d, IntList p, int s, int n) {
        for (int i = 0; i < n; i++) {
            p.set(i, -1);
            d.set(i, 1_000_000_000);
        }
        d.set(s, 0);
        IntList q = new IntList(new int[n]);
        int l = 0, r = 1;
        q.set(l, s);
        while (l < r) {
            int x = q.get(l);
            l++;
            IntList cur = (IntList) g.get(x);
            for (int j = 0; j < cur.size(); j++) {
                int i = cur.get(j);
                if (d.get(i) > d.get(x) + 1) {
                    d.set(i, d.get(x) + 1);
                    q.set(r, i);
                    r++;
                    p.set(i, x);
                }
            }
        }
    }

    public static void solve(Scanner in) {
        int n = in.nextInt();
        int m = in.nextInt();
        g = new ArrayList<IntList>();
        IntList marked = new IntList(new int[0]);
        for (int i = 0; i < n; i++)
            g.add(new IntList(new int[0]));
        for (int i = 0; i < n - 1; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            a--;
            b--;
            //System.out.println(a + " " + b);
            IntList v = (IntList)g.get(a);
            v.add(b);
            v = (IntList)g.get(b);
            v.add(a);
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            x--;
            marked.add(x);
        }
        /*System.out.println();
        for (int i = 0; i < n; i++) {
            IntList v = (IntList)g.get(i);
            for (int j = 0; j < v.size(); j++) {
                int x = v.get(j);
                System.out.print(x + " ");
            }
            System.out.println();
        }
        System.out.println();*/
        IntList d1 = new IntList(new int[n]);
        IntList p1 = new IntList(new int[n]);
        bfs(d1, p1, marked.get(0), n);
        /*for (int i = 0; i < n; i++)
            System.out.println(d1.get(i));*/
        int v = -1, mx = -1;
        for (int i = 0; i < marked.size(); i++) {
            int j = marked.get(i);
            if (mx < d1.get(j)) {
                v = j;
                mx = d1.get(j);
            }
        }
        IntList way = new IntList(new int[0]);
        int cur = v;
        while(cur != marked.get(0)) {
            way.add(cur);
            cur = p1.get(cur);
        }
        way.add(cur);
        if (way.size() % 2 == 0) {
            System.out.println("NO");
        } else {
            bfs(d1, p1, way.get(way.size() / 2), n);
            int dist = d1.get(marked.get(0));
            for (int i = 0; i < marked.size(); i++) {
                int j = marked.get(i);
                if (d1.get(j) != dist) {
                    System.out.println("NO");
                    return;
                }
            }
            System.out.println("YES");
            System.out.println(way.get(way.size() / 2) + 1);
        }
    }
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        solve(in);
    }
}
