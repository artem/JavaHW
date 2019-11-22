import java.util.Scanner;
import java.util.Arrays;
import java.io.*;

public class ReverseSort {
    public static void solve() {
        try (MyScanner in = new MyScanner(System.in);) {
            int height = 0;
            long table[][] = new long[1][];                        
            int tableHeight = 0;        
            while(in.hasNextLine()) {                
                String currentLine = in.nextLine(); 
                try (MyScanner currentLineScanner = new MyScanner(currentLine);) {        
                    currentLineScanner.setMode(1);
                    if (tableHeight == table.length) {                
                        table = Arrays.copyOf(table, 2 * tableHeight);
                    }
                    long currentLineArray[] = new long[3];
                    currentLineArray[0] = 0;
                    currentLineArray[1] = tableHeight;
                    int currentLineArrayEnd = 2;
                    while(currentLineScanner.hasNextInt()) {
                        if (currentLineArrayEnd == currentLineArray.length) {                
                            currentLineArray = Arrays.copyOf(currentLineArray, 2 * currentLineArrayEnd);
                        }
                        int currentInt = currentLineScanner.nextInt();
                        currentLineArray[0] += currentInt;
                        currentLineArray[currentLineArrayEnd++] = currentInt;                       
                    }                        
                    table[tableHeight++] = Arrays.copyOf(currentLineArray, currentLineArrayEnd);
                    height++;                      
                    currentLineScanner.close();
                } catch (IOException e) {
                    System.out.println("IO error");
                }
            }
            if (table.length >= 2)
                Arrays.sort(table, 0, height, (a, b) -> (a[0] == b[0] ? Long.compare(a[1], b[1]) : Long.compare(a[0], b[0])));
            for (int i = height - 1; i >= 0; i--) {
                Arrays.sort(table[i], 2, table[i].length);
                for (int j = table[i].length - 1; j >= 2; j--) {
                    System.out.print(table[i][j] + " ");
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
