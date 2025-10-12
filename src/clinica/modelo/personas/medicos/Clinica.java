package clinica.modelo.personas.medicos;

/** Especialidad de la clase medico. */
public class Clinica extends Medico {

	public Clinica(String dni,String N_A,String domicilio,String ciudad,int NMatricula,String telefono)
	{
		super(dni,N_A,domicilio,ciudad,NMatricula,telefono);
	}

	/** Calcula el honorario correspondiente. */
	@Override
	public double honorario()
    {
        return this.getBase() * 1.05;
    }

    /** Devuelve la especialidad del medico. */
    @java.lang.Override
    public java.lang.String getEspecialidad()
    {
        return "Clinica";
    }

    /** Metodo que te da el nombre del medico.*/
    public String getNombre()
    {
        return  this.getN_A();
    }
}
