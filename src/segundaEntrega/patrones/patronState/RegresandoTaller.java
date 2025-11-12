package segundaEntrega.patrones.patronState;
import excepciones.RegresandoTallerExcepcion;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;

public class RegresandoTaller implements IState {
    private Ambulancia ambulancia;

    public RegresandoTaller(Ambulancia a)
    {
        this.ambulancia=a;
    }

    @Override
    public void pacienteSolicitaAtencion(Asociado asociado) {

    }

    @Override
    public void pacienteSolicitaTraslado(Asociado asociado) throws RegresandoTallerExcepcion{throw new RegresandoTallerExcepcion();}		//informa que no puede

    @Override
    public void retornoAutomatico()
    {
        this.ambulancia.ambulanciaState=new Disponible(this.ambulancia);
    }

    @Override
    public void solicitudMantenimiento() throws RegresandoTallerExcepcion{throw new RegresandoTallerExcepcion();}		//informa que no puede

}
