package segundaEntrega.controlador;

import segundaEntrega.modelo.ModeloSimulacion;
import segundaEntrega.vista.IVistaSimulacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorSimulacion implements ActionListener {
    private ModeloSimulacion inicioSimulacion;
    private IVistaSimulacion vista;

    public ControladorSimulacion(IVistaSimulacion vista, ModeloSimulacion inicioSimulacion) {
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
