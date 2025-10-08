package gestionpaciente;
import java.time.LocalDate;
import java.util.ArrayList;
import  medico.Medico;
import salaespera.Habitacion;

public abstract class Paciente implements IPaciente extends Persona {

    private int numhistclinica;
    private String rangoetario;
    private int numorden;
    ArrayList <Medico> historialMedico = new ArrayList<Medicos>();
    LocalDate fechaingreso;
    Habitacion habitacion;

    public Paciente(String dni, String n_A, String domicilio, String ciudad,String telefono,int numhistclinica) {
        super();
        this.numhistclinica = numhistclinica;
    }

    public abstract Paciente decidirLugar(Paciente otro);
    public abstract Paciente enfrentar(Nino n);
    public abstract Paciente enfrentar(Joven j);
    public abstract Paciente enfrentar(Mayor m);


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

    public void setHabitacion(Habitacion habitacion)
    {
        this.habitacion=habitacion;
    }

    public ArrayList<Medico> getHistorialMedico() {
        return historialMedico;
    }
    LocalDate setFechaIngreso()
    {
        this.fechaingreso=LocalDate.now();
    }
    LocalDate getFechaIngreso()
    {
        return this.fechaingreso;
    }

    public void setnumorden(int numorden) {
        this.numorden=numorden;
    }

}