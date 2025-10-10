package clinica.patrones.decorator;

import clinica.modelo.personas.medicos.IMedico;

/** extiende la clase DecoratorPosgrado y aplica la capa decoradora */
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
    public java.lang.String getEspecialidad() { return this.encapsulado.getEspecialidad(); }
}
