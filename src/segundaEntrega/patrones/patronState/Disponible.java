package segundaEntrega.patrones.patronState;

public class Disponible implements IState {
    Ambulancia ambulancia;

    public Disponible(Ambulancia a)
    {
        this.ambulancia=a;
    }

    @Override
    public void pacienteSolicitaAtención()
    {
        this.ambulancia.ambulanciaState=new AtendiendoDomicilio(this.ambulancia);	//ambulancia pasa al estado de atencion a domicilio(preguntar por domicilio)
    }

    @Override
    public void pacienteSolicitaTraslado()
    {
        this.ambulancia.ambulanciaState=new TrasladandoPaciente(this.ambulancia);				//fijarse para agregar un paciente
    }

    @Override
    public void retornoAutomatico() {}  // se mantiene en el mismo estado

    @Override
    public void solicitudMantenimiento()
    {
        this.ambulancia.ambulanciaState=new EnTaller(this.ambulancia);			// a taller
    }

}