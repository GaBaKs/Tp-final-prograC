package clinica.patrones.facade;
import clinica.modelo.facturacion.Factura;
import clinica.modelo.facturacion.ReporteMedico;
import clinica.modelo.habitacion.Habitacion;
import clinica.modelo.personas.medicos.IMedico;
import clinica.modelo.personas.medicos.Medico;
import clinica.modulos.ProcesoPacientes;
import clinica.modulos.atencion.AtencionPaciente;
import clinica.modulos.ingreso.IngresoPacientes;
import clinica.modelo.personas.pacientes.Paciente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import excepciones.*;

/** Simula el sistema para interactuar con el programa aplicando el patron Facade. */
public class Sistema {
    /**Arreglo de medicos registrados en la clinica.*/
    private ArrayList<IMedico> medicosistema;

    private ArrayList<Factura> facturas;

    /**Arreglo de pacientes registrados en la clinica. */
    private ArrayList <Paciente> pacientesistema;

    /**Modulo que contiene los metodos para ingresar pacientes a la clinica.*/
    private IngresoPacientes modIngresoPacientes;
    /**Modulo que contiene los metodos para atender pacientes de la clinica.*/
    private AtencionPaciente modAtencionPacientes;


    ProcesoPacientes modProcesoPacientes;

    ReporteMedico reporteMedico;

    public Sistema() {
        medicosistema =new ArrayList<>();
        pacientesistema= new ArrayList<>();
        modProcesoPacientes=new ProcesoPacientes();
        modIngresoPacientes =new IngresoPacientes(modProcesoPacientes);
        modAtencionPacientes=new AtencionPaciente(modProcesoPacientes);
        facturas=new ArrayList<>();
        reporteMedico=new ReporteMedico();
    }

    /**
     * Metodo que registra al paciente en la base de datos de la clinica.
     * @param p1
     * @throws DuplicadoException
     */
	public void registraPaciente(Paciente p1) throws DuplicadoException {    // ingresa al sistema
       if (pacientesistema.contains(p1))
           throw new DuplicadoException("Paciente ya existe");
       else
        pacientesistema.add(p1);
    }

    /**
     * Se registra un medico en la base de datos de la clinica, si este ya estaba previamente registrado se lanza una excepcion.
     * @param p1
     * @throws DuplicadoException
     */
    public void registraMedico(IMedico p1) throws DuplicadoException{
        if (medicosistema.contains(p1))
            throw new DuplicadoException("Medico duplicado");
        else
            medicosistema.add(p1);
    }

    /**
     * Se registra un paciente en la lista de espera, si este no se encuentra en la base de datos de la clinica se lanza una excepcion.
     * @param p1
     * @throws NoEstaEnSistemaException
     */
    public void ingresaPaciente(Paciente p1) throws NoEstaEnSistemaException
    {   // si ya esta en el sistema, lo mete a la lista de espera
        if (pacientesistema.contains(p1)) {
            modIngresoPacientes.ingresaPaciente(p1);
            modProcesoPacientes.agregaListaEspera(p1);
        }
        else
            throw new NoEstaEnSistemaException("El paciente ingresado no esta en el sistema.");
	}

    /**
     * Un medico atiende al paciente que se encontraba en la lista de espera,
     * si el medico no se encuentra en el sistema o el paciente no esta en la lista de espera se lanzan excepciones.
     * @param m1
     * @param p1
     * @throws NoEstaEnSistemaException
     * @throws NoEstaEnListaDeEsperaException
     */
    public void atiendePaciente(Medico m1,Paciente p1) throws NoEstaEnSistemaException,NoEstaEnListaDeEsperaException{
        if (!(medicosistema.contains(m1)))
            throw new NoEstaEnSistemaException("Medico no esta en el sistema");
        else
            if (modProcesoPacientes.getListaEspera().contains(p1)){
                 modAtencionPacientes.atiendePaciente(m1,p1);
             }
            else
                 throw new NoEstaEnListaDeEsperaException("El paciente no se encuentra en la lista de espera");
    }

    /**
     * Se le delega la internacion del paciente al modulo de atencion de pacientes.
     * @param p1
     * @param h1
     */
    public void internaPaciente(Paciente p1, Habitacion h1, int cantdias){
        if (modAtencionPacientes.tienePaciente(p1))
        {
            modProcesoPacientes.internaPaciente(p1,h1,cantdias);
        }
    }

    /**
     * Se le da el alta al paciente, sacandolo de la lista de espera y genera la factura correspondiente,
     * si el paciente no se encuentra en la lista de atendidos se lanza una excepcion.
     * @param p1
     * @throws NoEstaEnSistemaException
     * @throws NoEstaEnListaDeAtencionException
     */
    public Factura egresaPaciente(Paciente p1) throws NoEstaEnSistemaException,NoEstaEnListaDeAtencionException
    {
       if  (modAtencionPacientes.tienePaciente(p1))
       {
           modAtencionPacientes.sacaPacienteAtencion(p1);
           Factura facaux= modProcesoPacientes.egresaPaciente(p1);
           facturas.add(facaux);
           facturas.sort(Comparator.comparing(Factura::getFechaEgreso));
           return facaux;
       }
       else
           throw new NoEstaEnListaDeAtencionException("El paciente no se encuentra en la lista de atencion, no es posible egresarlo");
    }

    /**
     * Genera el reporte medico asociado a la consulta correspondiente.
     * @param m1
     * @param desde
     * @param hasta
     * @return Reporte
     */
    public String generaReporteMedico(IMedico m1,LocalDate desde,LocalDate hasta){
        return reporteMedico.generaReporteMedico(facturas,m1,desde,hasta);
    }



}
