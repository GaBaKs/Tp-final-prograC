package clinica.modulos.ingreso;

import clinica.modelo.habitacion.Habitacion;
import clinica.modelo.personas.pacientes.Paciente;
import clinica.modulos.ProcesoPacientes;

import java.util.LinkedList;
import java.time.LocalDate;

/** Representa el ingreso de los pacientes a la lista de espera. */
public class IngresoPacientes
{

    ProcesoPacientes modProceso;

	/** Contador que le dara valor unico al numero de orden. */
    private int cont;


    public IngresoPacientes(ProcesoPacientes proceso)
    {
        modProceso=proceso;
    }


    /** Calcula el numero de orden. */
    public int numeroOrden()
    {
		cont++;
	    return cont;
	}

    /** Se ingresa al paciente en la lista de espera dandole un numero de orden.
     *
     * @param p1
     */
	public void ingresaPaciente(Paciente p1)
    {   // ingresa paciente a la lista de espera

            int aux=numeroOrden();
            p1.setnumorden(aux);
            ingresaCola(p1);
	}

    /** Se le asigna al paciente un lugar en la sala de espera privada o en el patio, ademas de agregarlo en la lista de espera.
     * @param p1
     */
    public void ingresaCola(Paciente p1)
    {
		if (modProceso.salaesperaprivada.salaLibre())
			modProceso.salaesperaprivada.setPaciente(p1);
		else {
            Paciente actual = modProceso.salaesperaprivada.getPaciente();
            Paciente ganador = p1.decidirLugar(actual);
            Paciente perdedor = (ganador == p1) ? actual : p1;

            if (ganador!=actual)
                modProceso.salaesperaprivada.setPaciente(ganador);
            modProceso.patio.agregaPaciente(perdedor);

		}
	}

}
