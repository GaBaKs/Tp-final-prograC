package clinica.modulos.facturacion;

import clinica.modelo.facturacion.Factura;
import clinica.modelo.habitacion.Habitacion;
import clinica.modelo.internacion.Internacion;
import clinica.modelo.personas.medicos.Medico;
import clinica.modelo.personas.pacientes.Paciente;
import clinica.patrones.facade.Sistema;

import java.time.LocalDate;
import java.util.*;

public class ModuloFacturacion extends Sistema {
    private List<Factura> facturas;

    public ModuloFacturacion() {
        this.facturas = new ArrayList<>();
    }

    public Factura generarFactura(Paciente paciente, List<Medico> medicos) {
        LocalDate fechaIngreso;
        LocalDate fechaEgreso;
        Habitacion habitacion = null;
        int dias = 0;
        double costoHabitacion = 0;

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

        Factura factura = new Factura(paciente, fechaIngreso, fechaEgreso);

        // agregar consultas médicas
        for (Medico m : medicos) {
            factura.agregarConsulta(m);
        }

        // agregar internación si existió
        if (habitacion != null) {
            factura.setInternacion(habitacion, dias, costoHabitacion);
        }

        factura.calcularTotal();
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
