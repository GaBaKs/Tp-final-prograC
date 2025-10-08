package clinica.patrones.decorator;

import clinica.modelo.medicos.IMedico;

public class Doctorado extends DecoratorPosgrado {

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
