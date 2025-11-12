package segundaEntrega.patrones.patronState;
import excepciones.AtendiendoDomicilioExcepcion;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;

public class AtendiendoDomicilio implements IState {
    private Ambulancia ambulancia;

    public AtendiendoDomicilio(Ambulancia a)
    {
        this.ambulancia=a;

    }

    @Override
    public void pacienteSolicitaAtención() {}		// permanece en el mismo estado

    @Override
    public void pacienteSolicitaAtencion(Asociado asociado) {

    }
    @Override
    public void pacienteSolicitaTraslado() throws AtendiendoDomicilioExcepcion  {throw new AtendiendoDomicilioExcepcion();}		//informa que no puede

    @Override
    public void retornoAutomatico()
    {
        this.ambulancia.ambulanciaState=new RegresandoSinPaciente(this.ambulancia);
    }

    @Override
    public void solicitudMantenimiento() throws AtendiendoDomicilioExcepcion  {throw new AtendiendoDomicilioExcepcion();}			//informa que no puede


}
