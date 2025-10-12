package clinica.modelo.facturacion;

import clinica.modelo.habitacion.Habitacion;
import clinica.modelo.personas.medicos.IMedico;
import clinica.modelo.personas.pacientes.Paciente;
import clinica.modulos.Consulta;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Representa la factura que se le da al {@link Paciente} una vez que egresa
 * <p>La factura incluye informacion sobre las fechas de ingreso y egreso,
 * los costos asociados a la internación (si existio), los honorarios medicos
 * y el calculo del total a pagar.</p>
 */
public class Factura {
    /**Contador que sirve para asignarle a cada factura un valor unico.*/
    private static int nfactura = 0;
    /**Representa el numero de factura.*/
    private int numero;
    /**Representa la fecha de ingreso del {@link Paciente} a la consulta.*/
    private LocalDate fechaingreso;
    /**Representa la fecha de egreso del {@link Paciente} de la clinica.*/
    private LocalDate fechaegreso;
    /**Representa al {@link Paciente} al cual le pertenece la factura.*/
    private Paciente paciente;
    /**En caso de internacion se representa el tipo de habitacion a la que fue trasladado el {@link Paciente}.*/
    private Habitacion habitacion;
    /**En caso de internacion cantidad de dias en los que el {@link Paciente} permanece internado.*/
    private int diasinternacion;
    /**En caso de internacion se representa el costo por la habitacion.*/
    private double costohabitacion;
    /**Representa el costo total.*/
    private double total;
    /**Representa la cantidad de dias que el paciente permanecera internado.*/
    private int cantdias;
    /**Representa el valor agregado al honorario del medico utilizado a la hora de cobrar la consulta.*/
    private double incrementohonorario=1.2;
    /**Lista que contiene a todos los medicos que atendieron al paciente.*/
    private ArrayList<IMedico> medicosconsultados;

    /**
     * Constructor que genera la factura con todos sus datos correspondientes.
     * @param consulta
     */
    public Factura(Consulta consulta)
    {
        this.numero = nfactura++;
        this.total=0;
        this.paciente = consulta.getPaciente();
        if (consulta.getHabitacion() != null) // aclarar q significa que fue internado
        {
            this.fechaingreso = consulta.getFechaIngreso();
            this.fechaegreso = consulta.getFechaEgreso();
            this.diasinternacion = consulta.getCantdias();
            this.habitacion = consulta.getHabitacion();
            this.costohabitacion = consulta.getHabitacion().calcularCostoTotal(diasinternacion);
            this.total+=this.costohabitacion;
        }
        else
        {
            // paciente ambulatorio
            this.fechaingreso = this.fechaegreso = consulta.getFechaIngreso();
        }
        this.cantdias = consulta.getCantdias();
        this.medicosconsultados = consulta.getMedicosConsultados();
        this.total = this.total + this.getTotal();
    }

    public double getTotal()
    {
        double total=0;
        int i;
        for (i=0;i< medicosconsultados.size();i++) {
            total+=this.getMedicosConsultados().get(i).honorario()*incrementohonorario;
        }

        return total;
    }

    /**
     * Muestar la factura asociada al {@link Paciente} y a sus consultas médicas.
     * @return toString
     */
    public String muestraFactura()
    {
        int i;
        ArrayList<IMedico> lista = new ArrayList<IMedico>();

        StringBuilder sb = new StringBuilder();
        sb.append("================================================\n");
        sb.append(" Nº Factura ").append(this.numero).append("\n");
        sb.append("Nombre paciente: ").append(this.paciente.getN_A()).append("\n");
        sb.append("Fecha Ingreso: ").append(this.fechaingreso).append("\n");
        sb.append("Fecha Egreso: ").append(this.fechaegreso).append("\n");


        sb.append("Cant de días: ").append(this.cantdias).append("\n");
                if( this.habitacion != null){
                    sb.append("Habitación: ").append(this.habitacion.getTipo())
                            .append("       Costo: $")
                            .append(String.format("%.2f", this.costohabitacion)).append("\n");
                }
        sb.append("Consultas médicas:\n");
        for (i=0;i<medicosconsultados.size();i+=1)
        {
            sb.append("Nombre medico: ").append(medicosconsultados.get(i).getNombre())
                    .append("   Especialidad: ").append(medicosconsultados.get(i).getEspecialidad())
                    .append("   Subtotal: ").append(medicosconsultados.get(i).honorario() * this.incrementohonorario);
                    sb.append("\n");
        }

        sb.append("\nTotal: $").append(String.format("%.2f",this.total)).append("\n");
        sb.append("================================================\n");
        return sb.toString();
    }
    public ArrayList<IMedico> getMedicosConsultados()
    {

        return this.medicosconsultados;
    }

    public LocalDate getFechaEgreso(){
        return this.fechaegreso;
    }

    public Paciente getPaciente(){
        return this.paciente;
    }
    public double getIncrementoHonorario()
    {
        return this.incrementohonorario;
    }

}
