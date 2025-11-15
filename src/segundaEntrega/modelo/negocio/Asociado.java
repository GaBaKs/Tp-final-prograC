package segundaEntrega.modelo.negocio;
import clinica.modelo.personas.Persona;
import java.util.Random;
import java.util.ArrayList;


public class Asociado extends Persona implements Runnable {
    protected int numsolicitudes;
    protected Ambulancia ambulancia;
    static boolean simulacionactiva;
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

    public void setNumasolicitudes(int numsolicitudes)
    {
        this.numsolicitudes = numsolicitudes;
        assert this.numsolicitudes>=0:"la cantidad de solicitudes no puede ser negativa";
    }

    @Override
    public void run() {
        int i=0;
        while( i < numsolicitudes && simulacionactiva){
            numero=random.nextInt(2) + 1;
            try {
                if (numero==1)
                 this.ambulancia.pacienteSolicitaAtencion(this);
                else
                    this.ambulancia.pacienteSolicitaTraslado(this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            i++;
        }
    }
    @Override
    public String toString() {
        return this.getN_A() + " (" + this.getDni() + ")";
    }

}