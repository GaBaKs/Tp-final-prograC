package segundaEntrega.controlador;

import segundaEntrega.modelo.ModeloSimulacion;
import segundaEntrega.modelo.ModeloBD;
import segundaEntrega.vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorInicio implements ActionListener {

    private ModeloSimulacion inicioSimulacion;
    private IVistaInicio vista;



    public ControladorInicio(ModeloSimulacion inicioSimulacion, IVistaInicio vista)
    {
        this.inicioSimulacion = inicioSimulacion;
        this.vista = vista;
        this.vista.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("Modulo de simulacion")) {
            // oculto la vista de inicio
            this.vista.cerrar();

            IVistaSimulacion vistasimulacion = new JframeSimulacion();
            ModeloSimulacion modeloInicioSimulacion = new ModeloSimulacion(); //el controlador deberia conocer esto no?

            // creo el controlador de principal y le paso vista y modelo
            ControladorSimulacion contprincipal = new ControladorSimulacion(vistasimulacion, modeloInicioSimulacion);

            // muestro la vista de la simulacion
            vistasimulacion.arranca();

        } else if (comando.equals("Modulo base de datos")) {
            // oculto la vista de inicio
            this.vista.cerrar();

            //creo la vista para la base de datos
            ModeloBD modelobd = new ModeloBD(this.inicioSimulacion.getAmbulancia());
            IVistaBD vistaBD = new JframeBD();
            //creo el controlador y le paso al vista y el modelo
            ControladorBD controladorbd = new ControladorBD(vistaBD,modelobd);

        }
    }

}

