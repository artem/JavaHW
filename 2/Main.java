import java.util.Arrays;
//import java.util.*
import java.util.Scanner;

public class Main{
    public static void fill(int[] arr, int val){
        for (int i = 0; i < arr.length; i++)
            arr[i] = val;
    }
    public static int[] create(int len, int val){
        int arr[] = new int[len];
        fill(arr, val);
        return arr;
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int x = s.nextInt();
        System.out.println(x);
        //int [][] ints = new int[3][5];
        int [][] ints = new int[][]{
            new int[]{1, 2},
            new int[]{3},
            new int[]{4}
        };
        
        /*int ints[] = new int[]{10, 20, 30, 40};
        int ints2[] = create(7, -1);
        System.arraycopy(ints, 1, ints2, 2, 3);*/
        //System.out.println(java.util.Arrays.toString(ints));
        System.out.println(Arrays.deepToString(ints));
        /*for (int v : ints2){
            System.out.println(v);
        }*/
        /*for (int i = 0; i < ints.length; i++){
            //fill(ints[i], i + 1);
            for (int j : ints[i]){
                System.out.print(j + " ");
            }
            System.out.println();
        }*/
    }
}  
