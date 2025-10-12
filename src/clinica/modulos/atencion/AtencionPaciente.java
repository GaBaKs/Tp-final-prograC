package clinica.modulos.atencion;

import clinica.modelo.personas.medicos.IMedico;
import clinica.modelo.personas.pacientes.Paciente;
import clinica.modulos.ProcesoPacientes;

import java.lang.reflect.Array;
import java.util.ArrayList;

/** Representa la atencion al paciente al entrar a la clinica. */
public class AtencionPaciente{

    private ProcesoPacientes modProceso;
    /**Lista que contiene los pacientes que se encuentran en atencion.*/
    private ArrayList<Paciente> pacientesEnAtencion;

    public AtencionPaciente(ProcesoPacientes proceso)
    {
        modProceso=proceso;
        pacientesEnAtencion=new ArrayList();
    }
    /** Se retira al paciente de la lista de espera y se agrega en la lista de pacientes en atencion.
     *
     * @param medico
     * @param paciente
     */
    public void atiendePaciente(IMedico medico, Paciente paciente)
    { // El paciente es retirado de la espera y se agrega a atención
        if (modProceso.getSalaesperaPrivada().getPaciente()!=null && modProceso.getSalaesperaPrivada().getPaciente().equals(paciente))
        {
            modProceso.getSalaesperaPrivada().setPaciente(null);
        }
        else
        {
            if (modProceso.getPatio().estaEnPatio(paciente)) {
                modProceso.getPatio().sacaPaciente(paciente);
            }
        }
        if (!(pacientesEnAtencion.contains(paciente))) {
            pacientesEnAtencion.add(paciente);
        }
        modProceso.agregaConsulta(medico,paciente);
    }

    /**
     * Se retira al paciente de la lista de atencion, lo que indica su egreso de la clinica.
     * @param p1
     */
    public void sacaPacienteAtencion(Paciente p1)
    {
        pacientesEnAtencion.remove(p1);
    }

    /**
     * Comprueba que el paciente se encuentre en la lista de atencion.
     * @param paciente
     * @return boolean
     */
    public boolean tienePaciente(Paciente paciente)
    {
        if(pacientesEnAtencion.contains(paciente))
            return true;
        else
            return false;
    }
    public  ArrayList<Paciente>  getPacientesEnAtencion() {
        return pacientesEnAtencion;
    }
}
