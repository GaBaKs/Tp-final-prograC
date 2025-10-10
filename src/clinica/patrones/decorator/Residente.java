package clinica.patrones.decorator;

import clinica.modelo.personas.medicos.IMedico;

/** extiende la clase DecoratorContratacion y aplica la capa decoradora sobre la capa q hizo el DecoratorPosgrado */
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


    public java.lang.String getEspecialidad() { return this.encapsulado.getEspecialidad(); }        // devuelve la especialidad del medico encapsulado y en este caso llamara
                                                                                                    //a getEspecialidad del encapsulado que devuelve el String de la especialidad
}
