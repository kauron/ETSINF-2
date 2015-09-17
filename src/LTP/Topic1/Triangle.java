package LTP.Topic1;

import java.util.Arrays;

public class Triangle extends Shape{

    public static final int A = 0, B = 1, C = 2;

    protected double[] sides;
    protected double[] angles;

    /**
     *
     * @param x Position of the angle A
     * @param y Position of the angle B
     * @param sides Array of size 3 with the sides
     * @param angles Array of size 3 with the angles
     * @throws Exception When the arrays are missing or the triangle cannot be solved
     */
    public Triangle (double x, double y, double[] sides, double[] angles) throws Exception {
        super(x, y);
        if (sides != null && sides.length == 3)
            this.sides = sides;
        else throw new Exception ("Sides missing or not the correct amount (3)");
        if (angles != null && angles.length == 3)
            this.angles = angles;
        else throw new Exception ("Angles missing or not the correct amount (3)");
        if (!solve()) {
            System.out.println(this);
            throw new Exception ("Triangle cannot be solved");
        }
    }

    protected boolean solve() {
        int sidecount = 0, anglecount = 0;
        for (int i = A; i <= C; i++) {
            if (sides[i] != 0) sidecount++;
            if (angles[i] != 0) anglecount++;
        }
        if (sidecount + anglecount < 3) return false;
        if (sidecount == 3) {
            double[] realAngles = new double[3];
            realAngles[C] = Math.acos((sides[A] * sides[A] + sides[B] * sides[B] - sides[C] * sides[C]) / (2 * sides[A] * sides[C]));
            realAngles[B] = Math.asin(sides[B] * Math.sin(realAngles[C]) / sides[C]);
            realAngles[A] = Math.asin(sides[A] * Math.sin(realAngles[C]) / sides[C]);
            for (int i = A; i <= C; i++)
                if (angles[i] != 0 && realAngles[i] != angles[i]) return false;
            angles = realAngles;
        } else if (sidecount == 2) {
            if (anglecount == 1) {
                if (sides[A] == 0) {
                    if (angles[A] != 0) {
                        sides[A] = Math.sqrt(sides[B] * sides[B] + sides[C] * sides[C] - 2 * sides[B] * sides[C] * Math.cos(angles[A]));
                    } else if (angles[B] != 0) {
                        angles[C] = Math.asin(sides[C] * Math.sin(angles[B]) / sides[B]);
                    } else if (angles[C] != 0) {
                        angles[B] = Math.asin(sides[B] * Math.sin(angles[C]) / sides[C]);
                    }
                } else if (sides[B] == 0) {
                    if (angles[A] != 0) {
                        angles[C] = Math.asin(sides[C] * Math.sin(angles[A]) / sides[A]);
                    } else if (angles[B] != 0) {
                        sides[A] = Math.sqrt(sides[A] * sides[A] + sides[C] * sides[C] - 2 * sides[A] * sides[C] * Math.cos(angles[B]));
                    } else if (angles[C] != 0) {
                        angles[A] = Math.asin(sides[A] * Math.sin(angles[C]) / sides[C]);
                    }
                } else if (sides[C] == 0) {
                    if (angles[A] != 0) {
                        angles[B] = Math.asin(sides[B] * Math.sin(angles[A]) / sides[A]);
                    } else if (angles[B] != 0) {
                        angles[A] = Math.asin(sides[A] * Math.sin(angles[B]) / sides[B]);
                    } else if (angles[C] != 0) {
                        sides[C] = Math.sqrt(sides[A] * sides[A] + sides[B] * sides[B] - 2 * sides[A] * sides[B] * Math.cos(angles[C]));
                    }
                }
            }
            if (anglecount == 2) {
                if (angles[A] == 0) angles[A] = Math.PI - angles[B] - angles[C];
                else if (angles[B] == 0) angles[B] = Math.PI - angles[A] - angles[C];
                else if (angles[C] == 0) angles[C] = Math.PI - angles[A] - angles[B];
                anglecount++;
            }
            if (anglecount == 3) {
                if (sides[A] == 0) {
                    sides[A] = sides[B] * Math.sin(angles[A]) / Math.sin(angles[B]);
                } else if (sides[B] == 0) {
                    sides[B] = sides[A] * Math.sin(angles[B]) / Math.sin(angles[A]);
                } else if (sides[C] == 0) {
                    sides[C] = sides[B] * Math.sin(angles[C]) / Math.sin(angles[B]);
                }
            }
            return solve();
        } else if (sidecount == 1) {
            if (anglecount == 2) {
                if (angles[A] == 0) angles[A] = Math.PI - angles[B] - angles[C];
                else if (angles[B] == 0) angles[B] = Math.PI - angles[A] - angles[C];
                else /*if (angles[C] == 0)*/ angles[C] = Math.PI - angles[A] - angles[B];
                anglecount++;
            }
            if (anglecount == 3) {
                double[] realSides = new double[3];
                if (sides[A] != 0) {
                    realSides[A] = sides[A];
                    realSides[B] = realSides[A] * Math.sin(angles[B]) / Math.sin(angles[A]);
                    realSides[C] = realSides[A] * Math.sin(angles[C]) / Math.sin(angles[A]);
                } else if (sides[B] != 0) {
                    realSides[A] = realSides[B] * Math.sin(angles[A]) / Math.sin(angles[B]);
                    realSides[B] = sides[B];
                    realSides[C] = realSides[B] * Math.sin(angles[C]) / Math.sin(angles[B]);
                } else if (sides[C] != 0) {
                    realSides[A] = realSides[C] * Math.sin(angles[A]) / Math.sin(angles[C]);
                    realSides[B] = realSides[C] * Math.sin(angles[B]) / Math.sin(angles[C]);
                    realSides[C] = sides[A];
                }
                for (int i = A; i <= C; i++)
                    if (realSides[i] != sides[i]) return false;
                sides = realSides;
            }
        } else return false;
        return true;
    }

    @Override
    public double getPerimeter() {
        return sides[A] + sides[B] + sides[C];
    }

    @Override
    public double getArea() {
        return sides[A] * sides[C] * Math.sin(angles[B]);
    }

    @Override
    public String toString() {
        return "Triangle with sides " + Arrays.toString(sides) + " and angles " + Arrays.toString(angles);
    }
}
