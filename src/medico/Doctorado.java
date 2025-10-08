package medico;

public class Doctorado extends Decorator_Posgrado {

	public Doctorado(IMedico encapsulado) 
	{
		super(encapsulado);
		
	}

	@Override
	public double honorario() 
	{
		return this.encapsulado.honorario() * 1.1;
	}
	
	

}
