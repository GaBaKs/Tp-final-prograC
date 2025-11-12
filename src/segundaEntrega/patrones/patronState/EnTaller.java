package segundaEntrega.patrones.patronState;

import excepciones.EnTallerExcepcion;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;

public class EnTaller	implements IState {
    private Ambulancia ambulancia;

    public EnTaller(Ambulancia a)
    {
        this.ambulancia=a;
    }

    @Override
    public void pacienteSolicitaAtencion(Asociado asociado) {
    }

    @Override
    public void pacienteSolicitaTraslado(Asociado asociado) throws EnTallerExcepcion {throw new EnTallerExcepcion();}	// informa que no puede

    @Override
    public void retornoAutomatico() {}		//permanece en el mismo estado

    @Override
    public void solicitudMantenimiento()
    {
        this.ambulancia.ambulanciaState=new RegresandoTaller(this.ambulancia);
    }

}