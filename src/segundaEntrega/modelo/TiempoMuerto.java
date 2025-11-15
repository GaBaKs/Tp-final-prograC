package segundaEntrega.modelo;

import java.util.Random;

/**
 * Clase que simula tiempos muertos o periodos de inactividad (pausas) aleatorias.
 */
public class TiempoMuerto {

    /**
     * Detiene la ejecución del hilo actual por un periodo de tiempo aleatorio.
     * @throws InterruptedException Si el hilo actual es interrumpido por otro hilo
     * mientras está durmiendo. La excepción es relanzada
     * para indicar un fallo en el proceso de pausa.
     */
    public static void esperar() throws InterruptedException{
        Random random = new Random();
        int ms = 2000 + random.nextInt(3000); //
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new InterruptedException("Error en sleep");
        }
    }
}
