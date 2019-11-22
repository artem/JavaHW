import java.util.*;
import java.io.*;

public class WordStatCount {        
	public static void main(String[] args) {        
        try (                      
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(new FileInputStream(args[0]), "utf8"));
        BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"));
        ) {            
            List <MyStruct> mas = new ArrayList<>();      
            int numOfString = 0;
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
                if (s.equals("")) {
                    break;
                }
                mas.add(new MyStruct(0, numOfString++, s));                
                if (endFound) {
                    break;
                }
            }            
            mas.sort((a, b) -> (a.s.compareTo(b.s)));
            List <MyStruct> v = new ArrayList<>();
            for (int i = 0; i < mas.size(); i++) {
                if (!v.isEmpty() && v.get(v.size() - 1).s.equals(mas.get(i).s)) {
                    v.get(v.size() - 1).x++;
                } else {
                    v.add(new MyStruct(1, mas.get(i).y, mas.get(i).s));
                }
            }
            v.sort((a, b) -> (a.x == b.x ? Integer.compare(a.y, b.y) : Integer.compare(a.x, b.x)));
            for (MyStruct cur : v) {
                writer.write(cur.s);
                writer.write(" ");
                writer.write(Integer.toString(cur.x));                
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


class MyStruct {
    public int x; // x = first
    public int y; // y = second
    public String s;
    public MyStruct (int a, int b, String t) {
        x = a;
        y = b;
        s = t;
    }
}

