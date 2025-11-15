package segundaEntrega.patrones.patronState;
import excepciones.AtendiendoDomicilioExcepcion;
import excepciones.EnTallerExcepcion;
import excepciones.TrasladandoPacienteExcepcion;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;
import segundaEntrega.patrones.excepciones.RegresandoTallerExcepcion;

/**Clase que implementa IState y representa el estado AtendiendoDomicilio de la ambulancia*/
public class AtendiendoDomicilio implements IState {
    private Ambulancia ambulancia;

    public AtendiendoDomicilio(Ambulancia a)
    {
        this.ambulancia=a;
        this.ambulancia.setDisponible(false);
        this.ambulancia.setEstaMantenimiento(false);

    }

    /**
     * Cambia, si es posible, el estado actual al recibir una peticion de atencion de un paciente
     */
    @Override
    public void pacienteSolicitaAtencion(Asociado asociado) {}  //

    /**
     * Cambia, si es posible, el estado actual al recibir una peticion de traslado de un paciente
     */
    @Override
    public void pacienteSolicitaTraslado(Asociado asociado){} // no llega nunca

    /**
     * Cambia, si es posible, el estado actual al recibir un retorno automatico
     */
    @Override
    public void retornoAutomatico()
    {
        this.ambulancia.setAmbulanciaState(new RegresandoSinPaciente(this.ambulancia));
    }

    /**
     * Cambia, si es posible, el estado actual al recibir una solicitud de mantenimiento por parte de un operario
     */
    @Override
    public void solicitudMantenimiento() {}			//no llega nunca



}
