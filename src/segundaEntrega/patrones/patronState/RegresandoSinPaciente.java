package segundaEntrega.patrones.patronState;

import excepciones.TrasladandoPacienteExcepcion;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;

public class RegresandoSinPaciente implements IState{
    private Ambulancia ambulancia;

    public RegresandoSinPaciente(Ambulancia a)
    {
        this.ambulancia=a;
        this.ambulancia.setDisponible(true);
        this.ambulancia.setEstaMantenimiento(false);
    }

    @Override
    public void pacienteSolicitaAtencion(Asociado asociado)
    {
        this.ambulancia.setAmbulanciaState(new AtendiendoDomicilio(this.ambulancia));
    }

    @Override
    public void pacienteSolicitaTraslado(Asociado asociado)
    {
        this.ambulancia.setAmbulanciaState(new AtendiendoDomicilio(this.ambulancia));
    }

    @Override
    public void retornoAutomatico()
    {
        this.ambulancia.setAmbulanciaState(new Disponible(this.ambulancia));

    }

    @Override
    public void solicitudMantenimiento() {} // no llega nunca

}
