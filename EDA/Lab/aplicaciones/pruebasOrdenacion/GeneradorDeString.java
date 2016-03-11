package aplicaciones.pruebasOrdenacion;

// comprueba que esta en el lugar correcto
// package aplicaciones.pruebasOrdenacion;


/**
 * GeneradorDeString genera String con un numero dado  
 * de caracteres iniciales iguales.
 * 
 *  @author Profesores de EDA, ETSInf, Grado Ing. Informatica
 *  @version Curso 2015-2016
 */

public class GeneradorDeString {
    
    private int numIg;   // Numero de caracteres iniciales iguales 
    private String base; // SubString inicial comun a los generados
    
    /**
     * Crea un GeneradorDeString cuyos n primeros caracteres 
     * son iguales
     */
    public GeneradorDeString(int n) {
        this.numIg = n;
        String s = "";
        int desAlea = (int) (Math.random() * 10);
        for (int i = 0; i < numIg; i++) {
            s += (char) ((i + desAlea) % 256 + (int) '0'); 
        }
        this.base = s;
   
    }

    /**
     * Devuelve un String con sus n primeros caracteres
     * identicos, aunque elegidos al azar
     */
    public String generar() {
        String s = "";   
        for (int i = 1; i <= (int) (Math.random() * 2 * numIg); i++) {
            s += (char) ((int) (Math.random() * 128) + (int) '0');
        }
        return this.base + s;
    }
}
