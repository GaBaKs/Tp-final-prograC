package clinica.patrones.decorator;

import clinica.modelo.personas.medicos.IMedico;

/** Extiende la clase DecoratorContratacion y aplica la capa decoradora sobre la capa que hizo el DecoratorPosgrado. */
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

    /** Devuelve la especialidad del medico encapsulado.*/
    public java.lang.String getEspecialidad() { return this.encapsulado.getEspecialidad(); }        // devuelve la especialidad del medico encapsulado y en este caso llamara
                                                                                                    //a getEspecialidad del encapsulado que devuelve el String de la especialidad
    /** Metodo que te da el nombre del medico encapsulado.*/
    public String getNombre() { return  this.encapsulado.getNombre(); }
}
