package clinica.patrones.facade;
import clinica.modelo.personas.medicos.IMedico;
import clinica.modelo.personas.medicos.Medico;
import clinica.modulos.ingreso.IngresoPacientes;
import clinica.modelo.personas.pacientes.Paciente;
import java.util.ArrayList;
import excepciones.*;

public class Sistema {

    ArrayList<IMedico> medicos;
    IngresoPacientes modIngresoPacientes;
    ArrayList <Paciente> pacientesistema= new ArrayList<>();

	public void registraPaciente(Paciente p1) throws DuplicadoException {    // ingresa al sistema
       if (pacientesistema.contains(p1))
           throw new DuplicadoException("Paciente ya existe");
       else
        pacientesistema.add(p1);
    }
	
	public void ingresaPaciente(Paciente p1) throws NoEstaEnSistemaException{   // si ya esta en el sistema, lo mete a la lista de espera
        if (pacientesistema.contains(p1))
            modIngresoPacientes.ingresaPaciente(p1);
        else
            throw new NoEstaEnSistemaException("El paciente ingresado no esta en el sistema.");
	}

    public void registraMedico(IMedico p1) throws DuplicadoException{
        if (medicos.contains(p1))
            throw new DuplicadoException("Medico duplicado");
        else
            medicos.add(p1);
    }

    public void atiendePaciente(Medico m1,Paciente p1) throws NoEstaEnSistemaException{
        if (!(medicos.contains(m1)))
            throw new NoEstaEnSistemaException("Medico no disponible");
        else
            if (modIngresoPacientes.listaEspera.contains(p1)){
              p1.agregaMedicoAtendido(m1);    // agrego a la lista de medicos que lo atendio
              m1.agregaPacienteAtendido(p1);
             }
            else
                 throw new NoEstaEnSistemaException("El paciente no se encuentra en la lista de espera");
    }

    public void egresaPaciente(Paciente p1){

    }


	public Sistema() {
        medicos=new ArrayList<>();
        modIngresoPacientes =new IngresoPacientes();
	}

}
