package segundaEntrega.vista;


import segundaEntrega.modelo.negocio.Ambulancia;

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
    public void update(Observable ambulancia, Object mensaje) {
        if (ambulancia==observado) {
            String estado = observado.getEstadoString();
            JframeSimulacion.setEstadoAmbulancia(estado);
            String mensajeConsola= (String) mensaje;
            JframeSimulacion.appendMovimientosAsociados(mensajeConsola);
        }
        else
            throw new IllegalArgumentException();
        
    }
}
