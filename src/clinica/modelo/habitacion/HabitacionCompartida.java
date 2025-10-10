package clinica.modelo.habitacion;

/**
 * Clase que se extiende de la clase abstracta Habitacion, representa un habitacion del tipo compartida
 */
public class HabitacionCompartida extends Habitacion {
    /** Representa el valor de la habitacion */
    private double costoHabComp;

    public HabitacionCompartida(int numero, double costoAsignacion, double costoHabComp) {
        super(numero, costoAsignacion);
        this.costoHabComp = costoHabComp;
    }

    /**
     * Sobreesribe el metodo para el calculo del costo total
     */
    @Override
    public double calcularCostoTotal(int diasInternacion) {
        return costoAsignacion + (costoHabComp * diasInternacion);
    }

    @Override
    public String getTipo() {
        return "Habitación Compartida";
    }
}
