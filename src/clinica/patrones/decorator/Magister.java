package clinica.patrones.decorator;

import clinica.modelo.personas.medicos.IMedico;


/** extiende la clase DecoratorPosgrado y aplica la capa decoradora */
public class Magister extends DecoratorPosgrado {

	public Magister(IMedico encapsulado)
	{
		super(encapsulado);
		
	}

	@Override
	public double honorario() { return this.encapsulado.honorario() * 1.05; }

    @Override
    public String getEspecialidad() { return this.encapsulado.getEspecialidad(); }
}
