package LTP.Lab1;

public class GrupoFiguras {
    static final int MAX_NUM_FIGURAS = 10;

    private Figura[] listaFiguras = new Figura[MAX_NUM_FIGURAS];

    private int num = 0;

    public void anyadeFigura(Figura f) {
        for (int i = 0; i < num; i++) if (listaFiguras[i].equals(f)) return;
        listaFiguras[num++] = f;
    }

    public String toString() {
        String s = "";
        for(int i = 0; i < num; i++)
            s += "\n" + listaFiguras[i].toString();
        return s;
    }

    public boolean equals(Object o) {
        boolean b = o instanceof GrupoFiguras;
        //if not a set a check could be made to see if both num are equal
        //and then the inner loop would iterate to the same num as the exterior
        //leaving the loop's performance in n^2 instead of n*m
        for (int i = 0; i < num && b; i++) {
            boolean c = false; //variable to check if this shape is in the other list
            for (int j = 0; j < ((GrupoFiguras)o).num && !c; j++)
                c = ((GrupoFiguras)o).listaFiguras[j].equals(listaFiguras[i]);
            b = c;
        }
        return b;
    }

    public double area() {
        double area = 0;
        for (int i = 0; i < num; i++)
            area += listaFiguras[i].area();
        return area;
    }

    public double volumen() {
        double volumen = 0;
        for (int i = 0; i < num; i++)
            if (listaFiguras[i] instanceof Volumen)
                volumen += ((Volumen) listaFiguras[i]).volumen();
        return volumen;
    }
}