package segundaEntrega.patrones.patronState;
import excepciones.TrasladandoPacienteExcepcion;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;

public class TrasladandoPaciente implements IState {
    private Ambulancia ambulancia;

    public TrasladandoPaciente(Ambulancia a)
    {
        this.ambulancia=a;
        this.ambulancia.setEstaMantenimiento(false);
        this.ambulancia.setDisponible(false);
    }

    @Override
    public void pacienteSolicitaAtencion(Asociado asociado) {}  //mantiene su estado

    @Override
    public void pacienteSolicitaTraslado(Asociado asociado){}    // no llega nunca

    @Override
    public void retornoAutomatico()
    {
        this.ambulancia.setAmbulanciaState(new Disponible(this.ambulancia));
    }

    @Override
    public void solicitudMantenimiento()  {} //no llega nunca aca

}