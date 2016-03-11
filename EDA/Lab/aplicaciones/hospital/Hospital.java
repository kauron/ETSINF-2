package aplicaciones.hospital;

// comprueba que lo has puesto en el lugar correcto
// package aplicaciones.hospital;

/**
 * La clase Hospital consiste, basicamente, en un array de camas c
 * on sus pacientes asignados. 
 *        
 * @version Febrero 2014
 */

public class Hospital {

    private Paciente[] camas;    // Camas del hospital
    private int ingresados;      // Numero total de pacientes que ingresaron
    private int altas;           // Numero total de pacientes que se curaron
    private int fallecidos;      // Numero total de pacientes que fallecieron
    private int ocupacionMaxima; // Maximo numero de camas ocupadas a la vez
    private int ocupadas;        // Numero de camas ocupadas actualmente

    /** Constructor de un Hospital
     *  @param capacidad Numero de camas disponibles en un Hospital
     */
    public Hospital(int capacidad) {
        camas = new Paciente[capacidad];
        ingresados = altas = fallecidos = ocupacionMaxima = ocupadas = 0;
    }
    
    /**
     * Comprueba si hay camas libres en un Hospital
     * @return true si hay camas libres, false en caso contrario
     */
    public boolean hayCamasLibres() {        
        return (camas.length - ocupadas) > 0;
    }
    
    /**
     * SII hayCamasLibres(): ingresa al paciente p en la primera cama 
     * libre de un Hospital.
     * 
     * @param p Paciente a ingresar
     */
    public void ingresarPaciente(Paciente p) {
        ingresados++;
        ocupadas++;
        if (ocupadas > ocupacionMaxima) { ocupacionMaxima = ocupadas; }
        for (int i = 0; i < camas.length; i++) {
            if (camas[i] == null || camas[i].getEstado() == Paciente.FALLECIDO)
            { camas[i] = p; break; }
        }
    }
    
    /**
     * Aplica una cura a un paciente de un Hospital. Con un 95% de 
     * probabilidades el paciente mejora un grado, mientras que un 5% 
     * de las veces el paciente no responde al tratamiento y fallece.
     * Tanto si el paciente se cura como si fallece deja libre la cama 
     * que estaba ocupando.
     * 
     * @param p Paciente al que aplicar la cura
     */
    public void aplicarTratamiento(Paciente p) {   
        p.aplicarCura();
        if (p.getEstado() == Paciente.FALLECIDO 
            || p.getEstado() == Paciente.SANO) {
            if (p.getEstado() == Paciente.SANO) {
                altas++;
                for (int i = 0; i < camas.length; i++) {
                    if (camas[i] == p) { 
                        camas[i] = null; 
                        break; 
                    }
                }
            } 
	        else { fallecidos++; }
            ocupadas--;            
        }
    }

    /**
     * Consultor del numero de pacientes ingresados en un Hospital.
     * 
     * @return int numero total de pacientes que han sido ingresados 
     * en un Hospital.
     */
    public int pacientesIngresados() {
        return ingresados;
    }

    /**
     * Consultor del numero de pacientes curados en un Hospital
     * 
     * @return int numero total de pacientes que han sido curados 
     * en un Hospital.
     */
    public int pacientesCurados() {
        return altas;
    }
    
    /**
     * Consultor del numero de pacientes fallecidos en un Hospital
     * 
     * @return int numero total de pacientes que han fallecido 
     * en un Hospital.
     */
    public int pacientesFallecidos() {
        return fallecidos;
    }
    
    /**
     * Consultor de la saturacion maxima en un Hospital.
     * @return double porcentaje maximo de la capacidad de un Hospital 
     * que ha sido utilizada en algun instante.
     */
    public double saturacion() {
        return ocupacionMaxima / (double) camas.length;
    }

    /**
     * Devuelve el array que representa las camas de un Hospital.
     * @return Paciente[] array de camas de un Hospital
     */
    public Paciente[] toArray() {        
        return camas;
    }
}
