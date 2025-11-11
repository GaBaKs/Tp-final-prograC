package segundaEntrega.controlador;

import segundaEntrega.modelo.InicioSimulacion;
import segundaEntrega.vista.IVistaInicio;
import segundaEntrega.vista.IVistaPrincipal;
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

            IVistaPrincipal vistaprincipal = new ventana.Jframeprincipal();
            InicioSimulacion modeloInicioSimulacion = new InicioSimulacion(); //el controlador deberia conocer esto no?

            // creo el controlador de principal y le paso vista y modelo
            ControladorPrincipal contprincipal = new ControladorPrincipal(vistaprincipal, modeloInicioSimulacion);

            // muestro la vista de la somulacion
            vistaprincipal.arranca();

        } else if (comando.equals("Modulo base de datos")) {
            //idem pero p crear la vista de asociados
        }
    }

}

