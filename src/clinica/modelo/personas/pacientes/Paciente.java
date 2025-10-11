package clinica.modelo.personas.pacientes;
import java.time.LocalDate;
import java.util.ArrayList;

import clinica.modelo.facturacion.DetalleConsulta;
import clinica.modelo.habitacion.Habitacion;
import clinica.modelo.internacion.Internacion;
import clinica.modelo.personas.medicos.IMedico;
import clinica.modelo.personas.medicos.Medico;
import clinica.modelo.personas.Persona;
/** Representa a un paciente el cual se extiende de persona y es extendida por los tipos de paciente(segun su rango etario).*/
public abstract class Paciente extends Persona {

    private int numhistclinica;

    /** Numero que le da la clinica para ser atendido.*/
    private int numorden;

    DetalleConsulta detalleConsulta;

    /** Fecha en la cual el pciente ingreso a la consulta.*/
    LocalDate fechaingreso;

    /** Es la habitacion en la cual el paciente sera atendido.*/
    Habitacion habitacion;

    /** Este puntero nos indica si el paciente esta en una habitacion de internacion(si esta internado) o no.*/
    private Internacion internacion;

    public Paciente(String dni, String n_A, String domicilio, String ciudad,String telefono,int numhistclinica) {
        super(dni, n_A,domicilio,ciudad,telefono);
        this.numhistclinica = numhistclinica;
        detalleConsulta= new DetalleConsulta();
    }
    
    /** Comprueba los rangos etarios de dos pacientes y le asigna a cada uno un lugar en la lista de espera. */
    public abstract Paciente decidirLugar(Paciente otro);
    public abstract Paciente enfrentar(Nino n);
    public abstract Paciente enfrentar(Joven j);
    public abstract Paciente enfrentar(Mayor m);

    /** Agrega un medico a la lista de medicos que atendieron al paciente. */
    public void agregaMedicoAtendido(Medico m1){
        detalleConsulta.agregaMedico(m1);
    }

    /** Interna al paciente, asignandole una habitacion */
    public void internar(Habitacion habitacion, LocalDate fechaIngreso, Paciente paciente) {
        this.internacion = new Internacion(habitacion, fechaIngreso, paciente);
    }

    /** Se le da el alta a un paciente con una fecha de egreso para conocer los dias que paso internado. */
    public void egresar(LocalDate fechaEgreso) {
        if (internacion != null) {
            internacion.egresar(fechaEgreso);
        }
    }

    public Internacion getInternacion() { return internacion; }



    public void setNumHistClinica(int num)
    {
        this.numhistclinica=num;
    }


    LocalDate setFechaIngreso()
    {
        this.fechaingreso=LocalDate.now();
        return this.fechaingreso;
    }
    LocalDate getFechaIngreso()
    {
        return this.fechaingreso;
    }

    public void setnumorden(int numorden) {
        this.numorden=numorden;
    }

}