package clinica.modelo.personas.pacientes;

import clinica.modelo.personas.medicos.Medico;
import clinica.modelo.personas.Persona;
import clinica.modulos.Consulta;

/** Representa a un paciente el cual se extiende de persona y es extendida por los tipos de paciente(segun su rango etario).*/
public abstract class Paciente extends Persona {
    /** Numero que representa el historial clinico del paciente*/
    private int numhistclinica;

    /** Numero que le da la clinica para ser atendido.*/
    private int numorden;


    public Paciente(String dni, String n_A, String domicilio, String ciudad,String telefono,int numhistclinica)
    {
        super(dni, n_A,domicilio,ciudad,telefono);
        this.numhistclinica = numhistclinica;
        numorden=0;
    }
    
    /** Comprueba los rangos etarios de dos pacientes y le asigna a cada uno un lugar en las zonas de espera. */
    public abstract Paciente decidirLugar(Paciente otro);
    public abstract Paciente enfrentar(Nino n);
    public abstract Paciente enfrentar(Joven j);
    public abstract Paciente enfrentar(Mayor m);


    public void setNumHistClinica(int num)
    {
        this.numhistclinica=num;
    }

    public void setnumorden(int numorden)
    {
        this.numorden=numorden;
    }

}