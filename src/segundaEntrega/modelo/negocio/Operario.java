package segundaEntrega.modelo.negocio;

import clinica.modelo.personas.Persona;

public class Operario extends Persona implements Runnable{
    protected int numsolicitudes;
    protected Ambulancia ambulancia;

    public Operario(String dni, String n_A, String domicilio, String ciudad, String telefono, int numsolicitudes) {
        super(dni, n_A, domicilio, ciudad, telefono);
        this.numsolicitudes = numsolicitudes;
    }

    public Ambulancia getAmbulancia() {
        return ambulancia;
    }
    public void setAmbulancia(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void run() {
        for(int i=0;i<numsolicitudes;i++){
            this.ambulancia.solicitudMantenimiento(this);
        }
    }
}