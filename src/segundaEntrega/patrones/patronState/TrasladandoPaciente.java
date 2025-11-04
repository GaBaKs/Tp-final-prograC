package segundaEntrega.patrones.patronState;
import excepciones.TrasladandoPacienteExcepcion;

public class TrasladandoPaciente implements IState {
    private Ambulancia ambulancia;

    public TrasladandoPaciente(Ambulancia a)
    {
        this.ambulancia=a;
    }

    @Override
    public void pacienteSolicitaAtención() {}		//permanece en el mismo estado

    @Override
    public void pacienteSolicitaTraslado() throws TrasladandoPacienteExcepcion {throw new TrasladandoPacienteExcepcion();}		//tiene que notificar que no puede (ver si lanza excepcion)

    @Override
    public void retornoAutomatico() {}			//permanece en el mismo estado

    @Override
    public void solicitudMantenimiento() throws TrasladandoPacienteExcepcion {throw new TrasladandoPacienteExcepcion();}	// tiene que notificar que no puede (ver si lanza excepcion)

}