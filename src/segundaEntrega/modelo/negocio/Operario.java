package segundaEntrega.modelo.negocio;

import clinica.modelo.personas.Persona;

public class Operario extends Persona implements Runnable{
    protected int numsolicitudes;
    protected Ambulancia ambulancia;
    static boolean simulacionactiva;

    public Operario(String dni, String n_A, String domicilio, String ciudad, String telefono, int numsolicitudes) {
        super(dni, n_A, domicilio, ciudad, telefono);
        this.numsolicitudes = numsolicitudes;
    }

    public Ambulancia getAmbulancia() {
        return ambulancia;
    }

    public void setAmbulancia(Ambulancia ambulancia)
    {
        this.ambulancia = ambulancia;
        assert ambulancia!=null :"no existe la ambulancia";
    }

    @Override
    public void run() {
        int i=0;
        while(i<numsolicitudes && simulacionactiva){
            try {
                this.ambulancia.solicitaMantenimiento(this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            i++;
        }
    }
}