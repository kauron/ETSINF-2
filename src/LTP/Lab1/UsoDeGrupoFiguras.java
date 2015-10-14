package LTP.Lab1;

public class UsoDeGrupoFiguras {
    public static void main(String[] args) {
        GrupoFiguras g = new GrupoFiguras();

        g.anyadeFigura(new Circulo(10, 5, 3.5));
        g.anyadeFigura(new Triangulo(10, 5, 6.5, 32));
        g.anyadeFigura(new Rectangulo(20, 8, 9.3, 40));

        System.out.println(g);
    }
}
