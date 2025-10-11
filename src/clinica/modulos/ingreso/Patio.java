package clinica.modulos.ingreso;

import java.util.ArrayList;
import java.util.Queue;

import clinica.modelo.personas.pacientes.Paciente;

/** Representa el patio donde esperan la mayoria de los pacientes. */
public class Patio {
    //deberiamos usar una coleccion
    private Queue<Paciente> patio;
	
	/** Agrega un paciente al patio. */
	public void appendPaciente(Paciente p)
    {
		patio.add(p);
	}
	
	/** Saca a un paciente del patio. */
	public void removePaciente(Paciente p)
    {
		this.patio.remove(p);
	}
	
	/** Comprueba si el paciente se encuentra en el patio. */
	public boolean isInPatio(Paciente p)
    {
		if(this.patio.contains(p))
            return true;
        else
            return false;
	}

}
