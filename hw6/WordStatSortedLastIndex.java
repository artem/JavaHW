import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordStatSortedLastIndex {
    public static void main(String[] args) {

        try (MyScanner in = new MyScanner (new File(args[0]));
             BufferedWriter writer = new BufferedWriter(
                     new OutputStreamWriter(new FileOutputStream(args[1])));
        ) {
            Map <String, List<IntList>> map = new TreeMap();
            int lineNumber = 0;
            while (in.hasNextLine()) {                
                MyScanner line = new MyScanner(in.nextLine());
                int ind = 0;
                while (line.hasNextWord()) {
                    String s = line.nextWord().toLowerCase();
                    List <IntList> cur = map.get(s);
                    if (cur == null) {
                        List <IntList> nwList = new ArrayList<IntList>();
                        IntList nwIntList = new IntList(lineNumber);
                        nwIntList.add(ind);
                        nwList.add(nwIntList);
                        map.put(s, nwList);
                    } else {
                        if (cur.get(cur.size() - 1).get(0) != lineNumber) {
                            cur.add(new IntList(lineNumber));
                        }
                        cur.get(cur.size() - 1).add(ind);
                    }
                    ind++;
                }
                lineNumber++;
            }
            for (Map.Entry <String, List<IntList>> cur : map.entrySet()) {
                writer.write(cur.getKey());
                writer.write(" ");
                List <IntList> value = cur.getValue();
                int all = 0;
                for (int i = 0; i < value.size(); i++) {
                    all += value.get(i).size() - 1;                    
                }
                writer.write(Integer.toString(all));
                writer.write(" ");
                for (int i = 0; i < value.size(); i++) {
                    IntList curIntList = value.get(i);
                    writer.write(Integer.toString(curIntList.get(curIntList.size() - 1) + 1));
                    if (i < value.size() - 1) {
                        writer.write(" ");
                    }
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("IO error");
        }

    }
}
