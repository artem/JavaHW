public class Sum{
    public static int getSum(String[] mas) {
        int n = mas.length;
        int res = 0;
        for (int i = 0; i < n; i++){
            int l = -1;
            boolean cur = false;
            for (int j = 0; j < mas[i].length(); j++) {
                if (cur == false) {
                    if (Character.isDigit(mas[i].charAt(j)) || mas[i].charAt(j) == '-') {
                        cur = true;
                        l = j;
                    }
                }
                else {
                    if (!Character.isDigit(mas[i].charAt(j))) {                        
                        res += Integer.parseInt(mas[i].substring(l, j));
                        cur = false;
                        l = -1;
                        if (mas[i].charAt(j) == '-')
                            j--;
                    }
                }
            }
            if (cur == true)
                res += Integer.parseInt(mas[i].substring(l, mas[i].length()));
        }
        return res;
    }
    public static void main(String[] args) {
        int ans = getSum(args);
        System.out.println(ans);
    }
}  
