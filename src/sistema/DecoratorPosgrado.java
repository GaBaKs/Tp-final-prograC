package sistema;

public abstract class DecoratorPosgrado implements IMedico{
	IMedico encapsulado;
	
	public DecoratorPosgrado(IMedico encapsulado)
	{
		this.encapsulado=encapsulado;
	}

	
	
	

}
