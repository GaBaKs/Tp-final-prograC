package clinica.modelo.personas.medicos;

import clinica.modelo.personas.Persona;
import clinica.modelo.personas.pacientes.Paciente;

import java.util.ArrayList;


/**La clase Medico contiene los atributos comunes de los medicos(sueldo base y numero de matricula), es abstracta por que se tiene que especializar(extenderse) */
public abstract class Medico extends Persona implements IMedico {
	int NMatricula;
	double base=20000;      // sueldo base de un medico
	ArrayList <Paciente> PacientesAtendidos=new ArrayList<>();
	
	public Medico(String dni,String N_A,String domicilio,String ciudad,int NMatricula,String telefono)
	{
        super(dni,N_A,domicilio,ciudad,telefono);       // llama al cosntructor de persona
		this.NMatricula=NMatricula;
	}

    public int getNMatricula() {
        return NMatricula;
    }

    public void setNMatricula(int NMatricula) {
        this.NMatricula = NMatricula;
    }

    public double getBase() {
        return base;
    }   // devuelve el sueldo minimo

    /** Agrega un {@link Paciente} a la lista de pacientes atendidos del medico correspondiente. */
    public void agregaPacienteAtendido(Paciente p1){
        PacientesAtendidos.add(p1);
    }

}
