public class SumLongHex{
    public static Long getSum(String[] mas) {
        int n = mas.length;
        long res = 0;
        for (int i = 0; i < n; i++){            
            boolean cur = false;            
            boolean hex = false;
            mas[i] = mas[i].toLowerCase();
            for (int j = 0; j < mas[i].length(); j++) {                
                if (Character.isWhitespace(mas[i].charAt(j))) {
                    continue;       
                }
                int l = j;                
                if (j + 1 < mas[i].length() && mas[i].substring(j, j + 2).equals("0x")) {
                    j++;
                    hex = true;
                    continue;
                }/*
                if (j + 1 < mas[i].length() && mas[i].charAt(j) == '0' && (mas[i].charAt(j + 1) == 'x')) { //starts with, в нижнийй регистр + сабстринг
                    j++;
                    hex = true;
                    continue;
                }*/
                while (l < mas[i].length() && !Character.isWhitespace(mas[i].charAt(l))) {                    
                    l++;
                }
                int x = 0;
                if (hex) {
                    res += Long.parseUnsignedLong(mas[i].substring(j, l), 16);
                    hex = false;
                } else {
                    res += Long.parseLong(mas[i].substring(j, l));
                }
                    
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
