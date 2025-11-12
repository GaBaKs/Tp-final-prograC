package segundaEntrega.patrones.patronState;
import excepciones.TrasladandoPacienteExcepcion;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;

public class TrasladandoPaciente implements IState {
    private Ambulancia ambulancia;

    public TrasladandoPaciente(Ambulancia a)
    {
        this.ambulancia=a;
    }

    @Override
    public void pacienteSolicitaTraslado(Asociado asociado) throws TrasladandoPacienteExcepcion {throw new TrasladandoPacienteExcepcion();}		//tiene que notificar que no puede (ver si lanza excepcion)

    @Override
    public void pacienteSolicitaAtencion(Asociado asociado) {

    }

    @Override
    public void retornoAutomatico() {}			//permanece en el mismo estado

    @Override
    public void solicitudMantenimiento() throws TrasladandoPacienteExcepcion {throw new TrasladandoPacienteExcepcion();}	// tiene que notificar que no puede (ver si lanza excepcion)

}