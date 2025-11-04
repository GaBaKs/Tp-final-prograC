package segundaEntrega.patrones.patronState;

import excepciones.AtendiendoDomicilioExcepcion;
import excepciones.EnTallerExcepcion;
import excepciones.RegresandoSinPacienteExcepcion;
import excepciones.RegresandoTallerExcepcion;
import excepciones.TrasladandoPacienteExcepcion;
import segundaEntrega.modelo.negocio.Asociado;

public interface IState {


    public void pacienteSolicitaAtencion() ;

    public void pacienteSolicitaTraslado() throws AtendiendoDomicilioExcepcion,TrasladandoPacienteExcepcion,EnTallerExcepcion,RegresandoTallerExcepcion ;

    public void retornoAutomatico();

    public void solicitudMantenimiento() throws AtendiendoDomicilioExcepcion,TrasladandoPacienteExcepcion,RegresandoTallerExcepcion,RegresandoSinPacienteExcepcion;

}
