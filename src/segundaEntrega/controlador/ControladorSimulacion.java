package segundaEntrega.controlador;

import segundaEntrega.modelo.ModeloSimulacion;
import segundaEntrega.modelo.negocio.Asociado;
import segundaEntrega.vista.IVistaSimulacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorSimulacion implements ActionListener {
    private ModeloSimulacion inicioSimulacion;
    private IVistaSimulacion vista;
    private ArrayList<Asociado> asociados;

    public ControladorSimulacion(IVistaSimulacion vista, ModeloSimulacion inicioSimulacion, ArrayList<Asociado> asociados) {
        this.inicioSimulacion = inicioSimulacion;
        this.vista = vista;
        this.vista.addActionListener(this);
        this.asociados = asociados;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("Iniciar")) {
            // Le dice al modelo que comience la simulación (inicie los hilos)
            JTextField cantsolicitudes = vista.getCantSolicitudes();
            int cantsolicitudesInicial = Integer.parseInt(cantsolicitudes.getText());
            //cambio a int
            this.inicioSimulacion.inicia(cantsolicitudesInicial,asociados);//de alguna manera le tengo q mandar un arraylist de asociados

        } else if (comando.equals("Finalizar")) {
            this.inicioSimulacion.finalizar();
        } else if (comando.equals("Solicitar mantenimiento")) {
            this.inicioSimulacion.solicitaMantenimiento();
        }
    }

    public int getCantSolicitudes() {
            int valor = Integer.parseInt(vista.getCantSolicitudes().getText());
            return valor;

    }

}
