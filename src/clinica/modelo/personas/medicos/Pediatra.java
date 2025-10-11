package clinica.modelo.personas.medicos;

/** Especialidad de la calse medico. */
public class Pediatra extends Medico {

	public Pediatra(String dni,String N_A,String domicilio,String ciudad,int NMatricula,String telefono)
	{
		super(dni,N_A,domicilio,ciudad,NMatricula,telefono);
	}
    
    /** Se calcula el honorario correspondiente. */
    @Override
    public double honorario() {
        return this.base * 1.07;
    }

    @java.lang.Override
    public java.lang.String getEspecialidad() { return "Pediatra"; }
}
