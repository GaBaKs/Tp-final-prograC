package segundaEntrega.patrones.patronState;

import segundaEntrega.modelo.TiempoMuerto;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;

/**Clase que implementa IState y representa el estado Disponible de la ambulancia*/
public class Disponible implements IState {
    /** {@link Ambulancia } */
    private Ambulancia ambulancia;

    public Disponible(Ambulancia a)
    {

        this.ambulancia=a;
        this.ambulancia.setEstaMantenimiento(false);
        this.ambulancia.setDisponible(true);
        this.ambulancia.setEstadoString("Disponible");
        this.ambulancia.setEstaRegresando(false);
    }

    /**
     * Cambia, si es posible, el estado actual al recibir una peticion de atencion de un paciente
     */
    @Override
    public void pacienteSolicitaAtencion(Asociado asociado) throws InterruptedException
    {
        this.ambulancia.setAmbulanciaState(new AtendiendoDomicilio(this.ambulancia)); //ambulancia pasa al estado de atencion a domicilio(preguntar por domicilio)
        this.ambulancia.setAsociado(asociado);
        this.ambulancia.llamaobserver(this.ambulancia.getAsociado().getN_A() +" esta siendo atendido a domicilio por la ambulancia.");
        TiempoMuerto.esperar(); // Simula el tiempo de la atención
    }

    /**
     * Cambia, si es posible, el estado actual al recibir una peticion de traslado de un paciente
     */
    @Override
    public void pacienteSolicitaTraslado(Asociado asociado) throws InterruptedException
    {
        this.ambulancia.setAmbulanciaState(new TrasladandoPaciente(this.ambulancia));
        this.ambulancia.setAsociado(asociado);
        this.ambulancia.llamaobserver(this.ambulancia.getAsociado().getN_A() + " esta siendo trasladado por la ambulancia.");
        TiempoMuerto.esperar();
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
    public void solicitudMantenimiento() throws InterruptedException
    {
        this.ambulancia.setAmbulanciaState(new EnTaller(this.ambulancia)); // a taller
        this.ambulancia.setAsociado(null);
        this.ambulancia.llamaobserver(" El operario mando a la ambulancia a mantenimiento.");
        TiempoMuerto.esperar();
        // Post-condicion:
        assert this.ambulancia.getAmbulanciaState() instanceof EnTaller : "La transicion de estado fallo";
    }

}