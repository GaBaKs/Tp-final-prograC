package segundaEntrega.controlador;

import segundaEntrega.modelo.InicioSimulacion;
import segundaEntrega.vista.IVistaInicio;
import segundaEntrega.vista.IVistaPrincipal;
import segundaEntrega.vista.IVistaSimulacion;
import segundaEntrega.vista.JframeSimulacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorInicio implements ActionListener {

    private InicioSimulacion inicioSimulacion;
    private IVistaInicio vista;



    public ControladorInicio(InicioSimulacion inicioSimulacion, IVistaInicio vista)
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
            InicioSimulacion modeloInicioSimulacion = new InicioSimulacion(); //el controlador deberia conocer esto no?

            // creo el controlador de principal y le paso vista y modelo
            ControladorSimulacion contprincipal = new ControladorSimulacion(vistasimulacion, modeloInicioSimulacion);

            // muestro la vista de la somulacion
            vistasimulacion.arranca();

        } else if (comando.equals("Modulo base de datos")) {
            //idem pero p crear la vista de asociados
        }
    }

}

