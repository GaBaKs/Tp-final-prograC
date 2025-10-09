package test;


import clinica.modelo.personas.medicos.Cirugia;
import clinica.modelo.personas.medicos.Pediatra;
import clinica.modelo.personas.pacientes.FactoryPaciente;
import clinica.modelo.personas.pacientes.*;
import clinica.modelo.personas.medicos.*;
import clinica.patrones.decorator.*;
import clinica.patrones.facade.*;

public class Prueba {

	public static void main(String[] args) {


        Paciente paciente1= FactoryPaciente.creaPaciente("Nino","43123432","Martin Gonzalez","Don bosco 3283","mar del plata","223568890",23);
        Paciente paciente2= FactoryPaciente.creaPaciente("Joven","38123456","Mario Gomez","Independencia 2356","mar del plata","223458723",32);



        IMedico m=new Cirugia("45290972","octavio","echeverria","mar del plata",12345,"4494935");
        IMedico DP=new Magister(m);
        IMedico DC=new Permanente(DP);
        System.out.println("especialidad: "+DC.toString());


	/*
		System.out.println("clinico");
		System.out.println(m.honorario());
		
		System.out.println("clinico-magister");
		IMedico DP=new Magister(m);
		System.out.println(DP.honorario());
		
		System.out.println("clinico-magister-permanente");
		DecoratorPosgrado DP2=new Magister(m);
		IMedico DC=new Permanente(DP2);
		System.out.println(DC.honorario());
*/


       // sistema.registraMedico(medico1);
       // sistema.registraMedico(medico2);
       // sistema.registraPaciente(paciente1);
       // sistema.registraPaciente(paciente2);
       // sistema.ingresaPaciente(paciente1);
       // sistema.ingresaPaciente(paciente2);
       // sistema.atiendePaciente(medico1, paciente1);
       // sistema.atiendePaciente(medico2, paciente1);
      //  factura1 = sistema.egresaPaciente(paciente1);
      //  sistema.atiendePaciente(medico3, paciente2);
      //  sistema.internaPaciente(paciente2,habitacion3);
      //  factura2 = sistema.egresaPaciente(paciente2, cantidadDias);
		
	}

}
