package LTP.Lab1;

public class Cubo extends Cuadrado implements Volumen {
    
    Cubo(double x, double y, double arista){
        super(x, y, arista);
    }
    
    Cubo(Cuadrado c) {super(c.x, c.y, c.base);}
    
    public String toString() {
        return String.format("Cubo:\n\tArista: %f", super.base);
    }
    
    public boolean equals(Object o) {
        return o instanceof Cubo
                && super.equals(o);
    }
    
    public double volumen() {return super.area() * super.base;}
    public double superficie() {return area();}
    
    public double area() {return super.area() * 6;}
}