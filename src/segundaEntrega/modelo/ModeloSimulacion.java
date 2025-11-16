package segundaEntrega.modelo;

import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;
import segundaEntrega.modelo.negocio.Operario;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase encargada de iniciar, gestionar y finalizar la simulación concurrente
 * de los Asociados y el Operario del sistema.
 * <p>
 * Actúa como un gestor de threads, asegurando que todos comiencen su ejecución
 * en la capa de negocio y reaccionen al cambio de estado de la simulación.
 * </p>
 */
public class ModeloSimulacion {
    public volatile boolean simulacionactiva;
    public Operario operario;
    private Ambulancia ambulancia; // Necesita saber de la ambulancia
    private ArrayList<Asociado> asociadosenHilos=new ArrayList();


    public ModeloSimulacion(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;

    }


    /**
     * Inicia la simulación concurrente de los asociados.
     * <p>
     * Establece la bandera global de simulación a {@code true}, asigna el número de
     * solicitudes a cada asociado y lanza un hilo de ejecución para cada uno.
     * </p>
     *
     * @param cantsolicitudes El número de solicitudes que cada Asociado debe realizar.
     * @param asociados       {@code Asociado} que serán threads.
     */

    public void inicia(int cantsolicitudes, int cantAsociados, ArrayList<Asociado> asociados) {
        simulacionactiva = true; // Pone en 'true' la bandera DE ESTA INSTANCIA
        int i=0;
        Iterator<Asociado> iterator = asociados.iterator(); // pido el iterator
        while (iterator.hasNext() && i<cantAsociados) {// mientras haya siguiente
            Asociado asociado = iterator.next();    // obtengo el siguiente
            Thread hilo = new Thread(asociado);
            asociadosenHilos.add(asociado);
            asociado.setSimulacionActiva(true);
            asociado.setNumsolicitudes(cantsolicitudes);
            hilo.start();
            i++;
        }
        Thread retorno=new RetornoAutomatico(true);
        retorno.start();
    }

    /**
     * Lanza el thread de ejecución del Operario.
     * <p>
     * El Operario inicia su ciclo de solicitudes de mantenimiento
     * a la {@code Ambulancia}.
     * </p>
     */
    public void solicitaMantenimiento(){
        simulacionactiva=true;
        Operario op = new Operario("2172654","Han Solo","matheu","Mar Del Plata","223");
        op.setSimulacionActiva(true);
        Thread hilo = new Thread(op);
        hilo.start();
    }

    /**
     * Finaliza la simulación concurrente.
     * <p>
     * Establece la bandera {@code simulacionactiva} a {@code false}, lo que
     * debe provocar que los bucles {@code while(simulacionactiva)} de los threads
     * {@code Asociado} y {@code Operario} terminen su ejecución de forma segura.
     * </p>
     */
    public void finalizar() {
        simulacionactiva = false;
        operario.setSimulacionActiva(false);
        Iterator<Asociado> iterator = asociadosenHilos.iterator();

        while (iterator.hasNext()) {
            Asociado asociado = iterator.next();
            asociado.setSimulacionActiva(false);
        }

    }
}
