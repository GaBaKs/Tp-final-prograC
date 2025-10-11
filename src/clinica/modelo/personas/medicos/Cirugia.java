package clinica.modelo.personas.medicos;

/** Especialidad de la calse medico. */
public class Cirugia extends Medico {

	public Cirugia(String dni,String N_A,String domicilio,String ciudad,int NMatricula,String telefono)
	{
		super(dni,N_A,domicilio,ciudad,NMatricula,telefono);
	}

    /** Calcula el honorario correspondiente. */
    @Override
    public double honorario() { return this.base * 1.10 ; }

    @Override
    public String getEspecialidad() { return "Cirugia" ; }
}
