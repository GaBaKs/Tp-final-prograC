package clinica.patrones.decorator;

import clinica.modelo.personas.medicos.IMedico;

public abstract class DecoratorContratacion implements IMedico{
    IMedico encapsulado;

    public DecoratorContratacion(IMedico encapsulado)
    {
        this.encapsulado =encapsulado;
    }


    public abstract double honorario();
}
