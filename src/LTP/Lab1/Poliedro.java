package LTP.Lab1;

public abstract class Poliedro extends Figura implements Volumen {
    protected double arista;

    public Poliedro(double x, double y, double arista) {
        super(x, y);
        this.arista = arista;
    }

    public abstract double area();
    public abstract double volumen();
}
