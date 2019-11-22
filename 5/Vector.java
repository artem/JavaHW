public class Vector {
    //private double x;
    //private double y;
    private final double x;
    private final double y;
    //должны быть проиницализированы ровно 1 раз
    private static int counter;
    //поля
    //конструкторы
    //методы
    
    public Vector(double x, double y, boolean polar) {        
        if (polar) {
            this.x = Math.cos(y) * x;
            this.y = Math.sin(y) * x;
        } else {
            this.x = x;
            this.y = y;
        }
        Vector.counter++;
    }  
    
    public static int getCounter() {
        return counter;
    }
    
    public Vector add(Vector v) {
        return new Vector(x + v.x, y + v.y, false);
        /*
        // this += v
        x += v.x;
        y += v.getY();
        */
    }

    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double getRho() {
        return Math.hypot(x, y);
    }
    
    public double getPhi() {
        return Math.atan2(y, x);
    }
    
    public static Vector cartesian(double x, double y) {
        return new Vector(x, y, false);
    }  
    public static Vector polar(double rho, double phi) {
        // у Static нету this;
        return new Vector(Math.cos(phi) * rho, Math.sin(phi) * rho, true);
    }  

    public String toString() {
        return "Vector(" + x + ", " + y + ")";
    }
    public Vector copy() {
        return new Vector(x, y, false);
    }
}
