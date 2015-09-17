package LTP.Topic1;

public abstract class Shape{

	/*
	4 parameters for each shape
	2 for position
	1 for type
	1 for radius/side
	*/

    protected double x, y;

    public Shape (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {return x;}
    public double getY() {return y;}
    public abstract double getPerimeter();
    public abstract double getArea();
}