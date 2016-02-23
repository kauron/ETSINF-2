package LTP.Topic1;

public class Square extends Rectangle {

    public Square (double x, double y, double side){
        super(side, side, x, y);
    }

    public double getSide() {return super.getBase();}
}
