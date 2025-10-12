package clinica.patrones.decorator;

import clinica.modelo.personas.medicos.IMedico;

/** Este decorator implementa IMedico y contiene un encapsulado IMedico (va a ser el DecoratorPosgrado) para aplicar una capa a ese decorator.*/
public abstract class DecoratorContratacion implements IMedico{
    protected IMedico encapsulado;     // este encapsulado sera un decoratorPosgrado por lo que cada llamado al encapsulado utiliza los metodos del decoratorPosgrado

    public DecoratorContratacion(IMedico encapsulado)
    {
        this.encapsulado = encapsulado;
    }

    public IMedico getEncapsulado() {
        return encapsulado;
    }


    public abstract double honorario();
}
