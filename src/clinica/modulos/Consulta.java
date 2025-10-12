package clinica.modulos;

import clinica.modelo.habitacion.Habitacion;
import clinica.modelo.personas.medicos.IMedico;
import clinica.modelo.personas.pacientes.Paciente;

import java.time.LocalDate;
import java.util.ArrayList;

/**Representa la consulta que uno o varios medicos le brindan al paciente.*/
public class Consulta {
    /**Representa al paciente en la consulta.*/
    private Paciente paciente;
    /**Representa la fecha de ingreso del {@link Paciente} a la consulta.*/
    private LocalDate fechaIngreso;
    /**Representa la fecha de egreso del {@link Paciente} de la clinica.*/
    private LocalDate fechaEgreso;
    /**Lista que contine a los medicos que brindaron atencion al paciente.*/
    private ArrayList<IMedico> medicosConsultados;
    /**Representa la habitacion donde el paciente será internado, si esto no es necesario sera null.*/
    private Habitacion habitacion;
    /**Representa la cantidad de dias en los que el paciente permanecera internado, si esto no es necesario sera 0.*/
    int cantdias;

    public Consulta(Paciente paciente,IMedico medico, LocalDate fechaIngreso)
    {
        this.paciente = paciente;
        this.fechaIngreso = fechaIngreso;
        this.medicosConsultados = new ArrayList<>();
        medicosConsultados.add(medico);
        setFechaEgreso(null);
        cantdias=0;
    }

    public Habitacion getHabitacion()
    {
        return this.habitacion;
    }

    public LocalDate getFechaIngreso(){
        return this.fechaIngreso;
    }

    public LocalDate getFechaEgreso(){
        return this.fechaEgreso;
    }

    public int getCantdias(){
        return this.cantdias;
    }

    public void agregaMedico(IMedico m1)
    {
        medicosConsultados.add(m1);
    }

    public void setFechaEgreso(LocalDate fechaEgreso)
    {
        this.fechaEgreso = fechaEgreso;
    }

    public void setHabitacion(Habitacion habitacion){
        this.habitacion = habitacion;
    }

    public void setCantDias(int cantdias){
        this.cantdias = cantdias;
    }


    public ArrayList<IMedico> getMedicosConsultados()
    {

        return this.medicosConsultados;
    }

    public Paciente getPaciente()
    {
        return this.paciente;
    }
}
