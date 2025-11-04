package segundaEntrega.patrones.patronState;
import excepciones.RegresandoTallerExcepcion;

public class RegresandoTaller implements IState {
    private Ambulancia ambulancia;

    public RegresandoTaller(Ambulancia a)
    {
        this.ambulancia=a;
    }

    @Override
    public void pacienteSolicitaAtención() {}		//permanece en el mismo estado

    @Override
    public void pacienteSolicitaTraslado() throws RegresandoTallerExcepcion{throw new RegresandoTallerExcepcion();}		//informa que no puede

    @Override
    public void retornoAutomatico()
    {
        this.ambulancia.ambulanciaState=new Disponible(this.ambulancia);
    }

    @Override
    public void solicitudMantenimiento() throws RegresandoTallerExcepcion{throw new RegresandoTallerExcepcion();}		//informa que no puede

}
