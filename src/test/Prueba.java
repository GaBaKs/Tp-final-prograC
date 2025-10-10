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

        Sistema sistema= new Sistema();
        Paciente paciente1= FactoryPaciente.creaPaciente("Nino","43123432","Martin Gonzalez","Don bosco 3283","mar del plata","223568890",23);
        Paciente paciente2= FactoryPaciente.creaPaciente("Joven","38123456","Mario Gomez","Independencia 2356","mar del plata","223458723",32);



       MedicoFactory medicofactory=new MedicoFactory();
       Medico medico1=medicofactory.creaMedico("clinica","45290972","octavio laz","La rioja 4582","mar del plata",12345,"223449493");
       IMedico decoratorPosgrado=new Magister(medico1);
       IMedico decoratorContratacion=new Permanente(decoratorPosgrado);

       Medico medico2=medicofactory.creaMedico("pediatra","45431234","Marcelo Gutierrez","Colon 2345","mar del plata",12347,"223449356");
       IMedico decoratorPosgrado2=new Magister(medico2);
       IMedico decoratorContratacion2=new Permanente(decoratorPosgrado2);

       sistema.registraMedico(medico1);
       sistema.registraMedico(medico2);
       sistema.registraPaciente(paciente1);
       sistema.registraPaciente(paciente2);
       sistema.ingresaPaciente(paciente1);
       sistema.ingresaPaciente(paciente2);
       sistema.atiendePaciente(medico1, paciente1);
       sistema.atiendePaciente(medico2, paciente1);
       //factura1 = sistema.egresaPaciente(paciente1);
      //  sistema.atiendePaciente(medico3, paciente2);
      //  sistema.internaPaciente(paciente2,habitacion3);
      //  factura2 = sistema.egresaPaciente(paciente2, cantidadDias);
		
	}

}
