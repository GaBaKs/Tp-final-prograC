package clinica.patrones.decorator;

import clinica.modelo.personas.medicos.IMedico;

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

    @java.lang.Override
    public java.lang.String toString() { return this.encapsulado.toString(); }
}
