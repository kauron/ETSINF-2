package LTP.Lab1;

public class Cuadrado extends Rectangulo {
    Cuadrado(double x, double y, double lado) {
        super(x, y, lado, lado);
    }
    
    public String toString() {
        return String.format("Cuadrado:\n\t%s\n\tLado: %f",
                ((Figura)this).toString(), super.base);
    }
    
    public boolean equals(Object o) {
        return o instanceof Cuadrado && super.equals(o);
    }
}
