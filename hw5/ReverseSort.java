import java.util.*;
import java.io.*;

public class ReverseSort {
    public static void solve() {
        try (MyScanner in = new MyScanner(System.in);) {            
            int table[][] = new int[1][];                        
            int tableHeight = 0;        
            while(in.hasNextLine()) {                
                String currentLine = in.nextLine(); 
                try (MyScanner currentLineScanner = new MyScanner(currentLine);) {                            
                    if (tableHeight == table.length) {                
                        table = Arrays.copyOf(table, 2 * tableHeight);
                    }
                    int currentLineArray[] = new int[1];                    
                    int currentLineArrayEnd = 0;
                    while(currentLineScanner.hasNextInt()) {
                        if (currentLineArrayEnd == currentLineArray.length) {                
                            currentLineArray = Arrays.copyOf(currentLineArray, 2 * currentLineArrayEnd);
                        }
                        int currentInt = currentLineScanner.nextInt();                    
                        currentLineArray[currentLineArrayEnd++] = currentInt;                       
                    }                        
                    table[tableHeight++] = Arrays.copyOf(currentLineArray, currentLineArrayEnd);                                                             
                } catch (IOException e) {
                    System.out.println("IO error");
                }
            }                        
            List<Line> mas = new ArrayList();
            for (int i = 0; i < tableHeight; i++) {
                long curSum = 0;
                for (int j = 0; j < table[i].length; j++) {
                    curSum += table[i][j];
                }   
                mas.add(new Line(curSum, i));
            }
            Collections.sort(mas);
            for (int i = tableHeight - 1; i >= 0; i--) {
                Arrays.sort(table[mas.get(i).num], 0, table[mas.get(i).num].length);
                for (int j = table[mas.get(i).num].length - 1; j >= 0; j--) {
                    System.out.print(table[mas.get(i).num][j] + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }
    public static void main(String[] args) {
        solve();        
    }   
}

class Line implements Comparable<Line> {
    public long sum;
    public int num;
    public Line () {
        sum = 0;
        num = 0;
    }
    public Line(long s, int n) {
        sum = s;
        num = n;
    }
    public int compareTo(Line a) {
        return (a.sum == sum ? Integer.compare(num,  a.num) : Long.compare(sum, a.sum));
    }
}
