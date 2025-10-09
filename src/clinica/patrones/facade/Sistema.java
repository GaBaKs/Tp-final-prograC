package clinica.patrones.facade;
import clinica.modelo.personas.medicos.Medico;
import clinica.modulos.ingreso.AtencionPacientes;
import clinica.modelo.personas.pacientes.Paciente;
import java.util.ArrayList;
import excepciones.*;

public class Sistema {
	
	ArrayList<Medico> medicos=new ArrayList<>();
	//ArrayList<Paciente> patio=new ArrayList<>();
	AtencionPacientes modAtencionPacientes;
	ArrayList <Paciente> pacientesistema= new ArrayList<>();


	public void registaPaciente(Paciente p1){
        pacientesistema.add(p1);
    }
	
	public void ingresaPaciente(Paciente p1) throws NoEstaEnSistemaException{
        if (pacientesistema.contains(p1))
            modAtencionPacientes.ingresaPaciente(p1);
        else
            throw new NoEstaEnSistemaException("El paciente ingresado no esta en el sistema.");
	}
	
	public Sistema() {
		
	}

}
