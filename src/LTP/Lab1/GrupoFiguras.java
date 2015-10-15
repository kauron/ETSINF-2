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

    public boolean perteneceA(GrupoFiguras g) {
        for (int i = 0; i < num; i++) {
            boolean pertenece = false; //variable to check if this shape is in the other list
            for (int j = 0; j < g.num && !pertenece; j++)
                pertenece = g.listaFiguras[j].equals(listaFiguras[i]);
            if (!pertenece) return false;
        }
        return true;
    }

    public boolean equals(Object o) {
        return o instanceof GrupoFiguras
                && this.perteneceA((GrupoFiguras) o)
                && ((GrupoFiguras)o).perteneceA(this);
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