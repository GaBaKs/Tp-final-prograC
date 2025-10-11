package clinica.modulos.facturacion;

import clinica.modelo.facturacion.DetalleConsulta;
import clinica.modelo.facturacion.Factura;
import clinica.modelo.habitacion.Habitacion;
import clinica.modelo.internacion.Internacion;
import clinica.modelo.personas.medicos.Medico;
import clinica.modelo.personas.pacientes.Paciente;
import clinica.patrones.facade.Sistema;

import java.time.LocalDate;
import java.util.*;

/** Modulo que se encarga de la facturacion de la clinica. */
public class ModuloFacturacion extends Sistema {
    /** Historial de facturas de la clinica. */
    private List<Factura> facturas;

    public ModuloFacturacion() {
        this.facturas = new ArrayList<>();
    }

    /** Genera la factura con los datos correspondientes. */
    public Factura generarFactura(Paciente paciente, DetalleConsulta consulta) {
        LocalDate fechaIngreso;
        LocalDate fechaEgreso;
        Habitacion habitacion = null;
        int dias = 0;
        double costoHabitacion = 0,total;

        if (paciente.getInternacion() != null) {
            Internacion internacion = paciente.getInternacion();
            fechaIngreso = internacion.getFechaIngreso();
            fechaEgreso = internacion.getFechaEgreso();
            habitacion = internacion.getHabitacion();
            dias = internacion.getDiasInternacion();
            costoHabitacion = internacion.getCostoTotal();
        } else {
            // paciente ambulatorio
            fechaIngreso = LocalDate.now();
            fechaEgreso = LocalDate.now();
        }

        Factura factura = new Factura(paciente, fechaIngreso, fechaEgreso,consulta);
        // agregar internación si existió
        if (habitacion != null) {
            factura.setInternacion(habitacion, dias, costoHabitacion);
        }

        factura.setTotal(factura.getTotal());;
        facturas.add(factura);

        return factura;
    }


    public void mostrarFacturas() {
        facturas.forEach(System.out::println);
    }

    public List<Factura> getFacturas() {
        return facturas;
    }
}
