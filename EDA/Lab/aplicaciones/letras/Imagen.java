package aplicaciones.letras;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Representa una imagen como una matriz de pixels.
 * La imagen tiene un tamanyo ancho x alto
 * Su hashCode se puede calcular con alguna de las siguientes funciones:
 * 1. Suma de todos sus pixeles (SUMA_TODOS)
 * 2. Suma ponderada de todos sus pixeles con base 255 (SUMA_PONDERADA_TODOS)
 * 3. Suma de los pixeles de la submatriz central 3x3 (SUMA_TRES)
 * 4. Suma ponderada de los pixeles de la submatriz central 3x3
 *    con base 255 (SUMA_PONDERADA_TRES)
 *
 * @author (EDA)
 * @version (Febrero 2016)
 */
public class Imagen {
    public static final int SUMA_TODOS           = 0;
    public static final int SUMA_PONDERADA_TODOS = 1;
    public static final int SUMA_TRES            = 2;
    public static final int SUMA_PONDERADA_TRES  = 3;
    public static final String[] NOMFDIS = {"SUMA_TODOS",
        "SUMA_PONDERADA_TODOS", "SUMA_TRES", "SUMA_PONDERADA_TRES" };

    public static final String NOM_URL = "https://poliformat.upv.es/x/j5uDOn";

    protected Pixel[][] pixels;  // matriz de pixels
    protected int alto, ancho;   // dimension de la imagen
    protected int valorHash;     // valor de hash que se calculara con
                                 // alguna de las 3 funciones que se contemplan

    /** Crea una imagen de dimension alto x ancho todavia sin pixels
     *  @param alto, el numero de filas de la imagen
     *  @param ancho, el numero de columnas de la imagen
     */
    protected Imagen(int numF, int numC) {
        this.alto = numF;
        this.ancho = numC;
        pixels = new Pixel[alto][ancho];
        valorHash = 0;
    }

    /** Crea una imagen de dimension alto x ancho cuyos pixeles se leen del
     *  fichero in. Se utiliza como funcion de dispersion fhash.
     *  Para cada pixel se guarda la componente roja, verde y azul
     *  (1 byte sin signo por cada una)
     *  @param in el fichero de imagenes
     *  @param numF, el numero de filas de la imagen
     *  @param numC, el numero de columnas de la imagen
     *  @param fhash, funcion de dispersion a utilizar:
     *  SUMA_TODOS, SUMA_PONDERADA_TODOS, SUMA_TRES, SUMA_PONDERADA_TRES
     */
    public Imagen(DataInputStream in, int numF, int numC, int fhash) {
        this(numF, numC);
        try {
            for (int i = 0; i < alto; i++) {
                for (int j = 0; j < ancho; j++) {
                    pixels[i][j] = new Pixel(in.readUnsignedByte(),
                             in.readUnsignedByte(), in.readUnsignedByte());
                }
            }
            valorHash = obtieneValorHash(fhash);
        } catch (IOException e) {
            System.err.println("Problemas con la lectura de fichero");
            e.printStackTrace();
        }
    }

    /** Crea una imagen de dimension alto x ancho cuyos pixeles se generan
    *  de forma aleatoria. Se utiliza como funcion de dispersion fhash.
    *  @param alto, el numero de filas de la imagen
    *  @param ancho, el numero de columnas de la imagen
    *  @param fhash, funcion de dispersion a utilizar:
    *  SUMA_TODOS, SUMA_PONDERADA_TODOS, SUMA_TRES, SUMA_PONDERADA_TRES
    */
    public Imagen(int numF, int numC, int fhash) {
        this(numF, numC);
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                pixels[i][j] = new Pixel((int) (Math.random() * Pixel.BASE),
                    (int) (Math.random() * Pixel.BASE),
                    (int) (Math.random() * Pixel.BASE));
            }
        }
        valorHash = obtieneValorHash(fhash);
    }

    /** Devuelve la altura de la imagen
     * @return int, el numero de filas de la imagen
     */
    public final int getAlto() { return alto; }

    /** Devuelve la anchura de la imagen
     * @return int, el numero de columnas de la imagen
     */
    public final int getAncho() { return ancho; }

    /** Devuelve el pixel que ocupa la posicion (x,y)
     * @param x, fila
     * @param y, columna
     * @return Pixel, el que ocupa la celda (x,y)
     */
    public Pixel getPixel(int x, int y) { return pixels[x][y]; }

    /** Devuelve true si img es una Imagen igual que this,
     *  false en caso contrario
     *  @param img, Imagen a comparar con esta
     *  @return boolean, true si this Imagen es igual a img
     *  y false en caso contrario
     */
    public boolean equals(Object img) {
        if (img instanceof Imagen) {
            Imagen der = (Imagen) img;
            if (ancho != der.ancho || alto != der.alto) {
                return false;
            }
            if (this.hashCode() != der.hashCode()) {
                return false;
            }
            for (int i = 0; i < alto; i++) {
                for (int j = 0; j < ancho; j++) {
                    if (!pixels[i][j].equals(der.pixels[i][j])) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /** Devuelve el valor de hashing de la imagen.
     *  @return int, valor de dispersion
     */
    public int hashCode() { return this.valorHash; }

    // Calcula el valor de hash de acuerdo a la funcion de dispersion fHash
    protected int obtieneValorHash(int fHash) {
        int res = 0;
        switch (fHash) {
            case SUMA_TODOS:
                res = this.valorHashSumaTodos(); break;
            case SUMA_PONDERADA_TODOS:
                res = this.valorHashSumaPonderadaTodos();
                break;
            case SUMA_TRES:
                res = this.valorHashSuma3(); break;
            case SUMA_PONDERADA_TRES:
                res = this.valorHashSumaPonderada3(); break;
            default:
                res = 0; break;
        }
        return res;
    }

    // Calcula el valor de hashing haciendo la suma de todos los pixeles
    // de la imagen
    private int valorHashSumaTodos() {
        int res = 0;
        for (Pixel[] row : pixels) {
            for (Pixel pixel : row) {
                res += pixel.hashCode();
            }
        }
        return res;
    }

    // Calcula el valor de hashing haciendo la suma ponderada de todos
    // los pixeles de la imagen, tomando como base 255
    private int valorHashSumaPonderadaTodos() {
        int res = 0;
        for (int fila = 0; fila < pixels.length; fila++) {
            for (int col = 0; col < pixels[fila].length; col++) {
                res *= Pixel.BASE;
                res += pixels[fila][col].hashCode();
            }
        }
        return res;
    }

    // Calcula el valor de hashing haciendo la suma de los pixeles de la
    // submatriz central 3x3
    private int valorHashSuma3() {
        int res = 0;
        int filaCentral = getAlto() / 2;
        int colCentral = getAncho() / 2;
        for (int fila = filaCentral - 1; fila <= filaCentral + 1; fila++) {
            for (int col = colCentral - 1; col <= colCentral + 1; col++) {
                res += pixels[fila][col].hashCode();
            }
        }
        return res;
    }

    // Calcula el valor de hashing haciendo la suma ponderada de los pixeles
    // de la submatriz central 3x3 , tomando como base 255
    private int valorHashSumaPonderada3() {
        int res = 0;
        int filaCentral = getAlto() / 2;
        int colCentral = getAncho() / 2;
        for (int fila = filaCentral - 1; fila <= filaCentral + 1; fila++) {
            for (int col = colCentral - 1; col <= colCentral + 1; col++) {
                res *= Pixel.BASE;
                res += pixels[fila][col].hashCode();
            }
        }
        return res;
    }
}