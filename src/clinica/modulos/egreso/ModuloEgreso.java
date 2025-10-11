package clinica.modulos.egreso;

import clinica.modelo.habitacion.Habitacion;
import clinica.modelo.internacion.Internacion;
import clinica.modelo.personas.pacientes.Paciente;
import clinica.modelo.personas.medicos.Medico;
import clinica.modulos.facturacion.ModuloFacturacion;

import java.time.LocalDate;
import java.util.List;

/** Modulo que simula el alta de los pacientes. */
public class ModuloEgreso {

    /** Lista de pacientes en atencion. */
    private List<Paciente> pacientesEnAtencion;
    /** Modulo que se encarga de la facturacion correspondiente */
    private ModuloFacturacion moduloFacturacion;

    public ModuloEgreso(List<Paciente> pacientesEnAtencion, ModuloFacturacion moduloFacturacion) {
        this.pacientesEnAtencion = pacientesEnAtencion;
        this.moduloFacturacion = moduloFacturacion;
    }


     //Egresar un paciente (lo selecciona, genera su factura y lo saca de la lista)

    /** Se le da el alta al paciente generando su factura y sacandolo de la lista de pacientes en atencion. */
    public void egresarPaciente(Paciente paciente ) {
        if (!pacientesEnAtencion.contains(paciente)) {
            System.out.println("El paciente no está en la lista de atención.");
            return;
        }

        // Si el paciente estaba internado, se completa la información de egreso
        if (paciente.getInternacion() != null) {
            Internacion internacion = paciente.getInternacion();
            internacion.egresar(LocalDate.now());
        }

        // Generar la factura
        var factura = moduloFacturacion.generarFactura(paciente,paciente.get);

        // Retirar paciente de lista de atención
        pacientesEnAtencion.remove(paciente);
    }
}


