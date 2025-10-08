package clinica.modelo.pacientes;
import java.time.LocalDate;
import java.util.ArrayList;

import clinica.modelo.habitacion.Habitacion;
import clinica.modelo.internacion.Internacion;
import clinica.modelo.medicos.Medico;
import clinica.modelo.personas.Persona;

public abstract class Paciente extends Persona implements IPaciente {

    private int numhistclinica;
    private String rangoetario;
    private int numorden;
    ArrayList <Medico> historialMedico = new ArrayList<Medico>();
    LocalDate fechaingreso;
    Habitacion habitacion;
    private Internacion internacion;

    public Paciente(String dni, String n_A, String domicilio, String ciudad,String telefono,int numhistclinica) {
        super(dni, n_A,domicilio,ciudad,telefono);
        this.numhistclinica = numhistclinica;
    }

    public abstract Paciente decidirLugar(Paciente otro);
    public abstract Paciente enfrentar(Nino n);
    public abstract Paciente enfrentar(Joven j);
    public abstract Paciente enfrentar(Mayor m);


    public void internar(Habitacion habitacion, LocalDate fechaIngreso, Paciente paciente) {
        this.internacion = new Internacion(habitacion, fechaIngreso, paciente);
    }

    public void egresar(LocalDate fechaEgreso) {
        if (internacion != null) {
            internacion.egresar(fechaEgreso);
        }
    }

    public Internacion getInternacion() { return internacion; }

    public String getRangoEtario()
    {
        return this.rangoetario;
    }

    public void setNumHistClinica(int num)
    {
        this.numhistclinica=num;
    }

    public void agregaHistorial(Medico medico)
    {
        this.historialMedico.add(medico);
    }

    public ArrayList<Medico> getHistorialMedico() {
        return historialMedico;
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