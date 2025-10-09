package clinica.modulos.ingreso;

import clinica.modelo.habitacion.Habitacion;
import clinica.modelo.internacion.Internacion;
import clinica.modelo.personas.pacientes.Paciente;

import java.util.LinkedList;
import java.util.Queue;
import java.time.LocalDate;

public class AtencionPacientes {
	private int cont=0;
	SalaEsperaPrivada salaesperaprivada;
	LinkedList<Paciente> patio;
    private Internacion internacion;
    private Paciente paciente;
    private Queue<Paciente> listaEspera;// Solo puede haber un paciente// Pacientes en espera afuera

    public void AtencionPacientes() {
        listaEspera = new LinkedList<Paciente>();
        patio = new LinkedList<Paciente>();
        salaesperaprivada = null;
    }


    public int numeroOrden() {
		cont++;
	    return cont;
	}
	
	public void ingresaPaciente(Paciente p1){
            p1.setnumorden(numeroOrden());
            ingresaCola(p1);
	}

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

    public Queue<Paciente> getPacientesPatio() {
        return patio;
    }

    public Queue<Paciente> getListaEspera() {
        return listaEspera;
    }

    public void internar(Habitacion habitacion, LocalDate fechaIngreso, Paciente paciente) {
        this.internacion = new Internacion(habitacion, fechaIngreso, paciente);
    }

    public void egresar(LocalDate fechaEgreso) {
        if (internacion != null) {
            internacion.egresar(fechaEgreso);
        }
    }

    public Internacion getInternacion() { return internacion; }




}
