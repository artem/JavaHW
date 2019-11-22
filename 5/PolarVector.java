public class PolarVector {
    private double rho;
    private double phi;
    
    //поля
    //конструкторы
    //методы
    
    public PolarVector(double rho, double phi) {
        if (rho < 0) {
            throw new IllegalArgumentException("rho < 0");
        }
        this.rho = rho;
        this.phi = phi;
    }
    
    public double getRho() {
        return rho;
    }
    
    public double getPhi() {
        return phi;
    }
    
    public double getX() {
        return Math.cos(phi) * rho;
    }
    
    public double getY() {
        return Math.sin(phi) * rho;
    }
    
    public static PolarVector cartesian(double x, double y) {
        return new PolarVector(Math.hypot(x, y), Math.atan2(y, x));
    }  
    public static PolarVector polar(double rho, double phi) {
        // у Static нету this;
        return new PolarVector(rho, phi);
    }  
    
}
