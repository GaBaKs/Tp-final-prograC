package medico;

public class Magister extends Decorator_Posgrado {

	public Magister(IMedico encapsulado) 
	{
		super(encapsulado);
		
	}

	@Override
	public double honorario() 
	{
		return this.encapsulado.honorario() * 1.05;
	}
	
}
