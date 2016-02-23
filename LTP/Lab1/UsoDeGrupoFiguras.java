package LTP.Lab1;

public class UsoDeGrupoFiguras {
    public static void main(String[] args) {
        GrupoFiguras g = new GrupoFiguras();

        g.anyadeFigura(new Cilindro(10, 5, 1, 3));
        g.anyadeFigura(new Circulo(10, 5, 3.5));
        g.anyadeFigura(new Cuadrado(1, 1, 1));
        g.anyadeFigura(new Cubo(3, 5, 10));
        g.anyadeFigura(new Paralepipedo(0, 1, 2, 3, 4));
        g.anyadeFigura(new Rectangulo(20, 8, 9.3, 40));
        g.anyadeFigura(new Triangulo(10, 5, 6.5, 32));


        System.out.println(g);
    }
}
