public class main{
    public static void main(String[] args){
        long a = 2348327482723738188L;
        String res = "";
        for (long i = 0; i < 32; i++){
            long b = (a >> i) & 1L;            
            res = res + b;
        }
        res = res + " ";
        for (int i = 32; i < 64; i++){
            long b = (a >> i) & 1L;
            res = res + b;
        }
        res = res + " ";        
        for (long i = 0; i <= 843354122 + 100000000; i++){
            if ((i ^ (i >> 32L)) == a){
                System.out.println(i);
            }
        }
      //  System.out.println(res);
    }
}
