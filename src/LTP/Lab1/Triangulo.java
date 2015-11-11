package LTP.Lab1;

public class Triangulo extends Figura {
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
        if (o instanceof Triangulo) {
            Triangulo t = (Triangulo) o;
            return super.equals(o)
                    && base   == t.base
                    && altura == t.altura;
        } else return false;
    }

    public double area() {return base * altura / 2;}
}
