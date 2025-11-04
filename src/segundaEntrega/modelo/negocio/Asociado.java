package segundaEntrega.modelo.negocio;
import clinica.modelo.personas.Persona;

import java.util.ArrayList;

public class Asociado extends Persona implements Runnable {
    protected int numsolicitudes;
    protected Ambulancia ambulancia;


    public Asociado(String dni, String n_A, String domicilio, String ciudad,String telefono,int numsolicitudes, Ambulancia ambulancia) {
        super(dni,n_A,domicilio,ciudad,telefono);
        this.numsolicitudes = numsolicitudes;
        this.ambulancia = ambulancia;
    }
    public int getNumasolicitudes() {
        return numsolicitudes;
    }
    public void setNumasolicitudes(int numsolicitudes) {
        this.numsolicitudes = numsolicitudes;
    }
    @Override
    public void run() {
        for(int i=0;i<numsolicitudes;i++){
            this.ambulancia.pacienteSolicitaAtencion(this);
        }
    }
}