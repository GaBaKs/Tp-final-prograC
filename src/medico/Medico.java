package medico;

public abstract class Medico extends Persona implements IMedico {
	int NMatricula,telefono;
	double base=20000;
	
	
	public Medico(String dni,String N_A,String domicilio,String ciudad,int NMatricula,int telefono) 
	{
		this.dni=dni;
		this.N_A=N_A;
		this.domicilio=domicilio;
		this.ciudad=ciudad;
		this.NMatricula=NMatricula;
		this.telefono=telefono;
	}

	
	
	
	
	

}
