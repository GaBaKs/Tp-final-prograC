
public class Clinica extends Medico {

	public Clinica(String dni,String N_A,String domicilio,String ciudad,int NMatricula,int telefono) 
	{
		super(dni,N_A,domicilio,ciudad,NMatricula,telefono);
	}

	@Override
	public double honorario()
	{
		return this.base * 1.05;
		
	}


}
