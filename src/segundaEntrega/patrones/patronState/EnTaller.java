package segundaEntrega.patrones.patronState;

import excepciones.EnTallerExcepcion;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;

public class EnTaller	implements IState {
    private Ambulancia ambulancia;

    public EnTaller(Ambulancia a)
    {
        this.ambulancia=a;
        this.ambulancia.setDisponible(false);
        this.ambulancia.setEstaMantenimiento(true);
    }

    @Override
    public void pacienteSolicitaAtencion(Asociado asociado) {}// no entra porque esta en mantenimiento

    @Override
    public void pacienteSolicitaTraslado(Asociado asociado){}	// informa que no puede

    @Override
    public void retornoAutomatico(){}		// no entra porque esta en mantenimiento

    @Override
    public void solicitudMantenimiento()
    {
        this.ambulancia.setAmbulanciaState(new RegresandoTaller(this.ambulancia));
    }

}