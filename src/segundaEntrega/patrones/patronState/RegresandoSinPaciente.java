package segundaEntrega.patrones.patronState;

import excepciones.TrasladandoPacienteExcepcion;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;

/**Clase que implementa IState y representa el estado RegresandoSinPaciente de la ambulancia*/
public class RegresandoSinPaciente implements IState{
    private Ambulancia ambulancia;

    public RegresandoSinPaciente(Ambulancia a)
    {
        this.ambulancia=a;
        this.ambulancia.setDisponible(true);
        this.ambulancia.setEstaMantenimiento(false);
        this.ambulancia.setEstadoString("Regresando sin paciente");
        this.ambulancia.setEstaRegresando(true);
    }

    /**
     * Cambia, si es posible, el estado actual al recibir una peticion de atencion de un paciente
     */
    @Override
    public void pacienteSolicitaAtencion(Asociado asociado)
    {
        this.ambulancia.setAmbulanciaState(new AtendiendoDomicilio(this.ambulancia));
    }

    /**
     * Cambia, si es posible, el estado actual al recibir una peticion de traslado de un paciente
     */
    @Override
    public void pacienteSolicitaTraslado(Asociado asociado)
    {
        this.ambulancia.setAmbulanciaState(new AtendiendoDomicilio(this.ambulancia));
    }

    /**
     * Cambia, si es posible, el estado actual al recibir un retorno automatico
     */
    @Override
    public void retornoAutomatico()
    {
        this.ambulancia.setAmbulanciaState(new Disponible(this.ambulancia));

    }

    /**
     * Cambia, si es posible, el estado actual al recibir una solicitud de mantenimiento por parte de un operario
     */
    @Override
    public void solicitudMantenimiento() {} // no llega nunca

}
