package LTP.Topic1;

import java.util.Locale;
import java.util.Scanner;

public class TestingTriangles {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in).useLocale(Locale.US);
        boolean rad = true;
        System.out.print("[A]ngles or [r]adians? > ");
        if (s.nextLine().equalsIgnoreCase("a")) rad = false;

        while(true) {
            System.out.print("Input data for a triangle (x y sides[3] angles[3]) > ");
            try {
                Triangle t = new Triangle(
                        s.nextDouble(), s.nextDouble(),
                        new double[] {s.nextDouble(), s.nextDouble(), s.nextDouble()},
                        new double[] {
                                s.nextDouble() * (rad ? 1 : Math.PI / 180),
                                s.nextDouble() * (rad ? 1 : Math.PI / 180),
                                s.nextDouble() * (rad ? 1 : Math.PI / 180)
                        }
                );
                System.out.println("\n" + t + "\n\n");
            } catch (Exception e) {
                System.err.println("There was an error");
                e.printStackTrace();
                return;
            }
        }
    }
}
