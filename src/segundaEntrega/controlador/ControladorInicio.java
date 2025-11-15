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



    public ControladorInicio(ModeloSimulacion inicioSimulacion, IVistaInicio vista,Ambulancia ambulancia)
    {
        this.ambulancia=ambulancia;
        this.inicioSimulacion = inicioSimulacion;
        this.vista = vista;
        this.vista.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        ModeloBD modelobd = new ModeloBD(this.ambulancia);

        if (comando.equals("Modulo de simulacion")) {
            // oculto la vista de inicio
            this.vista.cerrar();

            IVistaSimulacion vistasimulacion = new JframeSimulacion();
            ModeloSimulacion modeloInicioSimulacion = new ModeloSimulacion(); //el controlador deberia conocer esto no?
            // creo el controlador de principal y le paso vista y modelo
            ControladorSimulacion contprincipal = new ControladorSimulacion(vistasimulacion, modeloInicioSimulacion,modelobd.getAsociados());

            // muestro la vista de la simulacion
            vistasimulacion.arranca();

        } else if (comando.equals("Modulo base de datos")) {
            // oculto la vista de inicio
            this.vista.cerrar();

            //creo la vista para la base de datos
            IVistaBD vistaBD = new JframeBD();
            //creo el controlador y le paso al vista y el modelo
            ControladorBD controladorbd = new ControladorBD(vistaBD,modelobd);
            //muestro la vista de la base de datos
            vistaBD.arranca();

        }
    }

}

