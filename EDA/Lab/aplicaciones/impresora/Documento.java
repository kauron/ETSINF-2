package aplicaciones.impresora;

/** Clase Documento que representa un documento a imprimir.
 * ATRIBUTOS: 
 *      TIENE UN titulo (String)
 *      TIENE UN numero de paginas (int)
 *      TIENE UN instante de envio a imprimir en segundos (int)
 * @author (profesores EDA 15-16)
 * @version (04 2016)
 **/

// COMPLETAR
 
public class Documento {
    private String titulo;
    private int numPaginas;
    private int envio;
    
    /** Crea un nuevo documento
     * @param titulo        Titulo del documento
     * @param numPaginas    Numero de paginas del documento
     * @param envio         Instante de envio a impresion (en seg.)
     */
    public Documento(String titulo, int numPaginas, int envio) {
        this.titulo = titulo;
        this.numPaginas = numPaginas;
        this.envio = envio;
    }
    
    /** Consultor del titulo del documento
     * @return Titulo del documento (String)
     */
    public String getTitulo() { return titulo; }
    
    /** Consultor del numero de paginas del documento
     * @return Numero de paginas del documento (int)
     */
    public int getNumPaginas() { return numPaginas; }
    
    /** Consultor del instante de envio a impresion del documento
     * @return Instante de impresion en seg. del documento (int)
     */
    public int getEnvio() { return envio; }
    
    /** Devuelve una descripcion (cadena de texto) del documento
     * @return Descripcion del documento (String)
     */
    public String toString() {
        return titulo + " (" + numPaginas + " pag.) Envio: " + envio;
    }
}
