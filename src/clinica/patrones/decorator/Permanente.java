package clinica.patrones.decorator;

import clinica.modelo.personas.medicos.IMedico;

public class Permanente  extends DecoratorContratacion {

	public Permanente(IMedico encapsuladoPosgrado)
	{
		super(encapsuladoPosgrado);
	}

	@Override
	public double honorario()
	{
		return this.encapsulado.honorario() * 1.1;
	}

    public java.lang.String toString() { return this.encapsulado.toString(); }
}
