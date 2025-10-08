package clinica.patrones.decorator;

import clinica.modelo.medicos.IMedico;

public abstract class DecoratorPosgrado implements IMedico{
	IMedico encapsulado;
	
	public DecoratorPosgrado(IMedico encapsulado)
	{
		this.encapsulado=encapsulado;
	}

	
	
	

}
