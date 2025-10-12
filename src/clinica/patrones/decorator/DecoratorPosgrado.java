package clinica.patrones.decorator;

/** Decorator que contiene un medico como encapsulado.*/
import clinica.modelo.personas.medicos.IMedico;

public abstract class DecoratorPosgrado implements IMedico{
    /** Este encapsulado es un medico por lo cual se usaran los metodos de un medico y se le aplica la capa del decorator.*/
    IMedico encapsulado;
	
	public DecoratorPosgrado(IMedico encapsulado)
	{
		this.encapsulado=encapsulado;
	}

    public abstract double honorario();
	
	

}
