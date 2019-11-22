import java.util.Scanner;
import java.util.Arrays;
import java.io.*;

public class Reverse {
    public static void solve() {
        try (MyScanner in = new MyScanner(System.in);) {
            int height = 0;
            int table[][] = new int[1][];                        
            int tableHeight = 0;                    
            while(in.hasNextLine()) {                
                String currentLine = in.nextLine();                
                try (MyScanner currentLineScanner = new MyScanner(currentLine);) {        
                    //currentLineScanner.setMode(1);                    
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
                    height++;                      
                    currentLineScanner.close();
                } catch (IOException e) {
                    System.out.println("IO error");
                }
            }
            for (int i = height - 1; i >= 0; i--) {
                for (int j = table[i].length - 1; j >= 0; j--) {
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
