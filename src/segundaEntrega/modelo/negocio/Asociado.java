package segundaEntrega.modelo.negocio;
import clinica.modelo.personas.Persona;
import java.util.Random;
import java.util.ArrayList;

/**
 * Representa a un Asociado como un hilo (Runnable) que activamente
 * solicita servicios a la ambulancia.
 * Hereda de Persona (Datos) e implementa Runnable (Lógica de Hilo).
 */
public class Asociado extends Persona implements Runnable {
    protected int numsolicitudes;
    protected Ambulancia ambulancia;// El monitor (recurso compartido) que necesita
    protected boolean simulacionactiva; // Flag para detener el hilo de forma segura
    Random random = new Random();
    int numero;

    public Asociado(String dni, String n_A, String domicilio, String ciudad,String telefono,int numsolicitudes, Ambulancia ambulancia) {
        super(dni,n_A,domicilio,ciudad,telefono);
        this.numsolicitudes = numsolicitudes;
        this.ambulancia = ambulancia;
        assert ambulancia!=null :"no existe la ambulancia";
    }

    public int getNumasolicitudes() {
        return numsolicitudes;
    }

    public void setNumsolicitudes(int numsolicitudes)
    {
        this.numsolicitudes = numsolicitudes;
        assert this.numsolicitudes>=0:"la cantidad de solicitudes no puede ser negativa";
    }
    /**
     * Lógica principal del hilo del Asociado.
     * Se ejecuta cuando se llama a HiloAsociado.start().
     * Simula solicitudes a la ambulancia hasta que se cumple el número
     * de solicitudes o la simulación finaliza.
     */
    @Override
    public void run() {
        int i=0;
        // El hilo vive mientras la simulación esté activa y le queden solicitudes
        while( simulacionactiva && i < numsolicitudes){
            numero=random.nextInt(2) + 1; //Decide aleatoriamente que tipo de solicitud pide
            try {
                if (numero==1)
                 this.ambulancia.pacienteSolicitaAtencion(this);
                else
                    this.ambulancia.pacienteSolicitaTraslado(this);
            } catch (InterruptedException e) {
                // Si el hilo es interrumpido (ej. al finalizar),
                // lo envolvemos en una RuntimeException para detenerlo.
                throw new RuntimeException(e);
            }
            i++;
        }
    }
    /**
     * Proporciona una representación textual del Asociado para ser
     * mostrada en la JList de la vista.
     * @return El nombre y DNI del asociado.
     */
    @Override
    public String toString() {
        return this.getN_A() + " (" + this.getDni() + ")";
    }

    public void setSimulacionActiva(boolean simulacionactiva) {
        this.simulacionactiva = simulacionactiva;
    }
}