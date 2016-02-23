package LTP.Lab2;

public class UsandoClasesEnvoltorio {
    public static void main(String[] args) {
        int     i = new Integer(15);
        long    l = new Long(15);
        short   s = new Short((short)0);
        double  d = new Double(3.4);
        float   f = new Float(4.56f);
        byte    b = new Byte((byte)1);
        boolean B = new Boolean(true);
        char    c = new Character('c');

        System.out.printf("%d, %d, %d, %f, %f, %d, %b, %c\n", i, l, s, d, f, b, B, c);
    }
}
