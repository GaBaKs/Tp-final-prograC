package clinica.modulos.atencion;

import clinica.modelo.personas.medicos.Medico;
import clinica.modelo.personas.pacientes.Paciente;
import clinica.modelo.habitacion.Habitacion;

import java.util.*;

public class AtencionPaciente {
    private Map<Paciente, List<Medico>> pacientesEnAtencion;
    private Map<Paciente, Habitacion> habitacionesAsignadas;


    public void AtencionPacientes() {
        pacientesEnAtencion = new HashMap<>();
        habitacionesAsignadas = new HashMap<>();
    }
    // El paciente es retirado de la espera y se agrega a atención
    public void atiendePaciente(Medico medico, Paciente paciente) {
        pacientesEnAtencion.putIfAbsent(paciente, new ArrayList<>());
        pacientesEnAtencion.get(paciente).add(medico);
    }

    public void internaPaciente(Paciente paciente, Habitacion habitacion) {
        habitacionesAsignadas.put(paciente, habitacion);
        habitacion.asignarPaciente(paciente);
    }

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

    public void retirarPaciente(Paciente paciente) {
        pacientesEnAtencion.remove(paciente);
        habitacionesAsignadas.remove(paciente);
    }

    public Map<Paciente, List<Medico>> getPacientesEnAtencion() {
        return pacientesEnAtencion;
    }
}
