package clinica.modelo.habitacion;

public class HabitacionCompartida extends Habitacion {
    private double costoDiario;

    public HabitacionCompartida(int numero, double costoAsignacion, double costoDiario) {
        super(numero, costoAsignacion);
        this.costoDiario = costoDiario;
    }

    @Override
    public double calcularCostoTotal(int diasInternacion) {
        return costoAsignacion + (costoDiario * diasInternacion);
    }

    @Override
    public String getTipo() {
        return "Habitación Compartida";
    }
}
