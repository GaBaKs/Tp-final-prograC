package segundaEntrega.controlador;

import segundaEntrega.modelo.ModeloSimulacion;
import segundaEntrega.modelo.ModeloBD;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorInicio implements ActionListener {

    private ModeloSimulacion inicioSimulacion;
    private IVistaInicio vista;
    private Ambulancia ambulancia;
    private ModeloBD modeloBD;
    private ModeloSimulacion modeloSimulacion;



    public ControladorInicio(ModeloSimulacion inicioSimulacion, IVistaInicio vista)
    {
        this.inicioSimulacion = inicioSimulacion;
        this.vista = vista;
        this.vista.addActionListener(this);
        this.ambulancia = Ambulancia.getInstance(); // Usamos el Singleton
        this.modeloBD = new ModeloBD(this.ambulancia);
        this.modeloSimulacion = new ModeloSimulacion(this.ambulancia); // Este modelo usará la ambulancia
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("Modulo de simulacion")) {
            // oculto la vista de inicio
            this.vista.cerrar();

            IVistaSimulacion vistasimulacion = new JframeSimulacion();

            // Pasamos el modelo de simulación y la lista de asociados del modeloBD
            ControladorSimulacion contprincipal = new ControladorSimulacion(vistasimulacion, this.modeloSimulacion,this.modeloBD.getAsociados());


            MiObserverVista miObserverVista = new MiObserverVista(vistasimulacion, this.ambulancia);


            // muestro la vista de la simulacion
            vistasimulacion.arranca();

        } else if (comando.equals("Modulo base de datos")) {
            // oculto la vista de inicio
            this.vista.cerrar();

            IVistaBD vistaBD = new JframeBD();
            // Le pasamos el modelo de base de datos
            ControladorBD controladorbd = new ControladorBD(vistaBD, this.modeloBD);
            //muestro la vista de la base de datos
            vistaBD.arranca();
        }
    }

}

