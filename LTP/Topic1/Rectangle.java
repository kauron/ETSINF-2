package LTP.Topic1;

public class Rectangle extends Shape{

    protected double base, height;

    public Rectangle (double base, double height, double x, double y) {
        super(x, y);
        this.base = base;
        this.height = height;
    }

    public double getBase() {return base;}
    public double getHeight() {return height;}

    @Override
    public double getPerimeter() {
        return base * 2 + height * 2;
    }

    @Override
    public double getArea() {
        return base * height;
    }
}