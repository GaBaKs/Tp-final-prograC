package segundaEntrega.patrones.patronState;

import excepciones.AtendiendoDomicilioExcepcion;
import excepciones.EnTallerExcepcion;
import excepciones.RegresandoSinPacienteExcepcion;
import excepciones.RegresandoTallerExcepcion;
import excepciones.TrasladandoPacienteExcepcion;
import segundaEntrega.modelo.negocio.Asociado;

public interface IState {

    public void pacienteSolicitaAtencion(Asociado asociado) ;

    public void pacienteSolicitaTraslado(Asociado asociado) ;

    public void retornoAutomatico();

    public void solicitudMantenimiento();

}
