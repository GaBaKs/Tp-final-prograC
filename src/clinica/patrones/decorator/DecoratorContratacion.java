package clinica.patrones.decorator;

import clinica.modelo.medicos.IMedico;

public abstract class DecoratorContratacion implements IMedico{
    IMedico encapsuladoPosgrado;

    public DecoratorContratacion(IMedico encapsulado)
    {
        this.encapsuladoPosgrado=encapsulado;
    }


    public abstract double honorario();
}
