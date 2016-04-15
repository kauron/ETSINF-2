package aplicaciones.letras;

import librerias.estructurasDeDatos.deDispersion.TablaHash;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Estudia la complejidad empirica de la busqueda en una Tabla Hash.
 * 
 * Se estudian las tallas....
 * Se utiliza como funcion de dispersion....
 * 
 * @author (EDA) 
 * @version (Marzo 2016)
 */
public class EvaluaCosteBusqueda {

    // Tallas para la medida
    private static int TALLA_INI = 1000;
    private static int TALLA_FIN = 10000;
    private static int TALLA_INCR = 1000;

    private static final int NUM_IMGS = 22000;

  
    public static void main(String args[]) {
        try {
           String nomF = "aplicaciones" + File.separatorChar + "letras"
                + File.separatorChar + "res" + File.separatorChar 
                + "costeBusqueda.txt";
           PrintWriter pw = new PrintWriter(new File(nomF));
           int fdis = Imagen.SUMA_TODOS;
           Imagen[] imgs = cargarImagenes(fdis);
           
           long avgTime = 0;
           
           
           for (int t = TALLA_INI; t <= TALLA_FIN; t += TALLA_INCR) {
               TablaHash<Imagen, String> tabla = new TablaHash<Imagen, String>(t);
               for (int i = 0; i < t; i++) tabla.insertar(imgs[i], "");

               long initTime = System.nanoTime();
               
             
        
             
           }
           /** COMPLETAR */
           
           pw.close();
        } catch (IOException e) {
            System.out.println("Problemas al abrir el fichero");
        }
     }
       
     private static Imagen[] cargarImagenes(int fdis) throws IOException {
         Imagen[] imgs = new Imagen[NUM_IMGS];
         URL url = new URL(Imagen.NOM_URL);
         URLConnection con = url.openConnection();
         DataInputStream is = new DataInputStream(con.getInputStream());

         for (int i = 0; i < NUM_IMGS; i++) {
            Imagen img = new Imagen(is, 11, 13, fdis);
            char letra = (char) is.readUnsignedByte();
            int estilo = is.readUnsignedByte();
            int color = is.readUnsignedByte();
            imgs[i] = img;
        }
        return imgs;
     }
       
    
}
  