package clinica.modelo.personas.medicos;

/** Especialidad de la calse medico.*/
public class Pediatra extends Medico {

	public Pediatra(String dni,String N_A,String domicilio,String ciudad,int NMatricula,String telefono)
	{
		super(dni,N_A,domicilio,ciudad,NMatricula,telefono);
	}
    
    /** Se calcula el honorario correspondiente.
     * Siempre se va a devolver un valor >=0
     * */
    @Override
    public double honorario()
    {
        return this.getBase() * 1.07;
    }

    /** Devuelve la especialidad del medico.*/
    @java.lang.Override
    public java.lang.String getEspecialidad()
    {
        return "Pediatra";
    }

    /** Metodo que te da el nombre del medico.*/
    public String getNombre()
    {
        return  this.getN_A();
    }
}
