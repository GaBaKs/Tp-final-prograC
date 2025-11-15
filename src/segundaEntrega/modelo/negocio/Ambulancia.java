package segundaEntrega.modelo.negocio;


import segundaEntrega.modelo.TiempoMuerto;
import segundaEntrega.patrones.patronState.Disponible;
import segundaEntrega.patrones.patronState.IState;
import segundaEntrega.persistencia.basededatos.ConexionBD;

import java.util.Observable;
/**
 * Esta clase Ambulancia es el recurso compartido del sistema
 * * va a implementar el patrón Monitor para gestionar la concurrencia. Solo un hilo (Thread)
 * (Asociado u Operario) puede acceder a la ambulancia a la vez.
 * * También actúa como el Contexto para el Patrón State, delegando su comportamiento
 * al objeto IState que represente su estado actual (ej. Disponible, EnTaller, etc.).
 * * Finalmente, extiende Observable (Patrón Observer) para notificar a la Vista
 * (ControladorSimulacion) sobre cambios de estado y acciones.
 */
public class Ambulancia extends Observable {
    protected IState ambulanciaState; //PatronState, el estado actual
    private String estadoString; //dto simple para notificar a la vista

    private static Ambulancia instance;

    // Atributos de control del Monitor
    protected Asociado asociado = null;        // El recurso "ocupado" (si no es null, está ocupada)
    protected boolean disponible = false;      // Flag de estado (manejado por el State)
    protected boolean estaMantenimiento = false; // Flag de estado (manejado por el State)

    private Ambulancia()
    {
        // La ambulancia siempre inicia en estado Disponible
        this.ambulanciaState=new Disponible(this);
    }

    public static Ambulancia getInstance() {
        if (instance == null) {
            instance = new Ambulancia();
        }
        return instance;
    }


    /**
     * Intenta atender a un asociado a domicilio.
     * Es un metodo synchronized (Monitor) para garantizar acceso exclusivo.
     *
     * @param asociado El hilo del asociado que solicita la atención.
     * @throws InterruptedException Si el hilo es interrumpido mientras está en wait().
     */
    public synchronized void pacienteSolicitaAtencion (Asociado asociado) throws InterruptedException
    {
        // El hilo debe esperar si la ambulancia está:
        // 1. Ocupada por otro asociado (asociado != null)
        // 2. No disponible (ej. regresando)
        // 3. En mantenimiento
        while (this.asociado != null  || !this.disponible || this.estaMantenimiento) {
            this.setChanged();
            this.notifyObservers(asociado.getN_A() + "Esta esperando a ser atendido a domicilio");
            wait(); // Libera el lock (la llave) y se pone a dormir
        }
        // si salimos del bucle, esto tiene que ser verdad
        assert this.asociado == null && this.disponible && !this.estaMantenimiento : "Post-condición de wait() violada";
        this.ambulanciaState.pacienteSolicitaAtencion(asociado); // Delega al estado (Patrón State)
        this.asociado = asociado; //Toma el recurso
        this.setChanged();
        this.notifyObservers(asociado.getN_A()+" esta siendo atendido a domicilio por la ambulancia.");
        TiempoMuerto.esperar(); // Simula el tiempo de la atención
        this.asociado=null;// Libera el recurso
        notifyAll(); // Despierta a TODOS los hilos (Asociados y Operarios) que esperaban

    }
    /**
     * Intenta trasladar a un asociado a la clínica.
     * Lógica de Monitor idéntica a pacienteSolicitaAtencion.
     *
     * @param asociado El hilo del asociado que solicita el traslado.
     * @throws InterruptedException Si el hilo es interrumpido.
     */
    public synchronized void pacienteSolicitaTraslado (Asociado asociado) throws InterruptedException
    {
             while (this.asociado != null || !disponible ||  this.estaMantenimiento)
             {
                this.setChanged();
                this.notifyObservers(asociado.getN_A() + "Esta esperando a ser trasladado por la ambulancia.");
                wait();
            }
        // si salimos del bucle, esto tiene que ser verdad
        assert this.asociado == null && this.disponible && !this.estaMantenimiento : "Post-condición de wait() violada";
        this.ambulanciaState.pacienteSolicitaAtencion(asociado); //Delega el traslado al State
        this.asociado = asociado; //Toma el recurso
        this.setChanged();
        this.notifyObservers(asociado.getN_A()+" esta siendo trasladado por la ambulancia.");
        TiempoMuerto.esperar();
        this.asociado=null; //Libera el recurso
        notifyAll(); //Despierta a todos los hilos en espera
    }

    /**
     * Intenta enviar la ambulancia a mantenimiento.
     * Lógica de Monitor idéntica a las solicitudes de asociado.
     *
     * @param operario El hilo del operario que solicita el mantenimiento.
     * @throws InterruptedException Si el hilo es interrumpido.
     */
    public synchronized void solicitaMantenimiento(Operario operario) throws InterruptedException
    {
        while (this.asociado != null || !this.disponible || this.estaMantenimiento) {
            this.setChanged();
            this.notifyObservers(operario.getN_A()+"El operario esta esperando para mandar la ambulancia a mantenimiento.");
            wait();
        }
        // si salimos del bucle, esto tiene que ser verdad
        assert this.asociado == null && this.disponible && !this.estaMantenimiento : "Post-condición de wait() violada";
        this.ambulanciaState.solicitudMantenimiento(); //Delega el estado
        this.setChanged();
        this.notifyObservers("El operario mando a la ambulancia a mantenimiento.");
        TiempoMuerto.esperar(); // Simula el tiempo que tarda en ir al taller
        notifyAll(); // Despierta a otros hilos (aunque la ambulancia seguirá no-disponible)
    }


    /**
     * Metodo llamado por el estado para simular el evento de "retorno automático"
     * (ej. fin de traslado o fin de mantenimiento).
     */
    public void retornoAutomatico() {
            this.ambulanciaState.retornoAutomatico();
    }


    public IState getAmbulanciaState() {return this.ambulanciaState;}
    /**
     * Setter para el patrón State. Permite a los estados cambiar el estado del contexto.
     * @param ambulanciaState El nuevo estado.
     */
    public void setAmbulanciaState(IState ambulanciaState)
    {
        this.ambulanciaState = ambulanciaState;
        assert ambulanciaState!=null:"la ambulancia no puede tener un estado nulo";
    }

    public String getEstadoString() {
        return estadoString;
    }
    public void setEstadoString(String estadoString)
    {
        this.estadoString = estadoString;
        assert estadoString!=null:"no se puede asignar un string nulo";
    }
    /**
     * Flag de control del Monitor, gestionado por los Estados.
     * @param condicion true si está disponible, false si no.
     */
    public void setDisponible(boolean condicion)
    {
        assert this != null : "El contexto (Ambulancia) no puede ser nulo";
        this.disponible =condicion;
    }
    /**
     * Flag de control del Monitor, gestionado por los Estados.
     * @param condicion true si está en mantenimiento, false si no.
     */
    public void setEstaMantenimiento(boolean condicion)
    {
        this.estaMantenimiento=condicion;
    }


}
