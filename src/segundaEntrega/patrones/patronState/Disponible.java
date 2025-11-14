package segundaEntrega.patrones.patronState;

import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;

public class Disponible implements IState {
    private Ambulancia ambulancia;

    public Disponible(Ambulancia a)
    {

        this.ambulancia=a;
        this.ambulancia.setEstaMantenimiento(false);
        this.ambulancia.setDisponible(true);
    }

    @Override
    public void pacienteSolicitaAtencion(Asociado asociado)
    {
        this.ambulancia.setAmbulanciaState(new AtendiendoDomicilio(this.ambulancia)); //ambulancia pasa al estado de atencion a domicilio(preguntar por domicilio)
    }

    @Override
    public void pacienteSolicitaTraslado(Asociado asociado)
    {
        this.ambulancia.setAmbulanciaState(new TrasladandoPaciente(this.ambulancia));
    }

    @Override
    public void retornoAutomatico() {}  // se mantiene en el mismo estado

    @Override
    public void solicitudMantenimiento()
    {
        this.ambulancia.setAmbulanciaState(new EnTaller(this.ambulancia)); // a taller
    }

}