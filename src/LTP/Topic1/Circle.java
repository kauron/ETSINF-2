package LTP.Topic1;

public class Circle extends Shape {
    private double radius;

    public Circle (double x, double y, double radius) {
        super(x, y);
        this.radius = radius;
    }

    public double getRadius() {return radius;}

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return Math.PI * radius * 2;
    }
}