package LTP.Lab1;

public abstract class Figura {
    protected double x,y;

    public Figura(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String getPosicion() {
        return String.format("Posición: (%f,%f)", x, y);
    }

    public String toString() {return getPosicion();}

    public boolean equals(Object o) {
        if (o instanceof Figura) {
            Figura f = (Figura) o;
            return x == f.x
                && y == f.y;
        } else {
            return false;
        }
    }

    public abstract double area();
}