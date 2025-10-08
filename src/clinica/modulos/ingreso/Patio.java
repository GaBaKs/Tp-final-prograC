package clinica.modulos.ingreso;

import java.util.ArrayList;
import java.util.Queue;

import clinica.modelo.personas.pacientes.Paciente;


public class Patio {
    //deberiamos usar una coleccion
    private Queue<Paciente> patio;
	
	
	public void appendPaciente(Paciente p)
    {
		patio.add(p);
	}
	
	public void removePaciente(Paciente p)
    {
		this.patio.remove(p);
	}
	
	
	public boolean isInPatio(Paciente p)
    {
		if(this.patio.contains(p))
            return true;
        else
            return false;
	}

}
