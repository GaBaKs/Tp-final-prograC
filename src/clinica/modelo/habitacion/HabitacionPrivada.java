package clinica.modelo.habitacion;

public class HabitacionPrivada extends Habitacion {
    private double costoHabPriv;

    public HabitacionPrivada(int numero, double costoAsignacion, double costoHabPriv) {
        super(numero, costoAsignacion);
        this.costoHabPriv = costoHabPriv;
    }

    @Override
    public double calcularCostoTotal(int diasInternacion) {
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
