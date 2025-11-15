package segundaEntrega.controlador;

import segundaEntrega.modelo.InicioSimulacion;
import segundaEntrega.vista.IVistaSimulacion;
import segundaEntrega.vista.JframeExcepciones;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ControladorSimulacion implements ActionListener {
    private InicioSimulacion inicioSimulacion;
    private IVistaSimulacion vista;

    public ControladorSimulacion(IVistaSimulacion vista, InicioSimulacion inicioSimulacion) {
        this.inicioSimulacion = inicioSimulacion;
        this.vista = vista;
        this.vista.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("Iniciar")) {
            // Le dice al modelo que comience la simulación (inicie los hilos)
            JTextField cantsolicitudes = vista.getCantSolicitudes();
            int cantsolicitudesInicial = Integer.parseInt(cantsolicitudes.getText());
            //cambio a int
            this.inicioSimulacion.inicia(cantsolicitudesInicial);

        } else if (comando.equals("Finalizar")) {
            this.inicioSimulacion.finalizar();
        } else if (comando.equals("Solicitar mantenimiento")) {
            this.inicioSimulacion.solicitudMantenimiento();
        }
    }

    public int getCantSolicitudes() {
            int valor = Integer.parseInt(vista.getCantSolicitudes().getText());
            return valor;

    }

}
