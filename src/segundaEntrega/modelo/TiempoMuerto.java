package segundaEntrega.modelo;

import java.util.Random;

public class TiempoMuerto {

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
