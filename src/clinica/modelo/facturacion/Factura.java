package clinica.modelo.facturacion;

import clinica.modelo.habitacion.Habitacion;
import clinica.modelo.personas.medicos.IMedico;
import clinica.modelo.personas.medicos.Medico;
import clinica.modelo.personas.pacientes.Paciente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa la factura que se le da al {@link Paciente} una vez terminada su consulta
 * <p>La factura incluye informacion sobre las fechas de ingreso y egreso,
 * los costos asociados a la internación (si existio), los honorarios medicos
 * y el calculo del total a pagar.</p>
 */
public class Factura {
    /**Contador que sirve para asignarle a cada factura un valor unico*/
    private static int nfactura = 0;
    /**Representa el numero de factura*/
    private int numero;
    /**Representa la fecha de ingreso del {@link Paciente} a la consulta*/
    private LocalDate fechaIngreso;
    /**Representa la fecha de egreso del {@link Paciente} de la clinica*/
    private LocalDate fechaEgreso;
    /**Representa al {@link Paciente} al cual le pertenece la factura*/
    private Paciente paciente;
    /**Medicos que atendieron al {@link Paciente}*/
    private DetalleConsulta detalleConsulta;
    /**En caso de internacion se representa el tipo de habitacion a la que fue trasladado el {@link Paciente}*/
    private Habitacion habitacion;
    /**En caso de internacion cantidad de dias en los que el {@link Paciente} permanece internado*/
    private int diasInternacion;
    /**En caso de internacion se representa el costo por la habitacion*/
    private double costoHabitacion;
    /**Representa el costo total*/
    private double total;

    public Factura(Paciente paciente, LocalDate ingreso, LocalDate egreso,DetalleConsulta consulta) {
        this.numero = nfactura++;
        this.paciente = paciente;
        this.fechaIngreso = ingreso;
        this.fechaEgreso = egreso;
        this.detalleConsulta = consulta;
    }



    public void setInternacion(Habitacion hab, int dias, double costo) {
        this.habitacion = hab;
        this.diasInternacion = dias;
        this.costoHabitacion = costo;
    }

    public double getTotal() {
        double total=0;
        int i;
        ArrayList<Medico> lista = new ArrayList<Medico>();
        if( this.habitacion != null){
            total+=this.habitacion.calcularCostoTotal(this.diasInternacion);
        }

        lista=detalleConsulta.getMedicosConsulta();
        for (i=0;i<lista.size();i++) {
            total+=lista.get(i).honorario()*1.20;
        }

        return total;
    }

    /**
     * Crea una nueva factura asociada a un {@link Paciente} y a una consulta médica.
     */
    public String muestraFactura() {
        double total=0;
        int i;
        ArrayList<Medico> lista = new ArrayList<Medico>();

        StringBuilder sb = new StringBuilder();
        sb.append("================================================\n");
        sb.append("Factura Nº ").append(numero).append("\n");
        sb.append("Paciente: ").append(paciente.getN_A()).append("\n");
        sb.append("Fecha Ingreso: ").append(fechaIngreso).append("\n");
        sb.append("Fecha Egreso: ").append(fechaEgreso).append("\n");


        sb.append("Días: ").append(diasInternacion).append("\n");
                if( habitacion != null){
                    sb.append("Habitación: ").append(habitacion.getTipo())
                            .append("       Costo: $")
                            .append(String.format("%.2f", habitacion.calcularCostoTotal(diasInternacion)))
                            .append("\n\n");
                    total+=habitacion.calcularCostoTotal(diasInternacion);
                }
                else{
                    sb.append("no se interno ")
                            .append("       Costo: $0")
                            .append("\n\n");
                }


        sb.append("Consultas médicas:\n");
        lista=detalleConsulta.getMedicosConsulta();
        for (i=0;i<lista.size();i++) {
            sb.append("nombre medico: "+lista.get(i).getN_A())
                    .append("   especialidad: "+lista.get(i).getEspecialidad())
                    .append("   subtotal: "+lista.get(i).honorario()*1.20)
                    .append("\n");
            total+=lista.get(i).honorario()*1.20;
        }

        sb.append("\nTOTAL: $").append(String.format("%.2f",total)).append("\n");
        sb.append("================================================\n");
        return sb.toString();
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
