public class Sum{
    public static Long getSum(String[] mas) {
        int n = mas.length;
        long res = 0;
        for (int i = 0; i < n; i++){            
            boolean cur = false;
            for (int j = 0; j < mas[i].length(); j++){
                if (Character.isWhitespace(mas[i].charAt(j)))
                    continue;       
                int l = j;
                while(l < mas[i].length() && !Character.isWhitespace(mas[i].charAt(j))) {
                    l++;
                }
                res += Long.parseLong(mas[i].substring(j, l), 16);
                j = l;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        Long ans = getSum(args);
        System.out.println(ans);
    }
}  
