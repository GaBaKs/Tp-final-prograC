package clinica.modelo.internacion;

import clinica.modelo.habitacion.Habitacion;
import clinica.modelo.personas.pacientes.Paciente;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
/**
 * Representa la internacion de un {@link Paciente}, al cual se le asigna una {@link Habitacion}
 */
public class Internacion {
    /** Representa el tipo de habitacion en la que el paciente se internara */
    private Habitacion habitacion;
    /** Representa la fecha de internacion del paciente */
    private LocalDate fechaIngreso;
    /** Representa la fecha en la que se le da el alta al paciente */
    private LocalDate fechaEgreso;
    /** Representa la cantidad de dias en internacion */
    private int diasInternacion;
    /** Representa el costo total de la internacion */
    private double costoTotal;

    /**
     * Crea una nueva internación para un paciente en una habitación determinada.
     *
     * <p>Al instanciar una internación, la habitación queda marcada como ocupada
     * mediante la asignación del paciente correspondiente.</p>
     *
     * @param habitacion habitación en la que se internará el paciente
     * @param fechaIngreso fecha de ingreso a la internación
     * @param paciente paciente que será internado
     * @throws IllegalStateException si la habitación ya se encuentra ocupada
     */
    public Internacion(Habitacion habitacion, LocalDate fechaIngreso, Paciente paciente) {
        this.habitacion = habitacion;
        this.fechaIngreso = fechaIngreso;
        this.diasInternacion = 0;
        this.costoTotal = 0;
        habitacion.asignarPaciente(paciente); // marca la habitación como ocupada
    }

    /**
     * Registra el egreso del paciente, calculando los días de internación
     * y el costo total asociado.
     *
     * <p>El número de días se determina restando la fecha de ingreso de la fecha de egreso.
     * Si el resultado es cero, se considera un mínimo de un día de internación.</p>
     *
     * @param fechaEgreso fecha en la que el paciente recibe el alta médica
     */
    public void egresar(LocalDate fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
        this.diasInternacion = (int) ChronoUnit.DAYS.between(fechaIngreso, fechaEgreso);
        if (diasInternacion == 0) diasInternacion = 1; // mínimo 1 día
        this.costoTotal = habitacion.calcularCostoTotal(diasInternacion);
        habitacion.liberarHabitacion();
    }

    public double getCostoTotal() { return costoTotal; }
    public int getDiasInternacion() { return diasInternacion; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public LocalDate getFechaEgreso() { return fechaEgreso; }
    public Habitacion getHabitacion() { return habitacion; }
}
