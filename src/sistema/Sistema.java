package sistema;
import salaespera.AtencionPacientes;
import gestionpaciente.Paciente;
import java.util.ArrayList;
public class Sistema {
	
	ArrayList<Medico> medicos=new ArrayList<>();
	ArrayList<Paciente> listapacientes=new ArrayList<>();
	//ArrayList<Paciente> patio=new ArrayList<>();
	AtencionPacientes modAtencionPacientes;
	
	public boolean pacienteEnSistema(Paciente p1) {
		//recorro araylist y verifico si esta
	}
	
	public void ingresaPaciente(Paciente p1) {
		if (pacienteEnSistema(p1)) {
			modAtencionPacientes.ingresaPaciente(p1);
		}
	}
	
	public Sistema() {
		
	}

}
