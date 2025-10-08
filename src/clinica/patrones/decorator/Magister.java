package clinica.patrones.decorator;

import clinica.modelo.personas.medicos.IMedico;

public class Magister extends DecoratorPosgrado {

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
