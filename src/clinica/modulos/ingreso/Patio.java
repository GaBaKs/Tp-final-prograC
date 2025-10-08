package clinica.modulos.ingreso;

import java.util.ArrayList;
import clinica.modelo.pacientes.Paciente;


public class Patio {

	private ArrayList<Paciente> patio= new ArrayList<>();
	
	
	
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
