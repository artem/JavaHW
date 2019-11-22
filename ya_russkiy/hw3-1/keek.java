import java.util.Scanner;
import java.util.Arrays;

public class Reverse {
    public static void solve() {
        Scanner in = new Scanner(System.in);                
        int n = 0;
        int mas[][] = new int[1][];                        
        int masSz = 0;        
        while(in.hasNextLine()) {
            String x = in.nextLine();
            Scanner currentLineScanner = new Scanner(x);            
            if (masSz == mas.length) {
                //int nwMas[][] = new int[2 * masSz][];
                //System.arraycopy(mas, 0, nwMas, 0, masSz);                
                //mas = nwMas;                
                mas = Arrays.copyOf(mas, 2 * masSz);
            }
            int vec[] = new int[1];
            int vecSz = 0;
            while(currentLineScanner.hasNextInt()) {
                if (vecSz == vec.length) {
                    //int nwVec[] = new int[vecSz * 2];
                    //System.arraycopy(vec, 0, nwVec, 0, vecSz);
                    //vec = nwVec;                    
                    vec = Arrays.copyOf(vec, 2 * vecSz);
                }                
                int y = currentLineScanner.nextInt();
                vec[vecSz++] = y;       
                //System.out.println(y);
            }            
            //System.out.println();                                      
            //System.out.println(Arrays.toString(vec) + " " + vecSz);
            //mas[masSz] = new int[0];
            //System.arraycopy(vec, 0, mas[masSz++], 0, vecSz);            
            mas[masSz++] = Arrays.copyOf(vec, vecSz);
            n++;          
            //masSz++;
        }
       // System.out.println();
        for (int i = n - 1; i >= 0; i--) {
            for (int j = mas[i].length - 1; j >= 0; j--) {
                System.out.print(mas[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        solve();
        
    }   
}
