package aplicaciones.letras;


/**
 * Representa cada uno de los pixeles de una imagen descritos en RGB.
 * Un pixel tiene 3 valores (entre 0 y 255): 
 *            rojo(Red), verde(Green) y azul(Blue)
 * 
 * @author (EDA) 
 * @version (Febrero 2016)
 */
public class Pixel {
    public static final int BASE = 255;
    // Atributos: RGB
    private int r, g, b; 
    private int hashCode;
    
    /** Crea un pixel de color negro */
    public Pixel() { 
        r = 0; g = 0; b = 0;
        hashCode = 0;
    }
    
    /** Crea un pixel con valores de rojo(r), verde(g) y azul(b)
        @param r, rojo
        @param g, verde
        @param b, azul
        */
    public Pixel(int red, int green, int blue) { 
        this.r = red; this.g = green; this.b = blue; 
        hashCode = r * BASE * BASE + g * BASE + b; 
    }
    //Consultores:
    /** Devuelve el valor del componente rojo */
    public int getR() { return r; }
    /** Devuelve el valor del componente verde */
    public int getG() { return g; }
    /** Devuelve el valor del componente azul */
    public int getB() { return b; }
    
    /** Devuelve true si o es un pixel con los mismos componentes que this;
     *  false en caso contrario */
    public boolean equals(Object o) {
        if (o instanceof Pixel) {
            Pixel p = (Pixel) o;
            return (p.r == r) && (p.g == g) && (p.b == b);
        }
        return false;
    }
    public String toString() {
        return r + " " + g + " " + b;
    }
    
    /** suma ponderada de las componentes de un pixel */
    public int hashCode() {
        return hashCode;
    }
}