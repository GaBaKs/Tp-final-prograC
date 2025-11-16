package segundaEntrega.vista;


import segundaEntrega.modelo.negocio.Ambulancia;

import java.util.Observable;
import java.util.Observer;
/**La clase MiObserverVista implementa el patrón Observer y actúa como un puente
 * entre el modelo (Ambulancia) y la vista (IVistaSimulacion).
 */
public class MiObserverVista implements Observer
{
    /**
     * Referencia a la vista donde se mostrarán los cambios de estado y mensajes.
     * Esta relación es de composición: la vista es necesaria para que este observer funcione.
     */
    IVistaSimulacion JframeSimulacion;      // COMPOSICION con la vista
    Ambulancia observado;



    /**
     * Constructor que asocia la vista y el objeto observado.
     * Registra esta instancia como observadora de la ambulancia.
     *
     * @param jframeSimulacion vista que será actualizada
     * @param ambulancia objeto del modelo que será observado
     */
    public MiObserverVista(IVistaSimulacion jframeSimulacion,Ambulancia ambulancia) {
        JframeSimulacion = jframeSimulacion;
        observado = ambulancia;
        observado.addObserver(this);
    }


    /**
     * Metodo invocado automáticamente cada vez que la ambulancia notifica un cambio.
     *
     * @param ambulancia el observable que generó la notificación
     * @param mensaje un objeto opcional enviado por el observable (en este caso un String)
     */
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
