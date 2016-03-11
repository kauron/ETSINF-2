package aplicaciones.hospital;

// comprueba que lo has puesto en el lugar correcto
// package aplicaciones.hospital;

import librerias.estructurasDeDatos.modelos.ColaPrioridad;
import librerias.estructurasDeDatos.lineales.LPIColaPrioridad;

//Alternativamente: 
//import librerias.estructurasDeDatos.jerarquicos.PriorityQColaPrioridad;

/**
 *  
 * La clase Urgencias representa la sala de espera de urgencias de un hospital; 
 * por ello usa una Cola de Prioridad para gestionar la atencion a los pacientes
 * 
 * @version Febrero 2014 
 */

public class Urgencias {
    private ColaPrioridad<Paciente> cp; // Cola de pacientes en urgencias
    private int llegados;               // Num. de pacientes llegados
    private int atendidos;              // Num. de pacientes atendidos
    private int tpoEspera;              // Tiempo total de espera en urgencias
    private Paciente[] sillas;          // Sillas de la sala de espera
    
    /**
     * Constructor de una sala de urgencias
     * @param capacidad Numero de sillas en una sala de urgencias
     */
    public Urgencias(int capacidad) {
        cp = new LPIColaPrioridad<Paciente>(); 
        //Alternativamente: 
        //cp = new PriorityQColaPrioridad<Paciente>();
        sillas = new Paciente[capacidad];
        llegados = atendidos = tpoEspera = 0;
    }
    
    /**
     * Comprueba si quedan pacientes esperando en una sala de urgencias
     * @return true si hay pacientes en espera, false en caso contrario
     */
    public boolean hayPacientesEnEspera() {
        return !cp.esVacia();
    }
    
    /**
     * SII hayPacientesEnEspera(): devuelve el paciente mas grave y lo 
     * elimina de una sala de urgencias.
     * 
     * @param  tiempo  instante de tiempo en el que se llama a un paciente 
     * de una sala de urgencias.
     * @return Paciente con mayor prioridad de la sala de espera.
     */
    public Paciente llamarAlPacienteMasGrave(long tiempo) {
        Paciente p = cp.eliminarMin();
        for (int i = 0; i < sillas.length; i++) {
            if (sillas[i] == p) { sillas[i] = null; break; }
        }
        atendidos++;
        tpoEspera += tiempo - p.getTpoLlegadaUrgencias();
        return p;
    }
    
    /**
     * SII hayPacientesEnEspera(): consulta el paciente mas grave de urgencias.
     * 
     * @return  Paciente con mayor prioridad de la sala de espera.
     */
    public Paciente pacienteMasGrave() {
        return cp.recuperarMin();
    }
    
    /**
     * Anyade al paciente p a una sala de urgencias.
     * 
     * @param p Paciente que llega a una sala de urgencias
     */
    public void llegadaDePaciente(Paciente p) {
        for (int i = 0; i < sillas.length; i++) {
            if (sillas[i] == null) { sillas[i] = p; break; }
        }
        cp.insertar(p);
        llegados++;
    }
    
    /**
     * Consultor del numero de pacientes que han llegado a urgencias.
     * 
     * @return int numero total de pacientes que han llegado a urgencias
     */
    public int pacientesLlegados() {
        return llegados;
    }
    
    /**
     * Consultor del numero de pacientes atendidos en urgencias.
     * 
     * @return int numero total de pacientes que han sido atendidos 
     * en urgencias.
     */
    public int pacientesAtendidos() {
        return atendidos;
    }
    
    /**
     * Consultor del tiempo medio de espera en una sala de urgencias
     * @return double tiempo medio de espera (en minutos) en una sala de urgencias
     */
    public double tiempoMedioEspera() {
        return tpoEspera / (double) atendidos;
    }
    
    /**
     * Devuelve el array que representa las sillas de una sala de urgencias
     * @return Paciente[] array de sillas de la sala de una sala de urgencias
     */
    public Paciente[] toArray() {        
        return sillas;
    }
}