package segundaEntrega.modelo.negocio;


import segundaEntrega.modelo.TiempoMuerto;
import segundaEntrega.patrones.patronState.Disponible;
import segundaEntrega.patrones.patronState.IState;

import java.util.Observable;

public class Ambulancia extends Observable {
    protected IState ambulanciaState;
    private String estadoString;

    //agregar paciente para su traslado
    protected Asociado asociado=null;
    protected boolean disponible =false;
    protected boolean estaMantenimiento=false;
    private int numsolicitudes;

    public Ambulancia()
    {

        this.ambulanciaState=new Disponible(this);
    }



    public synchronized void pacienteSolicitaAtencion (Asociado asociado) throws InterruptedException
    {
        while (this.asociado != null  || !this.disponible || this.estaMantenimiento) {
            this.setChanged();
            this.notifyObservers(asociado.getN_A() + "Esta esperando a ser atendido a domicilio");
            wait();
        }
        // si salimos del bucle, esto tiene que ser verdad
        assert this.asociado == null && this.disponible && !this.estaMantenimiento : "Post-condición de wait() violada";
        this.ambulanciaState.pacienteSolicitaAtencion(asociado);
        this.asociado = asociado;
        this.setChanged();
        this.notifyObservers(asociado.getN_A()+" esta siendo atendido a domicilio por la ambulancia.");
        TiempoMuerto.esperar();
        this.asociado=null;
        notifyAll();

    }

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
        this.ambulanciaState.pacienteSolicitaAtencion(asociado);
        this.asociado = asociado;
        this.setChanged();
        this.notifyObservers(asociado.getN_A()+" esta siendo trasladado por la ambulancia.");
        TiempoMuerto.esperar();
        this.asociado=null;
        notifyAll();
    }


    public synchronized void solicitaMantenimiento(Operario operario) throws InterruptedException
    {
        while (this.asociado != null || !this.disponible || this.estaMantenimiento) {
            this.setChanged();
            this.notifyObservers(operario.getN_A()+"El operario esta esperando para mandar la ambulancia a mantenimiento.");
            wait();
        }
        // si salimos del bucle, esto tiene que ser verdad
        assert this.asociado == null && this.disponible && !this.estaMantenimiento : "Post-condición de wait() violada";
        this.setChanged();
        this.notifyObservers("El operario mando a la ambulancia a mantenimiento.");
        this.ambulanciaState.solicitudMantenimiento();
        TiempoMuerto.esperar();
        notifyAll();
    }



    public void retornoAutomatico() {
            this.ambulanciaState.retornoAutomatico();
    }

    public IState getAmbulanciaState() {return this.ambulanciaState;}

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

    public void setDisponible(boolean condicion)
    {
        assert this != null : "El contexto (Ambulancia) no puede ser nulo";
        this.disponible =condicion;
    }

    public void setEstaMantenimiento(boolean condicion)
    {
        this.estaMantenimiento=condicion;
    }


}
