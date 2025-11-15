package segundaEntrega.patrones.patronState;

import excepciones.AtendiendoDomicilioExcepcion;
import excepciones.EnTallerExcepcion;
import excepciones.TrasladandoPacienteExcepcion;
import segundaEntrega.modelo.negocio.Asociado;

/**
 * Interface que implementa los metodos para el cambio de estado en la ambulancia
 */
public interface IState {


    /**
     * Cambia, si es posible, el estado actual al recibir una peticion de atencion de un paciente
     */
    public void pacienteSolicitaAtencion(Asociado asociado) ;

    /**
     * Cambia, si es posible, el estado actual al recibir una peticion de traslado de un paciente
     */
    public void pacienteSolicitaTraslado(Asociado asociado) ;

    /**
     * Cambia, si es posible, el estado actual al recibir un retorno automatico
     */
    public void retornoAutomatico();

    /**
     * Cambia, si es posible, el estado actual al recibir una solicitud de mantenimiento por parte de un operario
     */
    public void solicitudMantenimiento();

}
