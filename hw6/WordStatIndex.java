import java.io.*;
import java.util.*;

public class WordStatIndex {
    public static void main(String[] args) {
        try (MyScanner in = new MyScanner (new File(args[0]));
            BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(args[1])));
        ) {            
            Map <String, IntList> map = new LinkedHashMap();
            int ind = 1;
            while (in.hasNextWord()) {
                String s = in.nextWord();
                s = s.toLowerCase();                
                if (map.containsKey(s)) {
                    map.get(s).add(ind);
                } else {
                    map.put(s, new IntList(ind));                    
                }
                ind++;
            }
            for (Map.Entry <String, IntList> cur : map.entrySet()) {      
                writer.write(cur.getKey());
                writer.write(" ");
                IntList x = cur.getValue();
                writer.write(Integer.toString(x.size()));
                writer.write(" ");
                writer.write(x.toStr());
                /*for (int j = 0; j < x.size(); j++) {
                    writer.write(Integer.toString(x.get(j)));                    
                    if (j != x.size() - 1) {
                        writer.write(" ");
                    }
                }*/
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("IO error");
        }
        
    }
}
