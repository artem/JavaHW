public class Sum {    
    static int sum(String s){
        int a = 0;
        int cur = 0;
        int z = 0;
        int mn = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {            
            if (z == 0 && Character.isDigit(s.charAt(i))) {
                z = 1;                
                int x = s.charAt(i) - '0';
                cur *= 10;
                cur += x;
            }
            else if (z == 1 && Character.isDigit(s.charAt(i))) {
                int x = s.charAt(i) - '0';
                cur *= 10;
                cur += x;
            }
            else if (z == 1 && !(Character.isDigit(s.charAt(i)))) {                
                if (mn == 1) {
                    cur *= -1;
                }
                mn = 0;
                a += cur;
                z = 0;
                cur = 0;
                if (s.charAt(i) == '-') {
                    z = 1;
                    mn = 1;
                }
            }
            else if (s.charAt(i) == '-' && z == 0) {
                mn = 1;
                z = 1;
                cur = 0;
            }            
           // System.out.println(mn);
        }
        if (z == 1) {
            if (mn == 1)
                cur *= -1;
            mn = 0;
            a += cur;
            z = 0;
        }        
        return a;
    }
    public static void main(String[] args) {        
        String s = "";        
        for (int i = 0; i < args.length; i++)
            s = s + args[i] + " ";
        //System.out.println(s);
        System.out.println(sum(s));
    }
}
