package LTP.Lab1;

public class Paralepipedo extends Rectangulo implements Volumen {
    protected double a;

    Paralepipedo(double x, double y, double bx, double by, double a){
        super(x, y, bx, by);
        this.a = a;
    }

    Paralepipedo(Rectangulo r, double a) {
        this(r.x, r.y, r.base, r.altura, a);
    }

    public String toString() {
        return String.format("Paralepipedo:\n\t%s\n\tBase: %f x %f\n\tAltura: %f",
                getPosicion(), super.base, super.altura, a);
    }

    public boolean equals(Object o) {
        return o instanceof Paralepipedo
                && super.equals(o)
                && a == ((Paralepipedo)o).a;
    }

    public double volumen() {return super.area() * a;}
    public double superficie() {return area();}

    public double area() {
        Rectangulo r1 = new Rectangulo(0, 0, super.base, a);
        Rectangulo r2 = new Rectangulo(0, 0, super.altura, a);
        return (super.area() + r1.area() + r2.area()) * 2;
    }
}
