package segundaEntrega.modelo;

import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;

import javax.swing.*;

public class InicioSimulacion {
    private int cantsolicitudes;
    public Ambulancia ambulancia = new Ambulancia();
    //esto esta todoo para el orto
        Thread asociado1 = new Thread(new Asociado("12345623","Pipo","Florisbelo 2738","Mar Del Plata","2235379123",cantsolicitudes,ambulancia));
        Thread asociado2 = new Thread(new Asociado("29371910","Pepe","Independencia 5792","Mar Del Plata","2235279023",4,ambulancia));
        Thread asociado3 = new Thread(new Asociado("45921891","Pepo","Chacabuco 3761","Mar Del Plata","2234379023",4,ambulancia));
        Thread asociado4 = new Thread(new Asociado("43861537","Papo","Brown 4829","Mar Del Plata","2235378023",4,ambulancia));
        Thread operario1 = new Thread(new Asociado("46919022","Pupo","Calle 4 290","Mar Del Plata","2235379023",4,ambulancia));
    static boolean simulacionactiva;

    public void inicia(int cantsolicitudes) {
        simulacionactiva = true;
        this.cantsolicitudes=cantsolicitudes;
        asociado1.start();
        asociado2.start();
        asociado3.start();
        asociado4.start();
        operario1.start();
    }

    public Ambulancia getAmbulancia() {
        return ambulancia;
    }

    public void finalizar() {
            simulacionactiva = false;
        //hay q hacer el tema este de finalizar la simulacion sin matar al thread y etc etc

    }
    public void solicitudMantenimiento(){
        operario1.start();
    }
}
