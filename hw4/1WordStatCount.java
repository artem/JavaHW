import java.util.*;
import java.io.*;

public class WordStatCount {    
    public static Comparator<WordCountStruct> WordCountStructComparator = new Comparator <WordCountStruct>() {
        public int compare(WordCountStruct a1, WordCountStruct a2) {
            if (a1.count == a2.count) {
                return a1.firstPos.compareTo(a2.firstPos);
            } else {
                return a1.count.compareTo(a2.count);
            }   
        }
    };    
    
	public static void main(String[] args) {        
        try (                      
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(args[0]), "utf8")
        )) {
            try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), "utf8")
            )) {                         
                Map <String, PairStruct> hashmap = new HashMap<>();
                int numOfString = 0;
                while (true) {                    
                    StringBuilder buf = new StringBuilder("");                    
                    boolean endFound = false;
                    while(true) {                        
                        int curSym = reader.read();
                        if (curSym == -1) {
                            endFound = true;
                            break;
                        }
                        buf.append((char)curSym);
                        if (Character.getType(buf.charAt(buf.length() - 1)) == Character.CONTROL) {                                
                            break;
                        }
                    }
                    if (endFound) {
                        break;
                    }
                    String s = new String(buf);
                    s = s.toLowerCase();
                    int wordBegin = -1;
                    for (int i = 0; i < s.length(); i++) {
                        if (!WordStatInput.CharacterIsOk(s.charAt(i))) {
                            continue;
                        }               
                        wordBegin = i;
                        while (i < s.length() && WordStatInput.CharacterIsOk(s.charAt(i))) {
                            i++;
                        }
                        String curString = s.substring(wordBegin, i);                            
                        if (!hashmap.containsKey(curString)) {
                            hashmap.put(curString, new PairStruct(1, numOfString));
                        } else {
                            hashmap.get(curString).x++;                            
                        }
                        numOfString++;
                    }
                }
                List <WordCountStruct> mas = new ArrayList <WordCountStruct> (); //Treemap 
                hashmap.forEach((k, v) -> {
                    mas.add(new WordCountStruct(k, v.x, v.y));                        
                });
                Collections.sort(mas, WordCountStructComparator);
                for (WordCountStruct cur : mas) {
                    writer.write(cur.s);
                    writer.write(" ");
                    writer.write(Integer.toString(cur.count));
                    writer.newLine();
                }
            } catch (FileNotFoundException e) {
                System.out.print("File can not be opened or created : ");
                System.out.print(e.getMessage());
            } catch (IOException e) {
                System.out.print("Writer - IOException : ");
                System.out.println(e.getMessage());
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

class PairStruct {
    public int x; // x = first
    public int y; // y = second
    public PairStruct(int a, int b) {
        x = a;
        y = b;
    }
}

class WordCountStruct {
    public String s;
    public Integer count;
    public Integer firstPos;
    public WordCountStruct(String str, int a, int b) {
        s = str;
        count = a;
        firstPos = b;
    }
}
