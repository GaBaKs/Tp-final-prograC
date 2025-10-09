package clinica.patrones.facade;
import clinica.modelo.personas.medicos.Medico;
import clinica.modulos.ingreso.IngresoPacientes;
import clinica.modelo.personas.pacientes.Paciente;
import java.util.ArrayList;
import excepciones.*;

public class Sistema {
	
	ArrayList<Medico> medicos;
	IngresoPacientes modIngresoPacientes;
	ArrayList <Paciente> pacientesistema= new ArrayList<>();


	public void registaPaciente(Paciente p1){
        pacientesistema.add(p1);
    }
	
	public void ingresaPaciente(Paciente p1) throws NoEstaEnSistemaException{
        if (pacientesistema.contains(p1))
            modIngresoPacientes.ingresaPaciente(p1);
        else
            throw new NoEstaEnSistemaException("El paciente ingresado no esta en el sistema.");
	}
	
	public Sistema() {
        medicos=new ArrayList<>();
        modIngresoPacientes =new IngresoPacientes();
	}

}
