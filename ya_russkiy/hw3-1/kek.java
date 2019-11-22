import java.util.Scanner;
import java.util.Arrays;

public class kek {
    public static void solve() {
        Scanner in = new Scanner(System.in);                
        int height = 0;
        int table[][] = new int[1][];                        
        int tableHeight = 0;        
        while(in.hasNextLine()) {
            String currentLine = in.nextLine();
            Scanner currentLineScanner = new Scanner(currentLine);            
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
        }       
        for (int i = height - 1; i >= 0; i--) {
            for (int j = table[i].length - 1; j >= 0; j--) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
        in.close();
    }
    public static void main(String[] args) {
        solve();        
    }   
}
