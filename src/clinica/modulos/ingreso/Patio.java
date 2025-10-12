package clinica.modulos.ingreso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import clinica.modelo.personas.pacientes.Paciente;

/** Representa el patio donde esperan la mayoria de los pacientes. */
public class Patio {
    /**Lista que contiene a los pacientes que se encuentran en el patio.*/
    private LinkedList<Paciente> patio;

    public Patio(){
        patio=new LinkedList<>();
    }

	/** Agrega un paciente al patio. */
	public void agregaPaciente(Paciente p)
    {
		patio.addLast(p);
	}
	
	/** Saca a un paciente del patio. */
	public void sacaPaciente(Paciente p)
    {
		this.patio.remove(p);
	}
	
	/** Comprueba si el paciente se encuentra en el patio. */
	public boolean estaEnPatio(Paciente p)
    {
		if(this.patio.contains(p))
            return true;
        else
            return false;
	}

}
