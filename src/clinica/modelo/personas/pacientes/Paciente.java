package clinica.modelo.personas.pacientes;
import java.time.LocalDate;
import java.util.ArrayList;

import clinica.modelo.facturacion.DetalleConsulta;
import clinica.modelo.habitacion.Habitacion;
import clinica.modelo.internacion.Internacion;
import clinica.modelo.personas.medicos.IMedico;
import clinica.modelo.personas.medicos.Medico;
import clinica.modelo.personas.Persona;
/** paciente se extiende de persona y es extendida por los tipos de paciente(su rango etario)*/
public abstract class Paciente extends Persona {

    private int numhistclinica;

    /** numero que le da la clinica para ser atendido*/
    private int numorden;

    DetalleConsulta detalleConsulta;

    /** fecha en la cual el pciente ingreso a la consulta*/
    LocalDate fechaingreso;

    /** es la habitacion en la cual el paciente sera atendido*/
    Habitacion habitacion;

    /** este puntero nos indica si el paciente esta en una habitacion de internacion(si esta internado) o no*/
    private Internacion internacion;

    public Paciente(String dni, String n_A, String domicilio, String ciudad,String telefono,int numhistclinica) {
        super(dni, n_A,domicilio,ciudad,telefono);
        this.numhistclinica = numhistclinica;
        detalleConsulta= new DetalleConsulta();
    }

    public abstract Paciente decidirLugar(Paciente otro);
    public abstract Paciente enfrentar(Nino n);
    public abstract Paciente enfrentar(Joven j);
    public abstract Paciente enfrentar(Mayor m);

    public void agregaMedicoAtendido(Medico m1){
        detalleConsulta.agregaMedico(m1);
    }

    public void internar(Habitacion habitacion, LocalDate fechaIngreso, Paciente paciente) {
        this.internacion = new Internacion(habitacion, fechaIngreso, paciente);
    }

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