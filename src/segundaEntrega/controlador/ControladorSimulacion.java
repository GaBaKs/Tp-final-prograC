package segundaEntrega.controlador;

import segundaEntrega.modelo.ModeloSimulacion;
import segundaEntrega.modelo.negocio.Asociado;
import segundaEntrega.vista.IVistaSimulacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * ControladorSimulacion coordina las acciones entre la vista de simulación
 * y el modelo que gestiona la ejecución de la misma.
 *
 * Este controlador también administra la lista de asociados que participan
 * en la simulación.
 */
public class ControladorSimulacion implements ActionListener {
    /** {@link ModeloSimulacion } */
    private ModeloSimulacion inicioSimulacion;
    /** {@link IVistaSimulacion } */
    private IVistaSimulacion vista;
    /** arraylist de {@link Asociado } */
    private ArrayList<Asociado> asociados;

    public ControladorSimulacion(IVistaSimulacion vista, ModeloSimulacion inicioSimulacion, ArrayList<Asociado> asociados) {
        this.inicioSimulacion = inicioSimulacion;
        this.vista = vista;
        this.vista.addActionListener(this);
        this.asociados = asociados;
    }

    /**
     * Maneja los comandos enviados desde la vista. Según el texto del comando,
     * inicia la simulación, la finaliza o solicita mantenimiento al modelo.
     *
     * @param e evento de acción generado por la vista
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("Iniciar")) {
            // Le dice al modelo que comience la simulación (inicie los hilos)
            JTextField cantsolicitudes = vista.getCantSolicitudes();
            int cantsolicitudesInicial = Integer.parseInt(cantsolicitudes.getText());
            JTextField cantAsociados = vista.getCantAsociados();
            int cantAsociadosTotal = Integer.parseInt(cantAsociados.getText());

            //cambio a int
            this.inicioSimulacion.inicia(cantsolicitudesInicial,cantAsociadosTotal,asociados);//de alguna manera le tengo q mandar un arraylist de asociados
            vista.enableSolMan();
        } else if (comando.equals("Finalizar")) {
            System.out.println("FINALIZA");
            this.inicioSimulacion.finalizar();
            vista.disableSolMan();
        } else if (comando.equals("Solicitar mantenimiento")) {
            this.inicioSimulacion.solicitaMantenimiento();
        }
    }

    /**
     * Obtiene y retorna la cantidad de solicitudes ingresadas por el usuario
     * en la vista de simulación.
     *
     * @return cantidad de solicitudes numerada desde el campo correspondiente
     * @throws NumberFormatException si el valor ingresado no es un número entero
     */
    public int getCantSolicitudes() {
            int valor = Integer.parseInt(vista.getCantSolicitudes().getText());
            return valor;

    }

}
