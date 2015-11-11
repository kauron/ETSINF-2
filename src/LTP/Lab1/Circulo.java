package LTP.Lab1;

public class Circulo extends Figura {
    protected double r;

    Circulo(double x, double y, double radio) {
        super(x, y);
        this.r = radio;
    }

    @Override
    public String toString() {
        return String.format("Círculo:\n\t%s\n\tRadio: %f",
                super.toString(), r);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Circulo
                && super.equals(o)
                && r == ((Circulo)o).r;
    }

    public double area() {return Math.PI * r * r;}
}
