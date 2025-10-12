package clinica.modulos;

import clinica.modelo.facturacion.Factura;
import clinica.modelo.habitacion.Habitacion;
import clinica.modelo.personas.medicos.IMedico;
import clinica.modelo.personas.pacientes.Paciente;

import java.time.LocalDate;
import java.util.*;

import clinica.modulos.ingreso.Patio;
import clinica.modulos.ingreso.SalaEsperaPrivada;

/**Clase que representa el procesamiento de los pacientes al ingresar a la clinica, asignandoles un lugar de espera.*/
public class ProcesoPacientes {

    private Factura modFacturacion;
    /** Representa la sala de espera privada, solo puede haber un paciente y si no hay nadie queda en @code null. */
    private   SalaEsperaPrivada salaesperaprivada;
    /** Lista que representa el patio donde esperan los pacientes a ser atendidos. */
    private Patio patio;
    /** Lista de espera de los pacientes. */
    private LinkedList<Paciente> listaEspera;
    /** Lista historica de consultas de cada paciente. */
    private  LinkedList<Consulta> consultaspendientes;

    /**
     * Se procesa al paciente asignandole un lugar de espera y agregandolo a la lista de espera.
     */
    public ProcesoPacientes()
    {
        salaesperaprivada=new SalaEsperaPrivada();
        patio=new Patio();
        listaEspera=new LinkedList<>();
        consultaspendientes =new LinkedList<>();
    }

    /**
     * Busca al paciente en el historial de consultas.
     * @param p1
     * @return IndicePaciente
     */
    public int buscaPaciente(Paciente p1) {
        int i=0;
        while ( i< consultaspendientes.size() && !(consultaspendientes.get(i).getPaciente().equals(p1)) )
            i++;
        if (i < consultaspendientes.size() && consultaspendientes.get(i).getPaciente().equals(p1))
        {
            return i;
        } else
            return -1;

    }

    /**
     * Agrega una consulta al historial de consultas.
     * @param m1
     * @param p1
     */
    public void agregaConsulta(IMedico m1, Paciente p1)
    {
        int j=buscaPaciente(p1);
            if (j!=-1)
               consultaspendientes.get(j).agregaMedico(m1);
           else
           {
               Consulta c1 = new Consulta(p1, m1, LocalDate.now());
               consultaspendientes.addFirst(c1);
           }
    }

    /**
     * Precondicones:
     * cantdias >= 0
     * Interna un paciente asignandole una habitacion durante la cantidad de dias de internacion.
     * @param p1
     * @param h1
     * @param cantdias
     */
    public void internaPaciente(Paciente p1, Habitacion h1,int cantdias)
    {
        int i=buscaPaciente(p1);
        consultaspendientes.get(i).setHabitacion(h1);
        consultaspendientes.get(i).setCantDias(cantdias);
    }

    /**
     * Egresa al paciente de la clinica quitandolo de la lista de atencion y generando la factura correspondiente.
     * @param p1
     * @return Factura
     */
    public Factura egresaPaciente(Paciente p1){
        int i=buscaPaciente(p1);
        Consulta consaux= consultaspendientes.get(i);
        consaux.setFechaEgreso(consaux.getFechaIngreso().plusDays(consaux.getCantdias()));
        Factura facaux= new Factura(consaux);
        consultaspendientes.remove(i);
        return facaux;
    }

    /**Agrega al paciente a la lista de espera.*/
    public void agregaListaEspera(Paciente paciente)
    {
        listaEspera.addLast(paciente);
    }

    /**Remueve al paciente de la lista de espera.*/
    public void sacaListaEspera(Paciente paciente)
    {
        listaEspera.remove(paciente);
    }

    /**Devuelve en un arreglo los pacientes que estan esperando en el patio**/
    public Patio getPatio()
    {
        return patio;
    }
    public SalaEsperaPrivada getSalaesperaPrivada()
    {
        return salaesperaprivada;
    }
    public  LinkedList<Consulta> getConsultaspendientes()
    {
        return consultaspendientes;
    }
    public LinkedList<Paciente> getListaEspera()
    {
        return listaEspera;
    }
}
