package segundaEntrega.modelo;

import segundaEntrega.controlador.ControladorBD;
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
    /** {@link Operario } */
    public Operario operario;
    /** {@link Ambulancia } */
    private Ambulancia ambulancia; // Necesita saber de la ambulancia
    /** arraylist de asociados que estan en hilos concurrentes {@link Asociado }*/
    private ArrayList<Asociado> asociadosenHilos=new ArrayList();
    /** {@link RetornoAutomatico } */
    RetornoAutomatico retorno;

    public ModeloSimulacion(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;

        retorno=new RetornoAutomatico(true);
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
        int i=0;
        Iterator<Asociado> iterator = asociados.iterator(); // pido el iterator
        while (iterator.hasNext() && i<cantAsociados) {// mientras haya siguiente
            Asociado asociado = iterator.next();    // obtengo el siguiente
            Thread hilo = new Thread(asociado);
            asociado.setSimulacionActiva(true);
            asociado.setNumsolicitudes(cantsolicitudes);
            asociadosenHilos.add(asociado);
            hilo.start();
            i++;
        }
        Thread hiloRetorno = new Thread(retorno);
        hiloRetorno.start();
    }

    /**
     * Lanza el thread que representa al Operario.
     * <p>
     * El Operario inicia su ciclo de solicitudes de mantenimiento
     * a la {@code Ambulancia}.
     * </p>
     */
    public void solicitaMantenimiento(){
        Operario op = new Operario("2172654","Han Solo","matheu","Mar Del Plata","223");
        op.setSimulacionActiva(true);
        Thread hilo = new Thread(op);
        hilo.start();
    }

    /**
     * Finaliza la simulación concurrente.
     * <p>
     * Debe provocar que los bucles de los threads
     * {@code Asociado} y {@code Operario} terminen su ejecución de forma segura.
     * </p>
     */
    public void finalizar() {
        Iterator<Asociado> iterator = asociadosenHilos.iterator();
        retorno.setSimulacionActiva(false);

        while (iterator.hasNext()) {
            Asociado asociado = iterator.next();
            asociado.setSimulacionActiva(false);
        }

    }
}
