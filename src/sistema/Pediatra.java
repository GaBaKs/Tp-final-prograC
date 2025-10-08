import medico.Medico;

public class Pediatra extends Medico {

	public Pediatra(String dni,String N_A,String domicilio,String ciudad,int NMatricula,int telefono) 
	{
		super(dni,N_A,domicilio,ciudad,NMatricula,telefono);
	}

	@Override
	public double honorario()
	{
		return this.base * 1.07;
		
	}

}
