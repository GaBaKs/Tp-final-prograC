package clinica.modelo.habitacion;

public class HabitacionCompartida extends Habitacion {
    private double costoHabComp;

    public HabitacionCompartida(int numero, double costoAsignacion, double costoHabComp) {
        super(numero, costoAsignacion);
        this.costoHabComp = costoHabComp;
    }

    @Override
    public double calcularCostoTotal(int diasInternacion) {
        return costoAsignacion + (costoHabComp * diasInternacion);
    }

    @Override
    public String getTipo() {
        return "Habitación Compartida";
    }
}
