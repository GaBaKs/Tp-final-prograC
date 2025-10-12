package clinica.modelo.personas.medicos;

import clinica.modelo.personas.pacientes.Paciente;

/** Interfaz utilizada en el calculo de los honorarios para los medicos. */
public interface IMedico {
    /**Calculo del honorario medico correspondiente.*/
    abstract public double honorario();
    /**Devuelve la especialidad del medico.*/
    abstract public String getEspecialidad();
    /**Devuelve el nombre del medico.*/
    abstract public String getNombre();
}
