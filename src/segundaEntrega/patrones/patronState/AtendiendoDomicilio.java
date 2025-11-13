package segundaEntrega.patrones.patronState;
import excepciones.AtendiendoDomicilioExcepcion;
import excepciones.EnTallerExcepcion;
import excepciones.TrasladandoPacienteExcepcion;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;
import segundaEntrega.patrones.excepciones.RegresandoTallerExcepcion;

public class AtendiendoDomicilio implements IState {
    private Ambulancia ambulancia;

    public AtendiendoDomicilio(Ambulancia a)
    {
        this.ambulancia=a;
        this.ambulancia.setDisponible(false);
        this.ambulancia.setEstaMantenimiento(false);

    }

    @Override
    public void pacienteSolicitaAtencion(Asociado asociado) {}  //

    @Override
    public void pacienteSolicitaTraslado(Asociado asociado){} // no llega nunca

    @Override
    public void retornoAutomatico()
    {
        this.ambulancia.setAmbulanciaState(new RegresandoSinPaciente(this.ambulancia));
    }

    @Override
    public void solicitudMantenimiento() {}			//no llega nunca



}
