package clinica.patrones.decorator;

import clinica.modelo.medicos.IMedico;

public class Permanente  extends DecoratorContratacion {

	public Permanente(IMedico encapsuladoPosgrado)
	{
		super(encapsuladoPosgrado);
	}

	@Override
	public double honorario()
	{
		return this.encapsuladoPosgrado.honorario() * 1.1;
	}
	
	

}
