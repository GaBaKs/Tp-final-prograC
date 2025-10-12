package clinica.modelo.personas.medicos;

/** Especialidad de la clase medico. */
public class Cirugia extends Medico {

	public Cirugia(String dni,String N_A,String domicilio,String ciudad,int NMatricula,String telefono)
	{
		super(dni,N_A,domicilio,ciudad,NMatricula,telefono);
	}

    /** Calcula el honorario correspondiente. */
    @Override
    public double honorario()
    {
        return this.getBase() * 1.10 ;
    }

    /** Devuelve la especialidad del medico. */
    @Override
    public String getEspecialidad()
    {
        return "Cirugia" ;
    }

    /** Metodo que te da el nombre del medico.*/
    public String getNombre()
    {
        return  this.getN_A();
    }
}
