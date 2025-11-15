package segundaEntrega.patrones.patronState;

import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;

/**Clase que implementa IState y representa el estado Disponible de la ambulancia*/
public class Disponible implements IState {
    private Ambulancia ambulancia;

    public Disponible(Ambulancia a)
    {

        this.ambulancia=a;
        this.ambulancia.setEstaMantenimiento(false);
        this.ambulancia.setDisponible(true);
    }

    /**
     * Cambia, si es posible, el estado actual al recibir una peticion de atencion de un paciente
     */
    @Override
    public void pacienteSolicitaAtencion(Asociado asociado)
    {
        this.ambulancia.setAmbulanciaState(new AtendiendoDomicilio(this.ambulancia)); //ambulancia pasa al estado de atencion a domicilio(preguntar por domicilio)
    }

    /**
     * Cambia, si es posible, el estado actual al recibir una peticion de traslado de un paciente
     */
    @Override
    public void pacienteSolicitaTraslado(Asociado asociado)
    {
        this.ambulancia.setAmbulanciaState(new TrasladandoPaciente(this.ambulancia));
    }

    /**
     * Cambia, si es posible, el estado actual al recibir un retorno automatico
     */
    @Override
    public void retornoAutomatico() {}  // se mantiene en el mismo estado

    /**
     * Cambia, si es posible, el estado actual al recibir una solicitud de mantenimiento por parte de un operario
     */
    @Override
    public void solicitudMantenimiento()
    {
        this.ambulancia.setAmbulanciaState(new EnTaller(this.ambulancia)); // a taller
        // Post-condicion:
        assert this.ambulancia.getAmbulanciaState() instanceof EnTaller : "La transicion de estado fallo";
    }

}