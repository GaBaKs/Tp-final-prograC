package segundaEntrega.vista;


import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.patrones.patronObserver.Observador;
import segundaEntrega.patrones.patronState.IState;

import java.util.Observable;
import java.util.Observer;

public class MiObserverVista implements Observer
{
    IVistaSimulacion JframeSimulacion;      // COMPOSICION con la vista
    Ambulancia observado;



    public MiObserverVista(IVistaSimulacion jframeSimulacion,Ambulancia ambulancia) {
        JframeSimulacion = jframeSimulacion;
        observado = ambulancia;
        observado.addObserver(this);
    }


    @Override
    public void update(Observable ambulancia, Object stateDTO) {
       if (ambulancia==observado) {
           String estado = observado.getEstadoString();
           JframeSimulacion.setEstadoAmbulancia(estado);
       }
        else
           throw new IllegalArgumentException();
       // hacer if de los hilos para mostrarlos, tmb con el mismo else arraylist de threads
    }
}
