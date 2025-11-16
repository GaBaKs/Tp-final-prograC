package segundaEntrega.modelo;

import segundaEntrega.modelo.negocio.Ambulancia;

public class RetornoAutomatico extends Thread{

    protected boolean simulacionActiva;
    Ambulancia ambulancia;

    public RetornoAutomatico(boolean simulacionactiva){
        this.simulacionActiva=simulacionactiva;
        ambulancia=Ambulancia.getInstance();
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
