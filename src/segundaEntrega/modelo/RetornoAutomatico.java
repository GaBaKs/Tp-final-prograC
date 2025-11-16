package segundaEntrega.modelo;

import segundaEntrega.modelo.negocio.Ambulancia;

/**
 * Implementa {@link Runnable} para crear un hilo que se encargará de gestionar
 * el retorno automático de la ambulancia.
 * </p>
 * El ciclo de vida de este hilo es controlado por la bandera {@code simulacionActiva}.
 * </p>
 */
public class RetornoAutomatico implements Runnable{
    /** Bandera que me dice si la simulacion esta activa o no()*/
    protected boolean simulacionActiva;
    /** {@link Ambulancia } */
    Ambulancia ambulancia;

    public RetornoAutomatico(boolean simulacionactiva){
        this.simulacionActiva=simulacionactiva;
        ambulancia=Ambulancia.getInstance();
    }

    public void setSimulacionActiva(boolean simulacionactiva){
        this.simulacionActiva=simulacionactiva;
    }

    /**
     * Mientras {@code simulacionActiva} sea {@code true}, invoca repetidamente
     * el método {@link Ambulancia#retornoAutomatico()}.
     * </p>
     * Si el hilo es interrumpido, captura la
     * {@link InterruptedException} y la relanza como {@link RuntimeException}
     * para notificar el fallo.
     * </p>
     */
    @Override
    public void run() {
        while(simulacionActiva){
            try {
                this.ambulancia.retornoAutomatico();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
