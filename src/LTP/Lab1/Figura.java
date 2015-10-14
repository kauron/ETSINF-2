package LTP.Lab1;

public abstract class Figura {
    protected double x,y;

    public Figura(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return String.format("Posición: (%f,%f)", x, y);
    }

    public boolean equals(Object o) {
        return o instanceof Figura
                && x == ((Figura)o).x
                && y == ((Figura)o).y;
    }

    public abstract double area();
}