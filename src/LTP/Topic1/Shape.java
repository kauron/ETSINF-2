package LTP.Topic1;

abstract public class Shape{

	/*
	4 parameters for each shape
	2 for position
	1 for type
	1 for radius/side
	*/

    private double x, y;

    public Shape (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {return x;}
    public double getY() {return y;}
    abstract public double getPerimeter();
    abstract public double getArea();
}