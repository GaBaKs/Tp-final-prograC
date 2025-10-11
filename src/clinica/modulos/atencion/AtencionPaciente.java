package clinica.modulos.atencion;

import clinica.modelo.personas.medicos.Medico;
import clinica.modelo.personas.pacientes.Paciente;
import clinica.modelo.habitacion.Habitacion;

import java.util.*;

/** Representa la atencion al paciente al entrar a la clinica. */
public class AtencionPaciente {
    /** Lista de pacientes en atencion, con su medico correspondiente. */
    private Map<Paciente, List<Medico>> pacientesEnAtencion;
    /** Lista de pacientes con su habitacion correspondiente. */
    private Map<Paciente, Habitacion> habitacionesAsignadas;


    public void AtencionPacientes() {
        pacientesEnAtencion = new HashMap<>();
        habitacionesAsignadas = new HashMap<>();
    }

    /** Se retira al paciente de la lista de espera y se agrega en la lista de pacientes en atencion. */
    public void atiendePaciente(Medico medico, Paciente paciente) { // El paciente es retirado de la espera y se agrega a atención
        pacientesEnAtencion.putIfAbsent(paciente, new ArrayList<>());
        pacientesEnAtencion.get(paciente).add(medico);
    }

    /** Interna al paciente asignandole la habitacion correspondiente.*/
    public void internaPaciente(Paciente paciente, Habitacion habitacion) {
        habitacionesAsignadas.put(paciente, habitacion);
        habitacion.asignarPaciente(paciente);
    }

    /** Libera la habitacion, preparandola para un nuevo paciente. */
    public void liberarHabitacion(Paciente paciente) {
        Habitacion h = habitacionesAsignadas.remove(paciente);
        if (h != null) h.liberarHabitacion();
    }


    public List<Medico> obtenerMedicosQueAtendieron(Paciente paciente) {
        return pacientesEnAtencion.getOrDefault(paciente, new ArrayList<>());
    }

    public Habitacion obtenerHabitacionDePaciente(Paciente paciente) {
        return habitacionesAsignadas.get(paciente);
    }

    /** Se retira al paciente de la lista de atendidos indicando que su consulta y/o internacion a terminado. */
    public void retirarPaciente(Paciente paciente) {
        pacientesEnAtencion.remove(paciente);
        habitacionesAsignadas.remove(paciente);
    }

    public Map<Paciente, List<Medico>> getPacientesEnAtencion() {
        return pacientesEnAtencion;
    }
}
