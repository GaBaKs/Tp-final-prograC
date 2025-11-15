package segundaEntrega.modelo;

import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class InicioSimulacion {
    public Ambulancia ambulancia = new Ambulancia();
    static boolean simulacionactiva;


    public void inicia(int cantsolicitudes, ArrayList<Asociado> asociados) {
        simulacionactiva = true;
        Iterator<Asociado> iterator = asociados.iterator(); // pido el iterator

        while (iterator.hasNext()) {          // mientras haya siguiente
            Asociado asociado = iterator.next();    // obtengo el siguiente
            asociado.start();
        }
    }

    public Ambulancia getAmbulancia() {
        return ambulancia;
    }

    public void finalizar() {
            simulacionactiva = false;

    }
    public void solicitudMantenimiento(){
        operario1.start();
    }
}
