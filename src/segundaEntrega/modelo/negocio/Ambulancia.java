package segundaEntrega.modelo.negocio;

import excepciones.AtendiendoDomicilioExcepcion;
import excepciones.EnTallerExcepcion;
import excepciones.RegresandoSinPacienteExcepcion;
import excepciones.RegresandoTallerExcepcion;
import excepciones.TrasladandoPacienteExcepcion;
import segundaEntrega.patrones.patronState.Disponible;
import segundaEntrega.patrones.patronState.IState;

import java.util.Observable;

public class Ambulancia extends Observable {
    private Operario operario;
    protected IState ambulanciaState;
    //agregar paciente para su traslado
    protected Asociado asociado=null;
    protected boolean mantenimiento=false;
    private int numsolicitudes;

    public synchronized void pacienteSolicitaAtencion (Asociado asociado)
    {
        while (this.asociado != null || this.mantenimiento == true  )
        {
            try {
                this.setChanged();
                this.notifyObservers(asociado.getN_A() + "Esta esperando a ser atendido a domicilio por la ambulancia.");
                wait();
            } catch (InterruptedException e) {e.printStackTrace();}
        }
        this.ambulanciaState.pacienteSolicitaAtencion(asociado);
        this.asociado = asociado;
        this.setChanged();
        this.notifyObservers(asociado.getN_A()+" esta siendo atendido a domicilio por la ambulancia.");//en el state
        //no se como simular la atencion xd deberia hacerlo en otra funcion?
        setChanged();
        this.notifyObservers(asociado.getN_A()+" termino de ser atendido a domicilio por la ambulancia."); //esto iria en el state y habria q cambiar el asociado a null aca
        this.asociado=null;
        notifyAll();
    }

    public synchronized void pacienteSolicitaTraslado (Asociado asociado)
    {
        while (this.asociado != null || this.mantenimiento == true)
        {
            try {
                this.setChanged();
                this.notifyObservers(asociado.getN_A() + "Esta esperando a ser atendido a domicilio por la ambulancia.");
                wait();
            } catch (InterruptedException e) {e.printStackTrace();}
        }
        this.setChanged();
        this.asociado = asociado;
        this.ambulanciaState.pacienteSolicitaTraslado(asociado);
        this.notifyObservers(asociado.getN_A()+" esta siendo atendido a domicilio por la ambulancia."); //en el state
        setChanged();
        this.notifyObservers(asociado.getN_A()+" termino de ser atendido a domicilio por la ambulancia."); //idem y ahi tendria q cambiar el asociado a null
        this.asociado=null;
        notifyAll();
    }
    public synchronized void solicitaMantenimiento()
    {
        while (this.asociado!=null || this.mantenimiento == true )
        {
            try {
                this.setChanged();
                this.notifyObservers("El operario esta esperando para mandar la ambulancia a mantenimiento.");
                wait();
            } catch (InterruptedException e) {e.printStackTrace();}

        }
        this.setChanged();
        this.notifyObservers("El operario mando a la ambulancia a mantenimiento."); //creo q esto en el state
        this.mantenimiento = true;
        this.ambulanciaState.solicitudMantenimiento();
        this.mantenimiento = false;
        notifyAll();
    }

    public Ambulancia()
    {
        this.ambulanciaState=new Disponible(this);
    }

    public void pacienteSolicitaAtención() {	this.ambulanciaState.pacienteSolicitaAtencion(asociado);    }


    //fijarse si hacer try/catch o propaga la excepcion
    public void pacienteSolicitaTraslado() throws AtendiendoDomicilioExcepcion, TrasladandoPacienteExcepcion, EnTallerExcepcion, RegresandoTallerExcepcion
    {
        this.ambulanciaState.pacienteSolicitaTraslado();
    }


    public void retornoAutomatico() {		this.ambulanciaState.retornoAutomatico();    }


    //fijarse si hacer try/catch o propaga la excepcion
    public void solicitudMantenimiento() throws AtendiendoDomicilioExcepcion, TrasladandoPacienteExcepcion, RegresandoTallerExcepcion ,RegresandoSinPacienteExcepcion
    {
        this.ambulanciaState.solicitudMantenimiento();
    }


}
