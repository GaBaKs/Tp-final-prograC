package sistema;

public abstract class DecoratorContratacion implements IMedico{
    IMedico encapsuladoPosgrado;

    public DecoratorContratacion(IMedico encapsulado)
    {
        this.encapsuladoPosgrado=encapsulado;
    }



}
