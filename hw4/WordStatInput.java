import java.util.*;
import java.io.*;

public class WordStatInput {    
    public static boolean CharacterIsOk(char c) {
        if (Character.isLetter(c) || 
        Character.getType(c) == Character.DASH_PUNCTUATION 
        || c == '\'') {
            return true;
        } else {
            return false;
        }
    }             

	public static void main(String[] args) {        
        try (                      
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(args[0]), "utf8"));
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), "utf8")
            );
        ) {                                
            int countOfString[] = new int[1];        
            int endOfStringArray = 0;
            String stringArray[] = new String[1];
            while (true) {
                StringBuilder buf = new StringBuilder();                    
                boolean endFound = false;
                boolean wordBegins = false;
                while(true) {                        
                    int curSym = reader.read();                    
                    if (curSym == -1) {
                        endFound = true;
                        break;
                    }                                        
                    if (!WordStatInput.CharacterIsOk((char)curSym)) {                               
                        if (wordBegins) {
                            break;                        
                        }
                    } else {
                        if (!wordBegins) {
                            wordBegins = true;
                        }                          
                        buf.append((char)curSym);                            
                    }
                }                                
                String s = buf.toString();               
                s = s.toLowerCase();
                int wordBegin = -1;
                for (int i = 0; i < s.length(); i++) {
                    if (!CharacterIsOk(s.charAt(i))) {
                        continue;
                    }               
                    wordBegin = i;
                    while (i < s.length() && CharacterIsOk(s.charAt(i))) {
                        i++;
                    }
                    String curString = s.substring(wordBegin, i);
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
                if (endFound) {
                    break;
                }
            }
            for (int i = 0; i < endOfStringArray; i++) {
                writer.write(stringArray[i]);
                writer.write(" ");
                writer.write(Integer.toString(countOfString[i]));
                writer.newLine();
            }            
        } 
        catch (FileNotFoundException e) {
            System.out.print("File doesn't exists : ");
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.print("Reader - IOException : ");
            System.out.println(e.getMessage());
        }
    }
}
