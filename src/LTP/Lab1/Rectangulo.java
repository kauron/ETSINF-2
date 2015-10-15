package LTP.Lab1;


public class Rectangulo extends Figura {
    protected double base, altura;

    Rectangulo(double x, double y, double base, double altura) {
        super(x, y);
        this.base = base;
        this.altura = altura;
    }

    public String toString() {
        return String.format("Rectángulo:\n\t%s\n\tBase: %f\n\tAltura: %f",
                super.toString(), base, altura);
    }

    public boolean equals(Object o) {
        if (o instanceof Rectangulo) {
            Rectangulo r = (Rectangulo) o;
            return super.equals(o)
                    && base   == r.base
                    && altura == r.altura;
        } else return false;
    }

    public double area() {return base * altura;}
}
