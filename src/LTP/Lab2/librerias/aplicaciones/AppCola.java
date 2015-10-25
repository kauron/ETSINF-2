package LTP.Lab2.librerias.aplicaciones;

import LTP.Lab2.librerias.implementacionesDeModelos.*;
import LTP.Lab2.librerias.modelos.*;

/**
 * @author (Professors LTPP) 
 * @version (curs 2015-16)
 */

public class AppCola {
    public static void main (String args[])throws Exception {
        Cola<Integer> c = new ColaAL <Integer>();
        for (int i = 1; i <= 15;i++)
            c.encolar(i);
        System.out.println("Cola inicial:\n"+c);
        int cantidadEliminada = 10;
        for (int i = 0; i <cantidadEliminada ;i++)
            c.desencolar();
        System.out.println("Cola despues de desencolar "+cantidadEliminada+":\n"+c);
        int cantidadEncolada = 25;
        for (int i = 1; i <=cantidadEncolada;i++)
            c.encolar(i);
        System.out.println("Cola despues de encolar "+cantidadEncolada+":\n"+c);
    }
}
