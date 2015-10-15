package LTP.Lab1;

public class Cilindro extends Circulo implements Volumen{
    protected double a;

    Cilindro(double x, double y, double r, double a){
        super(x, y, r);
        this.a = a;
    }

    Cilindro(Circulo c, double a) {
        this(c.x, c.y, c.r, a);
    }

    public String toString() {
        return String.format("Cilindro:\n\tRadio: %f\n\tAltura: %f",
                super.r, a);
    }

    public boolean equals(Object o) {
        return o instanceof Cilindro
                && super.equals(o)
                && a == ((Cilindro)o).a;
    }

    public double volumen() {return super.area() * a;}
    public double superficie() {return area();}

    public double area() {
        Rectangulo rect = new Rectangulo(0, 0, super.r * 2 * Math.PI, a);
        return super.area() * 2 + rect.area();
    }
}
