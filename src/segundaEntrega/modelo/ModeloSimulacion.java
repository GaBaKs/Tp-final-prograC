package segundaEntrega.modelo;

import segundaEntrega.modelo.negocio.Asociado;
import segundaEntrega.modelo.negocio.Operario;

import java.util.ArrayList;
import java.util.Iterator;

public class ModeloSimulacion {
    static boolean simulacionactiva;
    public Operario operario;

    public ModeloSimulacion() {
    }


    public void inicia(int cantsolicitudes, ArrayList<Asociado> asociados) {
        simulacionactiva = true;
        Iterator<Asociado> iterator = asociados.iterator(); // pido el iterator

        while (iterator.hasNext()) {// mientras haya siguiente
            Asociado asociado = iterator.next();    // obtengo el siguiente
            Thread hilo = new Thread(asociado);
            asociado.setNumsolicitudes(cantsolicitudes);
            hilo.start();
        }
    }
    public void solicitaMantenimiento(){
        Thread hilo = new Thread(this.operario);
        hilo.start();
    }
    public void finalizar() {
        simulacionactiva = false;

    }
}
