package clinica.modelo.internacion;

import clinica.modelo.habitacion.Habitacion;
import clinica.modelo.personas.pacientes.Paciente;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Internacion {
    private Habitacion habitacion;
    private LocalDate fechaIngreso;
    private LocalDate fechaEgreso;
    private int diasInternacion;
    private double costoTotal;

    public Internacion(Habitacion habitacion, LocalDate fechaIngreso, Paciente paciente) {
        this.habitacion = habitacion;
        this.fechaIngreso = fechaIngreso;
        this.diasInternacion = 0;
        this.costoTotal = 0;
        habitacion.asignarPaciente(paciente); // marca la habitación como ocupada
    }

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
