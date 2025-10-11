package clinica.modelo.personas.medicos;

import clinica.modelo.personas.pacientes.Paciente;

/** Interfaz utilizada en el calculo de los honorarios para los medicos. */
public interface IMedico {
	
abstract public double honorario();
abstract public String getEspecialidad();

}
