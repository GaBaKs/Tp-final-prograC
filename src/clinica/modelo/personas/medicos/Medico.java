package clinica.modelo.personas.medicos;

import clinica.modelo.personas.Persona;
import clinica.modelo.personas.pacientes.Paciente;

import java.util.ArrayList;


/**La clase Medico contiene los atributos comunes de los medicos(sueldo base y numero de matricula), es abstracta por que se tiene que especializar(extenderse).*/
public abstract class Medico extends Persona implements IMedico {
	private int NMatricula;
	private double base=20000;

	public Medico(String dni,String N_A,String domicilio,String ciudad,int NMatricula,String telefono)
	{
        super(dni,N_A,domicilio,ciudad,telefono);
		this.NMatricula=NMatricula;
	}
    /**Metodo que devuelve la matricula del medico **/
    public int getNMatricula()
    {
        return NMatricula;
    }
    /**Metodo que modifica la matricula del medico **/
    public void setNMatricula(int NMatricula)
    {
        this.NMatricula = NMatricula;
    }
    /**Este metodo devuelve el sueldo minimo **/
    public double getBase()
    {
        return base;
    }

}
