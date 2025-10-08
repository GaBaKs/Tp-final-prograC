import clinica.modelo.medicos.Clinica;
import clinica.modelo.medicos.IMedico;
import clinica.patrones.decorator.Magister;
import clinica.patrones.decorator.DecoratorPosgrado;
import clinica.patrones.decorator.Permanente;

public class Prueba {

	public static void main(String[] args) {
		
		IMedico m=new Clinica("45290972","octavio laz","echeverria","mar del plata",123,4494935);
		
		
		System.out.println("clinico");
		System.out.println(m.honorario());
		
		System.out.println("clinico-magister");
		IMedico DP=new Magister(m);
		System.out.println(DP.honorario());
		
		System.out.println("clinico-magister-permanente");
		DecoratorPosgrado DP2=new Magister(m);
		IMedico DC=new Permanente(DP2);
		System.out.println(DC.honorario());
		
		
		
	}

}
