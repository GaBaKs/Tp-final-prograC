package segundaEntrega.modelo.negocio;


import clinica.modelo.personas.Persona;
import segundaEntrega.controlador.ControladorBD;
import segundaEntrega.modelo.TiempoMuerto;
import segundaEntrega.patrones.patronState.Disponible;
import segundaEntrega.patrones.patronState.IState;
import segundaEntrega.persistencia.basededatos.ConexionBD;

import java.util.Observable;

/**
 * Esta clase Ambulancia es el recurso compartido del sistema que
 * va a hacer de monitor para gestionar la concurrencia.
 * Solo un hilo puede acceder a la ambulancia a la vez.
 * También actúa como el Contexto para el Patrón State, delegando su comportamiento
 * al objeto IState que represente su estado actual.
 * Finalmente, extiende Observable para notificar a la Vista sobre cambios de estado y acciones.
 */
public class Ambulancia extends Observable {
    /** estado actual de la ambulancia*/
    protected IState ambulanciaState; //PatronState, el estado actual
    /** string de estado actual de la ambulancia*/
    private String estadoString; //dto simple para notificar a la vista
    /** atributo utilizado para el singleton de la ambulancia*/
    private static Ambulancia instance;
    /** {@link Asociado } */
    protected Asociado asociado;        // El recurso "ocupado" (si no es null, está ocupada)
    /** flag que me dice si la ambulancia esta disponible o no(true== esta disponible)*/
    protected boolean disponible;      // Flag de estado (manejado por el State)
    /** flag que me dice si la ambulancia esta en mantenimiento o no(true== esta en mantenimiento)*/
    protected boolean estaMantenimiento;  // Flag de estado (manejado por el State)
    /** flag que me dice si la ambulancia esta en regresando o no(true== esta regresando)*/
    protected boolean estaRegresando;

    private Ambulancia()
    {
        this.ambulanciaState=new Disponible(this);
        this.disponible=true;
        this.estaMantenimiento=false;
        this.asociado=null;
        this.estaRegresando=false;
    }

    public static Ambulancia getInstance() {
        if (instance == null) {
            instance = new Ambulancia();
        }
        return instance;
    }


    /**
     * Intenta atender a un asociado a domicilio.
     * Es un metodo synchronized.
     *
     * @param asociado El hilo del asociado que solicita la atención.
     * @throws InterruptedException Si el hilo es interrumpido mientras está en wait().
     */
    public synchronized void pacienteSolicitaAtencion (Asociado asociado) throws InterruptedException
    {
        while(!this.disponible)
            wait();
        this.ambulanciaState.pacienteSolicitaAtencion(asociado); // Delega al estado (Patrón State)
        notifyAll(); // Despierta a TODOS los hilos
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
             while (!this.disponible)
             {
                this.setChanged();
                this.notifyObservers(asociado.getN_A() + " esta esperando a ser trasladado por la ambulancia.");
                wait();
            }
        assert !this.disponible: "Post-condición de wait() violada";

        this.ambulanciaState.pacienteSolicitaAtencion(asociado); //Delega el traslado al State
        notifyAll();
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
        while (!(!this.estaRegresando && (this.disponible && !this.estaMantenimiento) || (!this.disponible && this.estaMantenimiento))){
            this.setChanged();
            this.notifyObservers("El operario esta esperando para mandar la ambulancia a mantenimiento.");
            wait();
        }
        assert !(!this.estaRegresando && (this.disponible && !this.estaMantenimiento) || (!this.disponible && this.estaMantenimiento)): "Post-condición de wait() violada";
        this.ambulanciaState.solicitudMantenimiento();
        notifyAll();
    }


    /**
     * Metodo llamado por el estado para accionar el evento de "retorno automático".
     */
    public synchronized void retornoAutomatico() throws InterruptedException
    {
        while (this.disponible && !this.estaMantenimiento && !this.estaRegresando)
        {
            wait();
        }
        this.ambulanciaState.retornoAutomatico();
        notifyAll();
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

    /**
     * Flag de control del Monitor, gestionado por los Estados.
     * @param condicion true si está en regresando a la clinica, false si no.
     */
    public void setEstaRegresando(boolean condicion){
        this.estaRegresando=condicion;
    }

    public void llamaobserver(String arg){
        this.setChanged();
        this.notifyObservers(arg);
    }

    public void setAsociado(Asociado asociado){
        this.asociado=asociado;
    }

    public Asociado getAsociado(){
        return  this.asociado;
    }

}
