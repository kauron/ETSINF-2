/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  aplicaciones.hospital.Paciente
 *  librerias.estructurasDeDatos.jerarquicos.PriorityQColaPrioridad
 *  librerias.estructurasDeDatos.lineales.LPIColaPrioridad
 */
package aplicaciones.hospital;

import librerias.estructurasDeDatos.jerarquicos.PriorityQColaPrioridad;
import librerias.estructurasDeDatos.lineales.LPIColaPrioridad;

public class TestUrgencias {
    private static int numErrores = 0;

    public static void main(String[] args) {
        numErrores = 0;
        System.out.println("APLICACION DE PRUEBA PARA LAS URGENCIAS DE UN HOSPITAL");
        System.out.println("-----------------------------------------------------------");
        System.out.println();
        TestUrgencias.testCompareToPaciente();
        Paciente[] v = new Paciente[]{new Paciente("Abel", 33, 3), new Paciente("Adolfo", 4, 2), new Paciente("Carolina", 2, 3), new Paciente("Benito", 64, 5), new Paciente("Blanca", 78, 3), new Paciente("Miguel", 75, 1), new Paciente("Diego", 6, 1), new Paciente("Maite", 31, 1), new Paciente("Diana", 82, 3), new Paciente("Baltasar", 70, 4), new Paciente("Bernardo", 2, 3)};
        TestUrgencias.testLPIColaPrioridad(v);
        TestUrgencias.testPriorityQColaPrioridad(v);
        if (numErrores == 0) {
            System.out.println("\nTODO CORRECTO\n");
        } else {
            System.out.println("\nERRORES DETECTADOS: " + numErrores + "\n");
        }
    }

    private static void testCompareToPaciente() {
        System.out.print("Comprobando el metodo compareTo de Paciente ...");
        Paciente a = new Paciente("A", 35, 2);
        Paciente b = new Paciente("B", 35, 2);
        Paciente c = new Paciente("C", 35, 1);
        Paciente d = new Paciente("D", 34, 2);
        Paciente e = new Paciente("E", 34, 1);
        Paciente f = new Paciente("F", 36, 3);
        boolean res = a.compareTo(b) == 0 && a.compareTo(c) > 0 && c.compareTo(a) < 0 && a.compareTo(d) == 0 && a.compareTo(e) > 0 && a.compareTo(f) < 0 && d.compareTo(a) == 0 && f.compareTo(a) > 0 && e.compareTo(a) < 0;
        a = new Paciente("A", 5, 2);
        b = new Paciente("B", 5, 2);
        c = new Paciente("C", 5, 1);
        d = new Paciente("D", 4, 2);
        e = new Paciente("E", 4, 1);
        f = new Paciente("F", 6, 3);
        res &= a.compareTo(b) == 0 && a.compareTo(c) > 0 && c.compareTo(a) < 0 && a.compareTo(d) > 0 && a.compareTo(e) > 0 && a.compareTo(f) < 0 && d.compareTo(a) < 0 && f.compareTo(a) > 0 && e.compareTo(a) < 0;
        a = new Paciente("A", 85, 2);
        b = new Paciente("B", 85, 2);
        c = new Paciente("C", 85, 1);
        d = new Paciente("D", 84, 2);
        e = new Paciente("E", 84, 1);
        f = new Paciente("F", 86, 3);
        res &= a.compareTo(b) == 0 && a.compareTo(c) > 0 && c.compareTo(a) < 0 && a.compareTo(d) < 0 && a.compareTo(e) > 0 && a.compareTo(f) < 0 && d.compareTo(a) > 0 && f.compareTo(a) > 0 && e.compareTo(a) < 0;
        a = new Paciente("A", 10, 2);
        b = new Paciente("B", 10, 1);
        c = new Paciente("C", 85, 1);
        d = new Paciente("D", 85, 2);
        e = new Paciente("E", 30, 1);
        f = new Paciente("F", 30, 2);
        TestUrgencias.mostrarResultado(res &= a.compareTo(c) > 0 && c.compareTo(a) < 0 && b.compareTo(c) < 0 && c.compareTo(b) > 0 && a.compareTo(e) > 0 && e.compareTo(a) < 0 && b.compareTo(e) < 0 && e.compareTo(b) > 0 && a.compareTo(d) < 0 && d.compareTo(a) > 0 && b.compareTo(d) < 0 && e.compareTo(b) > 0 && a.compareTo(f) < 0 && f.compareTo(a) > 0 && b.compareTo(f) < 0 && f.compareTo(b) > 0 && c.compareTo(e) < 0 && e.compareTo(c) > 0 && c.compareTo(f) < 0 && f.compareTo(c) > 0 && d.compareTo(e) > 0 && e.compareTo(d) < 0 && d.compareTo(f) < 0 && f.compareTo(d) > 0);
    }

