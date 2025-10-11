package clinica.modulos.ingreso;

import clinica.modelo.habitacion.Habitacion;
import clinica.modelo.internacion.Internacion;
import clinica.modelo.personas.pacientes.Paciente;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.time.LocalDate;

/** Representa el ingreso de los pacientes a la lista de espera. */
public class IngresoPacientes {
	/** Contador que le dara valor unico al numero de orden. */
    private int cont;
    /** Representa la sala de espera privada, solo puede haber un paciente y si no hay nadie queda en @code null. */
	SalaEsperaPrivada salaesperaprivada;
	/** Lista que representa el patio donde esperan los pacientes a ser atendidos. */
    LinkedList<Paciente> patio;
    /** Interna al paciente si hace falta. */
    private Internacion internacion;
    /** Representa al paciente. */
    private Paciente paciente;
    /** Lista con los pacientes que se encuentran en espera a ser atendidos. */
    public LinkedList<Paciente> listaEspera;

    /** Calcula el numero de orden. */
    public int numeroOrden() {
		cont++;
	    return cont;
	}

    /** Se ingresa al paciente en la lista de espera dandole un numero de orden. */
	public void ingresaPaciente(Paciente p1){   // ingresa paciente a la lista de espera
            p1.setnumorden(numeroOrden());
            ingresaCola(p1);
	}

    /** Se le asigna al paciente un lugar en la sala de espera privada o en el patio. */
    public void ingresaCola(Paciente p1) {
		if (salaesperaprivada.salaLibre())
			salaesperaprivada.setPaciente(p1);

		else {

            Paciente actual = salaesperaprivada.getPaciente();
            Paciente ganador = p1.decidirLugar(actual);
            Paciente perdedor = (ganador == p1) ? actual : p1;

            if (ganador!=actual)
                salaesperaprivada.setPaciente(ganador);
            patio.addLast(perdedor);

		}
	}


    /** Interna al paciente asignando la habitacion correspondiente. */
    public void internar(Habitacion habitacion, LocalDate fechaIngreso, Paciente paciente) {
        this.internacion = new Internacion(habitacion, fechaIngreso, paciente);
    }

    /** Se le da el alta al paciente */
    public void egresar(LocalDate fechaEgreso) {
        if (internacion != null) {
            internacion.egresar(fechaEgreso);
        }
    }

    //no se que es esto
    public IngresoPacientes(){
        salaesperaprivada=null;
        patio=new LinkedList<>();
        //internacion ??
        listaEspera=new LinkedList<>();
        cont=0;
    }


}
