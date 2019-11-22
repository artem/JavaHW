import java.util.*;
import java.io.*;

public class WordStatInput {          
	public static void main(String[] args) {        
        try (                      
        /*BufferedReader reader = new BufferedReader(
            new InputStreamReader(new FileInputStream(args[0]), "utf8"));*/
        MyScanner reader = new MyScanner(new File(args[0]));
        BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(args[1])));
        ) {                        
            int countOfString[] = new int[1];        
            int endOfStringArray = 0;
            String stringArray[] = new String[1];
            while(reader.hasNextWord()) {
                String curString = reader.next().toLowerCase();           
                boolean wasFound = false;
                for (int j = 0; j < endOfStringArray; j++) {
                    if (curString.equals(stringArray[j])) {
                        wasFound = true;
                        countOfString[j]++;
                        break;
                    }
                }
                if (wasFound) {
                    continue;
                }
                if (endOfStringArray == stringArray.length) {
                    countOfString = Arrays.copyOf(countOfString, 2 * countOfString.length);
                    stringArray = Arrays.copyOf(stringArray, 2 * stringArray.length);                    
                }
                stringArray[endOfStringArray] = curString;
                countOfString[endOfStringArray++]++;                        
            }
            for (int i = 0; i < endOfStringArray; i++) {
                writer.write(stringArray[i]);
                writer.write(" ");
                writer.write(Integer.toString(countOfString[i]));
                writer.newLine();
            }
        }
        catch (FileNotFoundException e) {
            System.out.print("File can not be opened or created : ");
            System.out.print(e.getMessage());
        } catch (IOException e) {
            System.out.print("Writer - IOException : ");
            System.out.println(e.getMessage());
        }
    }
}
