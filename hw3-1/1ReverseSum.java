import java.util.Arrays;
import java.io.*;
import java.util.*;

public class ReverseSum {
    public static void solve() {
        //Scanner in = new Scanner(System.in);
        Scanner inn = new Scanner(System.in);
       // try( Writer outt = new FileWriter(new File("input.txt"))) {        
       /* String[] mas = new String[3];
        int id = 0;
        System.out.println(inn.nextLine());
        while(inn.hasNextLine()) {
            mas[id++] = inn.nextLine();
        }
        for (int i = 0; i < id; i++)
            outt.write(mas[id]);*/
        try (MyScanner in = new MyScanner(System.in)) {
        //try (Scanner in = new Scanner(System.in)){
            int height = 0;
            int table[][] = new int[1][];
            int colSumArr[] = new int[1];
            int strSumArr[] = new int[1];
            int maxLineLength = 0;
            int x = 0;
            while (true) {
                String currentLine = in.nextLine();
                //String currentLine = mas[x++];
                if (currentLine == null) {
                    break;
                }
                //System.out.println(currentLine);
                MyScanner currentLineScanner = new MyScanner(currentLine);
                
                if (height == strSumArr.length) {
                    strSumArr = Arrays.copyOf(strSumArr, 2 * strSumArr.length);
                }
                if (height == table.length) {
                    table = Arrays.copyOf(table, 2 * height);
                }
                int currentLineArray[] = new int[1];
                int currentLineArrayEnd = 0;
                int currentInt = -1;
                while (currentLineScanner.hasNext()) {
                    if (currentLineArrayEnd == colSumArr.length) {
                        colSumArr = Arrays.copyOf(colSumArr, 2 * colSumArr.length);
                    }
                    if (currentLineArrayEnd == currentLineArray.length) {
                        currentLineArray = Arrays.copyOf(currentLineArray, 2 * currentLineArrayEnd);
                    }
                    currentInt = currentLineScanner.nextInt();
                    //System.out.print(currentInt + " ");
                    strSumArr[height] += currentInt;
                    currentLineArray[currentLineArrayEnd] = currentInt;
                    colSumArr[currentLineArrayEnd++] += currentInt;
                }
                //System.out.println();
                table[height] = Arrays.copyOf(currentLineArray, currentLineArrayEnd);
                //System.out.println(table[height]);
                maxLineLength = Math.max(maxLineLength, table[height++].length);
                currentLineScanner.close();
            }
            strSumArr = Arrays.copyOf(strSumArr, height);
            colSumArr = Arrays.copyOf(colSumArr, maxLineLength);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < table[i].length; j++) {
                    System.out.print(strSumArr[i] + colSumArr[j] - table[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.print("IOException found : ");
            System.out.println(e.getMessage());
        }
    /*}catch(IOException e) {
        System.out.println("jopa");
    }*/
    }
    public static void main(String[] args) {
        solve();
    }
}
