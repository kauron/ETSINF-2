package LTP.Topic1;

public class Square extends Shape {
    private double side;

    public Square (double x, double y, double side){
        super(x, y);
        this.side = side;
    }

    public double getSide() {return side;}

    public double getArea () {
        return side*side;
    }

    public double getPerimeter() {
        return side * 4;
    }
}
