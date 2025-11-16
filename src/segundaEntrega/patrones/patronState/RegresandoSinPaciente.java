package segundaEntrega.patrones.patronState;

import segundaEntrega.modelo.TiempoMuerto;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;

/**Clase que implementa IState y representa el estado RegresandoSinPaciente de la ambulancia*/
public class RegresandoSinPaciente implements IState{
    /** {@link Ambulancia } */
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
    public void pacienteSolicitaAtencion(Asociado asociado) throws InterruptedException
    {
        this.ambulancia.setAmbulanciaState(new AtendiendoDomicilio(this.ambulancia));
        this.ambulancia.setAsociado(asociado);
        this.ambulancia.llamaobserver(this.ambulancia.getAsociado().getN_A() + " esta siendo atendido a domicilio por la ambulancia.");
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
        this.ambulancia.llamaobserver(this.ambulancia.getAsociado().getN_A() +" esta siendo trasladado por la ambulancia.");
        TiempoMuerto.esperar();
    }

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
    public void solicitudMantenimiento() {} // no llega nunca

}
