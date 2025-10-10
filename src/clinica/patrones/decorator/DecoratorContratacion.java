package clinica.patrones.decorator;

import clinica.modelo.personas.medicos.IMedico;

/** este decorator implementa IMedico y contiene un encapsulado IMedico (va a ser el DecoratorPosgrado) para aplicar una capa a ese decorator*/
public abstract class DecoratorContratacion implements IMedico{
    IMedico encapsulado;     // este encapsulado sera un decoratorPosgrado por lo que cada llamado al encapsulado utiliza los metodos del decoratorPosgrado

    public DecoratorContratacion(IMedico encapsulado)
    {
        this.encapsulado =encapsulado;
    }


    public abstract double honorario();
}
