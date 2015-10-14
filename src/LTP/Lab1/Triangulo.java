package LTP.Lab1;

public class Triangulo extends Figura{
    protected double base, altura;

    Triangulo(double cx, double cy, double b, double a) {
        super(cx, cy);
        base = b;
        altura = a;
    }

    public String toString() {
        return String.format("Triángulo:\n\t%s\n\tBase: %f\n\tAltura: %f",
                super.toString(), base, altura);
    }

    public boolean equals(Object o) {
        return o instanceof Triangulo
                && super.equals(o)
                && base == ((Triangulo)o).base
                && altura == ((Triangulo)o).altura;
    }

    public double area() {return base * altura / 2;}
}
