package clinica.modelo.medicos;

public class Pediatra extends Medico {

	public Pediatra(String dni,String N_A,String domicilio,String ciudad,int NMatricula,String telefono,String especialidad)
	{
		super(dni,N_A,domicilio,ciudad,NMatricula,telefono,especialidad);
	}

    @Override
    public double honorario() {
        return this.base * 1.07;
    }

}
