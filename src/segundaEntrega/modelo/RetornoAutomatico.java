package segundaEntrega.modelo;

import segundaEntrega.modelo.negocio.Ambulancia;

public class RetornoAutomatico implements Runnable{

    protected boolean simulacionActiva;
    Ambulancia ambulancia;

    public RetornoAutomatico(boolean simulacionactiva){
        this.simulacionActiva=simulacionactiva;
        ambulancia=Ambulancia.getInstance();
    }

    public void setSimulacionActiva(boolean simulacionactiva){
        this.simulacionActiva=simulacionactiva;
    }


    @Override
    public void run() {
        while(simulacionActiva){
            try {
                this.ambulancia.retornoAutomatico();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
