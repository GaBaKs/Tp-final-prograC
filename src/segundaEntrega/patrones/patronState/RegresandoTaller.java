package segundaEntrega.patrones.patronState;
import segundaEntrega.modelo.TiempoMuerto;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;

/**Clase que implementa IState y representa el estado RegresandoTaller de la ambulancia*/
public class RegresandoTaller implements IState {
    /** {@link Ambulancia } */
    private Ambulancia ambulancia;

    public RegresandoTaller(Ambulancia a)
    {
        this.ambulancia=a;
        this.ambulancia.setDisponible(false);
        this.ambulancia.setEstaMantenimiento(true);
        this.ambulancia.setEstadoString("Regresando del taller");
        this.ambulancia.setEstaRegresando(true);
    }

    /**
     * Cambia, si es posible, el estado actual al recibir una peticion de atencion de un paciente
     */
    @Override
    public void pacienteSolicitaAtencion(Asociado asociado) {}

    /**
     * Cambia, si es posible, el estado actual al recibir una peticion de traslado de un paciente
     */
    @Override
    public void pacienteSolicitaTraslado(Asociado asociado) {}      // no llega nunca

    /**
     * Cambia, si es posible, el estado actual al recibir un retorno automatico
     */
    @Override
    public void retornoAutomatico() throws InterruptedException
    {
        this.ambulancia.setAmbulanciaState(new Disponible(this.ambulancia));
        this.ambulancia.llamaobserver("Retorno automatico a la clinica");
        TiempoMuerto.esperar();
    }

    /**
     * Cambia, si es posible, el estado actual al recibir una solicitud de mantenimiento por parte de un operario
     */
    @Override
    public void solicitudMantenimiento() {}

}
