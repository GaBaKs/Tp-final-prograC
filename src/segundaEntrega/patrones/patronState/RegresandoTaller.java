package segundaEntrega.patrones.patronState;
import excepciones.RegresandoTallerExcepcion;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;

public class RegresandoTaller implements IState {
    private Ambulancia ambulancia;

    public RegresandoTaller(Ambulancia a)
    {
        this.ambulancia=a;
        this.ambulancia.setDisponible(false);
        this.ambulancia.setEstaMantenimiento(true);
    }

    @Override
    public void pacienteSolicitaAtencion(Asociado asociado) {

    }

    @Override
    public void pacienteSolicitaTraslado(Asociado asociado) {}

    @Override
    public void retornoAutomatico()
    {
        this.ambulancia.setAmbulanciaState(new Disponible(this.ambulancia));
    }

    @Override
    public void solicitudMantenimiento() {}

}
