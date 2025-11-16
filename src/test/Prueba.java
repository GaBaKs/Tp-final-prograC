package test;


import clinica.modelo.facturacion.Factura;
import clinica.modelo.habitacion.Habitacion;
import clinica.modelo.habitacion.HabitacionCompartida;
import clinica.modelo.habitacion.HabitacionPrivada;
import clinica.modelo.personas.medicos.Cirugia;
import clinica.modelo.personas.medicos.Pediatra;
import clinica.modelo.personas.pacientes.FactoryPaciente;
import clinica.modelo.personas.pacientes.*;
import clinica.modelo.personas.medicos.*;
import clinica.patrones.decorator.*;
import clinica.patrones.facade.*;
import segundaEntrega.controlador.ControladorBD;
import segundaEntrega.controlador.ControladorInicio;
import segundaEntrega.modelo.ModeloBD;
import segundaEntrega.modelo.ModeloSimulacion;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.persistencia.basededatos.ConexionBD;
import segundaEntrega.vista.IVistaInicio;
import segundaEntrega.vista.JframeBD;
import segundaEntrega.vista.JframeInicio;
import segundaEntrega.vista.JframeSimulacion;

import java.time.LocalDate;

public class Prueba {

	public static void main(String[] args) {

        Sistema sistema= new Sistema();
        Paciente paciente1= FactoryPaciente.creaPaciente("Mayor","43123432","Vesa Vierikko","Don bosco 3283","Mar del plata","223568890",23);
        Paciente paciente2= FactoryPaciente.creaPaciente("Nino","38123456","Jorge Curioso","Independencia 2356","Mar del plata","223458723",32);
        Paciente paciente3= FactoryPaciente.creaPaciente("Joven","38423456","Esteban Arriaga","Independencia 2356","Mar del plata","223458523",33);
        Paciente paciente4= FactoryPaciente.creaPaciente("Mayor","38133456","Gandalf el gris","Independencia 2356","Mar del plata","223458923",32);


       MedicoFactory medicofactory=new MedicoFactory();
       Medico medico1=medicofactory.creaMedico("cirugia","45290972","octavio laz","La rioja 4582","mar del plata",12345,"223449493");
       IMedico decoratorPosgrado=new Magister(medico1);
       IMedico decoratorContratacion=new Permanente(decoratorPosgrado);

        Medico medico2=medicofactory.creaMedico("pediatra","45431234","Marcelo Gutierrez","Colon 2345","mar del plata",12347,"223449356");
        IMedico decoratorPosgrado2=new Magister(medico2);
        IMedico decoratorContratacion2=new Permanente(decoratorPosgrado2);

        Medico medico3=medicofactory.creaMedico("clinico","45431534","Doctor Casa","Vertiz 2345","Mar del plata",23565,"223446356");
        IMedico decoratorPosgrado3=new Doctorado(medico3);
        IMedico decoratorContratacion3=new Residente(decoratorPosgrado3);



       Habitacion h1 = new HabitacionCompartida(3,120,100);
       Habitacion h2 = new HabitacionPrivada(2,120,400);



       sistema.registraMedico(medico1);
        sistema.registraMedico(medico2);
        sistema.registraMedico(medico3);
       sistema.registraPaciente(paciente1);
       sistema.registraPaciente(paciente2);
        sistema.registraPaciente(paciente3);
        sistema.registraPaciente(paciente4);
       sistema.ingresaPaciente(paciente1);
        sistema.ingresaPaciente(paciente2);
        sistema.ingresaPaciente(paciente3);
        sistema.ingresaPaciente(paciente4);
       sistema.atiendePaciente(medico2, paciente1);
       sistema.atiendePaciente(medico1, paciente1);
       sistema.atiendePaciente(medico2, paciente4);
       sistema.atiendePaciente(medico3, paciente4);
       Factura factura1 = sistema.egresaPaciente(paciente1);
       sistema.atiendePaciente(medico2, paciente2);
       sistema.internaPaciente(paciente2,h1,5);
       Factura factura2 = sistema.egresaPaciente(paciente4);

        System.out.println(factura1.muestraFactura());
        System.out.println(factura2.muestraFactura());
        System.out.println(sistema.generaReporteMedico(medico1, LocalDate.of(1999,10,10),LocalDate.of(2026,10,12)));
        System.out.println(sistema.generaReporteMedico(medico2, LocalDate.of(1999,10,10),LocalDate.of(2026,10,12)));

        //parte 2
        Ambulancia ambulancia=Ambulancia.getInstance();

        //creacion de MVC inicio
        IVistaInicio ventanaInicio=new JframeInicio();
        ventanaInicio.arranca();

        ModeloSimulacion modeloSimulacion=new ModeloSimulacion(ambulancia);

        ControladorInicio controladorInicio=new ControladorInicio(modeloSimulacion,ventanaInicio);

        //creo el singleton
        ConexionBD conexionBD=ConexionBD.getInstance();








	}

}
