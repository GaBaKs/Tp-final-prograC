package clinica.modelo.facturacion;

import clinica.modelo.habitacion.Habitacion;
import clinica.modelo.medicos.Medico;
import clinica.modelo.pacientes.Paciente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Factura {
    private static int contador = 1;
    private int numero;
    private LocalDate fechaIngreso;
    private LocalDate fechaEgreso;
    private Paciente paciente;
    private List<DetalleConsulta> consultas;
    private Habitacion habitacion;
    private int diasInternacion;
    private double costoHabitacion;
    private double total;

    public Factura(Paciente paciente, LocalDate ingreso, LocalDate egreso) {
        this.numero = contador++;
        this.paciente = paciente;
        this.fechaIngreso = ingreso;
        this.fechaEgreso = egreso;
        this.consultas = new ArrayList<>();
    }

    public void agregarConsulta(Medico medico) {
        consultas.add(new DetalleConsulta(medico));
    }

    public void setInternacion(Habitacion hab, int dias, double costo) {
        this.habitacion = hab;
        this.diasInternacion = dias;
        this.costoHabitacion = costo;
    }

    public void calcularTotal() {
        total = costoHabitacion;
        for (DetalleConsulta c : consultas) total += c.getSubtotal();
    }

    public double getTotal() { return total; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("================================================\n");
        sb.append("Factura Nº ").append(numero).append("\n");
        sb.append("Paciente: ").append(paciente.getN_A()).append("\n");
        sb.append("Fecha Ingreso: ").append(fechaIngreso).append("\n");
        sb.append("Fecha Egreso: ").append(fechaEgreso).append("\n\n");

        if (habitacion != null) {
            sb.append("Habitación: ").append(habitacion.getTipo())
                    .append("  |  Días: ").append(diasInternacion)
                    .append("  |  Costo: $")
                    .append(String.format("%.2f", habitacion.calcularCostoTotal(diasInternacion)))
                    .append("\n\n");
        }

        sb.append("Consultas médicas:\n");
        for (DetalleConsulta d : consultas) {
            sb.append("  - ").append(d).append("\n");
        }

        sb.append("\nTOTAL: $").append(String.format("%.2f", total)).append("\n");
        sb.append("================================================\n");
        return sb.toString();
    }
}