    private static void testLPIColaPrioridad(Paciente[] v) {
        System.out.print("Comprobando la clase LPIColaPrioridad ... ");
        LPIColaPrioridad cp = new LPIColaPrioridad();
        boolean res = cp.esVacia();
        for (int i = 0; i < v.length; ++i) {
            cp.insertar((Comparable)v[i]);
        }
        res &= !cp.esVacia() && ((Paciente)cp.recuperarMin()).equals((Object)v[6]);
        TestUrgencias.mostrarResultado(res &= ((Paciente)cp.eliminarMin()).equals((Object)v[6]) && ((Paciente)cp.eliminarMin()).equals((Object)v[5]) && ((Paciente)cp.eliminarMin()).equals((Object)v[7]) && ((Paciente)cp.eliminarMin()).equals((Object)v[1]) && ((Paciente)cp.eliminarMin()).equals((Object)v[2]) && ((Paciente)cp.eliminarMin()).equals((Object)v[10]) && ((Paciente)cp.eliminarMin()).equals((Object)v[8]) && ((Paciente)cp.eliminarMin()).equals((Object)v[4]) && ((Paciente)cp.eliminarMin()).equals((Object)v[0]) && ((Paciente)cp.eliminarMin()).equals((Object)v[9]) && ((Paciente)cp.eliminarMin()).equals((Object)v[3]) && cp.esVacia());
    }

    private static void testPriorityQColaPrioridad(Paciente[] v) {
        System.out.print("Comprobando la clase PriorityQColaPrioridad ...");
        PriorityQColaPrioridad cp = new PriorityQColaPrioridad();
        boolean res = cp.esVacia();
        for (int i = 0; i < v.length; ++i) {
            cp.insertar((Comparable)v[i]);
        }
        res &= !cp.esVacia() && ((Paciente)cp.recuperarMin()).equals((Object)v[6]);
        TestUrgencias.mostrarResultado(res &= ((Paciente)cp.eliminarMin()).equals((Object)v[6]) && ((Paciente)cp.eliminarMin()).equals((Object)v[5]) && ((Paciente)cp.eliminarMin()).equals((Object)v[7]) && ((Paciente)cp.eliminarMin()).equals((Object)v[1]) && ((Paciente)cp.eliminarMin()).equals((Object)v[10]) && ((Paciente)cp.eliminarMin()).equals((Object)v[2]) && ((Paciente)cp.eliminarMin()).equals((Object)v[8]) && ((Paciente)cp.eliminarMin()).equals((Object)v[4]) && ((Paciente)cp.eliminarMin()).equals((Object)v[0]) && ((Paciente)cp.eliminarMin()).equals((Object)v[9]) && ((Paciente)cp.eliminarMin()).equals((Object)v[3]) && cp.esVacia());
    }

    public static void mostrarResultado(boolean res) {
        if (res) {
            System.out.println("\t...Comprobacion correcta");
        } else {
            System.out.println("\t... ERROR!!!");
            ++numErrores;
        }
    }
}