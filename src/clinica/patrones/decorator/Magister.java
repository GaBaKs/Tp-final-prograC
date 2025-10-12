package clinica.patrones.decorator;

import clinica.modelo.personas.medicos.IMedico;


/** Extiende la clase DecoratorPosgrado y aplica la capa decoradora. */
public class Magister extends DecoratorPosgrado {

	public Magister(IMedico encapsulado)
	{
		super(encapsulado);
		
	}

	@Override
	public double honorario() { return this.encapsulado.honorario() * 1.05; }

    /** Devuelve la especialidad del medico encapsulado.*/
    @Override
    public String getEspecialidad() { return this.encapsulado.getEspecialidad(); }

    /** Metodo que te da el nombre del medico encapsulado. */
    public String getNombre() { return  this.encapsulado.getNombre(); }
}
