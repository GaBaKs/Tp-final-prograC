package segundaEntrega.modelo.negocio;

import clinica.modelo.personas.Persona;

/**
 * Representa un Operario que extiende la funcionalidad de Persona y actúa como un hilo (Thread).
 * El Operario es responsable de simular la solicitud de mantenimiento de la {@link Ambulancia}
 * de forma concurrente, mientras la simulación esté activa.
 */
public class Operario extends Persona implements Runnable{
    protected Ambulancia ambulancia;
    static boolean simulacionactiva;

    public Operario(String dni, String n_A, String domicilio, String ciudad, String telefono) {
        super(dni, n_A, domicilio, ciudad, telefono);
    }

    public Ambulancia getAmbulancia() {
        return ambulancia;
    }

    public void setAmbulancia(Ambulancia ambulancia)
    {
        this.ambulancia = ambulancia;
        assert ambulancia!=null :"no existe la ambulancia";
    }

    /**
     * Lógica principal del hilo del Operario.
     * Simula solicitudes de mantenimiento hasta que se cumple el número
     * de solicitudes o la simulación finaliza.
     */
    @Override
    public void run() {
        int i=0;
        while(simulacionactiva){
            try {
                // El operario solo solicita mantenimiento
                this.ambulancia.solicitaMantenimiento(this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            i++;
        }
    }
}