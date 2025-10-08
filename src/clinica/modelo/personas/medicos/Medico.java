package clinica.modelo.personas.medicos;

import clinica.modelo.personas.Persona;

public abstract class Medico extends Persona implements IMedico {
	int NMatricula;
	double base=20000;
    protected String especialidad;
	
	
	public Medico(String dni,String N_A,String domicilio,String ciudad,int NMatricula,String telefono,String especialidad)
	{
        super(dni,N_A,domicilio,ciudad,telefono);
		this.NMatricula=NMatricula;
        this.especialidad = especialidad;
	}

    public int getNMatricula() {
        return NMatricula;
    }

    public void setNMatricula(int NMatricula) {
        this.NMatricula = NMatricula;
    }

    public double getBase() {
        return base;
    }
    public String getEspecialidad() {
        return especialidad;
    }
}
