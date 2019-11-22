

// Объекты
// данные + методы(функции котроые их обрабатывают)
//


public class Main {
    public static void main(String[] args) {        
        //Vector v = Vector.cartesian(1, Math.PI / 2);
        Vector v = Vector.polar(1, Math.PI / 2);
        Circle c = new Circle(v, 10.0);
        v = v.add(Vector.cartesian(1, 2));        
        System.out.println(v.getRho() + " " + v.getPhi());
        System.out.println(v.getX() + " " + v.getY());                        
        v.add(Vector.cartesian(-11, -13));
        System.out.println(c);
        /*
        Client c = new Client("123456", "Best Client");        
        System.out.println(c.getName() + " " + c.getPassport());
        */
        
    }
}
