package segundaEntrega.patrones.patronState;

import excepciones.EnTallerExcepcion;

public class EnTaller	implements IState {
    private Ambulancia ambulancia;

    public EnTaller(Ambulancia a)
    {
        this.ambulancia=a;
    }

    @Override
    public void pacienteSolicitaAtención() {}		// permanece en el mismo estado

    @Override
    public void pacienteSolicitaTraslado() throws EnTallerExcepcion {throw new EnTallerExcepcion();}	// informa que no puede

    @Override
    public void retornoAutomatico() {}		//permanece en el mismo estado

    @Override
    public void solicitudMantenimiento()
    {
        this.ambulancia.ambulanciaState=new RegresandoTaller(this.ambulancia);
    }

}