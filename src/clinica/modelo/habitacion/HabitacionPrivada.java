package clinica.modelo.habitacion;
/**
 * Clase que se extiende de la clase abstracta Habitacion, representa un habitacion del tipo privada.
 */
public class HabitacionPrivada extends Habitacion
{
    /** Representa el valor de la habitacion. */
    private double costoHabPriv;

    public HabitacionPrivada(int numero, double costoAsignacion, double costoHabPriv)
    {
        super(numero, costoAsignacion);
        this.costoHabPriv = costoHabPriv;
    }

    /**
     * Sobreesribe el metodo para el calculo del costo total.
     * @param diasInternacion
     * @return CostoTotal
     */
    @Override
    public double calcularCostoTotal(int diasInternacion)
    {
        double factor = 1.0;

        if (diasInternacion == 1)
            factor = 1.0;
        else if (diasInternacion >= 2 && diasInternacion <= 5)
            factor = 1.3;
        else if (diasInternacion >= 6)
            factor = 2.0;

        return costoAsignacion + (costoHabPriv * diasInternacion * factor);
    }

    @Override
    public String getTipo() {
        return "Habitación Privada";
    }
}
