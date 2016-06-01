package aplicaciones.impresora;

/** Clase SimuladorImpresion. Realiza una simulacion que compara 
 * el tiempo de espera medio de impresión utilizando una ImpresoraCola
 * o una ImpresoraCP.
 * @author (profesores EDA 15-16)
 * @version (04 2016)
 **/
 
public class SimuladorImpresion {
    // Array de titulos para los documentos usados en la simulacion
    private static String[] titulos = {"El hobbit - J.R.R. Tolkien", 
        "El nombre del viento - P. Rothfuss", 
        "Portico - F. Pohl",
        "Mundo Anillo - L. Niven",
        "Soy leyenda - R. Matheson",
        "Tropas del espacio - R.A. Heinlein",
        "El libro de los portales - L. Gallego",
        "Neverwhere - N. Gaiman",
        "Rescate en el tiempo - M. Crichton",
        "Mision de gravedad - H. Clement",
        "Cita con Rama - A.C. Clarke",
        "Inferno - D. Brown"
    };
    
    // Almacena el tiempo de espera medio de impresion
    private static double tiempoEspera;
    
    /** Convierte un double en un String usando dos decimales
     * @param n     Numero real a convertir
     * @return String con el valor de n
     */
    private static String doubleToString(double n) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        return df.format(n);
    }
    
    /** Genera un array de documentos de forma aleatoria
     * @return Array de documentos para la simulacion
     */
    private static Documento[] generarDocumentos() {
        Documento[] v = new Documento[titulos.length];
        java.util.Random rnd = new java.util.Random();
        int envio = 0;
        for (int i = 0; i < v.length; i++) {
            int numPag = rnd.nextInt(90) + 10;
            envio += rnd.nextInt(60);
            v[i] = new Documento(titulos[i], numPag, envio);
        }
        return v;
    }
    
    /** Simula la impresion del siguiente trabajo almacenado en
     * la impresora.
     * @param imp   Impresora utilizada
     * @param hora  Hora de inicio de la impresion (en seg.)
     * @return Duracion de la impresion del trabajo (en seg.)
     */
    private static int imprimirSiguienteTrabajo(Impresora imp, int hora) {
        Documento doc = imp.siguienteTrabajo();
        int duracion = imp.imprimirSiguiente();
        int horaFinImpresion = hora + duracion;
        double espera = horaFinImpresion - doc.getEnvio();
        System.out.println("[" + horaFinImpresion + "] " + doc.toString() 
            + " (" + doubleToString(espera) + " seg. de espera)");
        tiempoEspera += espera;
        return duracion;
    }
    
    /** Simula la impresion de los documentos contenidos en el array.
     * @param docs  Array de documentos
     * @param imp   Impresora utilizada
     * @return Tiempo de espera medio para la impresion
     */
    private static double simulacion(Documento[] docs, Impresora imp) {
        int hora = 0, i = 0;
        tiempoEspera = 0;
        while (i < docs.length) {
            if (imp.hayTrabajos()) {
                hora += imprimirSiguienteTrabajo(imp, hora);
            } else {
                hora = docs[i].getEnvio();
            }
            while (i < docs.length && docs[i].getEnvio() <= hora) {
                imp.guardarTrabajo(docs[i]);
                i++;
            }
        }
        while (imp.hayTrabajos()) {
            hora += imprimirSiguienteTrabajo(imp, hora);
        }
        return tiempoEspera / docs.length;
    }
    
    /** Comprueba la clase Documento
     * @return Boolean indicando si se supera el test
     */
    private static boolean testDocumento() {
        try {
            Documento d1 = new Documento("D1", 10, 0);
            Documento d2 = new Documento("D2", 50, 1);
            Documento d3 = new Documento("D3", 10, 2);
            if (d1.compareTo(d2) < 0 || d1.compareTo(d3) != 0 
                || d2.compareTo(d3) > 0) { 
                throw new Exception("Error en el metodo compareTo"); 
            }
        } catch (Exception e) {
            System.out.println("Error en la clase Documento: "
                + e.getMessage());
            return false;
        }
        return true;
    }

    /** Comprueba la clase ImpresoraCP
     * @return Boolean indicando si se supera el test
     */
    private static boolean testImpresora() {
        try {
            Documento[] docs = {new Documento("D1", 25, 0),
                new Documento("D2", 50, 1), new Documento("D3", 10, 2)};
            Documento[] res = {docs[2], docs[0], docs[1]};
            int[] duraciones = {20, 50, 100};
            ImpresoraCP imp = new ImpresoraCP();
            if (imp.hayTrabajos()) {
                throw new Exception("Error en el metodo hayTrabajos");
            }
            for (int i = 0; i < docs.length; i++) {
                imp.guardarTrabajo(docs[i]);
            }
            if (!imp.hayTrabajos()) {
                throw new Exception("Error en el metodo hayTrabajos");
            }
            for (int i = 0; i < res.length; i++) {
                if (imp.siguienteTrabajo() != res[i]) {
                    throw new Exception("Error en el metodo siguienteTrabajo");
                }
                int duracion = imp.imprimirSiguiente();
                if (duracion != duraciones[i]) {
                    throw new 
                        Exception("Error en el metodo imprimirSiguiente");    
                }
            }
        } catch (Exception e) {
            System.out.println("Error en la clase ImpresoraCP: "
                + e.getMessage());
            return false;
        }
        return true;
    }

    /** Programa que ejecuta la simulacion
     * @param args  Argumentos del programa
     */
    public static void main(String[] args) {
        if (!testDocumento()) { return; }
        if (!testImpresora()) { return; }
        Documento[] docs = generarDocumentos();
        double ts = simulacion(docs, new ImpresoraCola());
        System.out.println("SIMULACION CON COLA: Tiempo medio de espera = " 
            + doubleToString(ts) + " seg.");
        System.out.println();
        ts = simulacion(docs, new ImpresoraCP());
        System.out.println("SIMULACION CON COLA DE PRIORIDAD: "
            + "Tiempo medio de espera = " + doubleToString(ts) + " seg."); 
    }
}
