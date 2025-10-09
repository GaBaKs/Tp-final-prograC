package clinica.patrones.decorator;

import clinica.modelo.personas.medicos.IMedico;

public class Residente extends DecoratorContratacion{

	public Residente(IMedico encapsuladoPosgrado)
	{
		super(encapsuladoPosgrado);
	}

	@Override
    public double honorario()
	{
		return this.encapsulado.honorario() * 1.05;
	}


    public java.lang.String toString() { return this.encapsulado.toString(); }

}
