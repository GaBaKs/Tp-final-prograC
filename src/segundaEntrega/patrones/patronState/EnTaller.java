package segundaEntrega.patrones.patronState;

import excepciones.EnTallerExcepcion;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;

/**Clase que implementa IState y representa el estado EnTaller de la ambulancia*/
public class EnTaller implements IState {
    private Ambulancia ambulancia;

    public EnTaller(Ambulancia a)
    {
        this.ambulancia=a;
        this.ambulancia.setDisponible(false);
        this.ambulancia.setEstaMantenimiento(true);
        this.ambulancia.setEstadoString("En taller");
    }

    /**
     * Cambia, si es posible, el estado actual al recibir una peticion de atencion de un paciente
     */
    @Override
    public void pacienteSolicitaAtencion(Asociado asociado) {}// no entra porque esta en mantenimiento

    /**
     * Cambia, si es posible, el estado actual al recibir una peticion de traslado de un paciente
     */
    @Override
    public void pacienteSolicitaTraslado(Asociado asociado){}	// informa que no puede

    /**
     * Cambia, si es posible, el estado actual al recibir un retorno automatico
     */
    @Override
    public void retornoAutomatico(){}		// no entra porque esta en mantenimiento

    /**
     * Cambia, si es posible, el estado actual al recibir una solicitud de mantenimiento por parte de un operario
     */
    @Override
    public void solicitudMantenimiento()
    {
        this.ambulancia.setAmbulanciaState(new RegresandoTaller(this.ambulancia));
    }

}