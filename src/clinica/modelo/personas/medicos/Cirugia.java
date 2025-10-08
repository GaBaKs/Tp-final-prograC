package clinica.modelo.personas.medicos;

public class Cirugia extends Medico {

	public Cirugia(String dni,String N_A,String domicilio,String ciudad,int NMatricula,String telefono,String especialidad)
	{
		super(dni,N_A,domicilio,ciudad,NMatricula,telefono,especialidad);
	}


    @Override
    public double honorario() {
        return 0;
    }

}
