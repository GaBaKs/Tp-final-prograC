package segundaEntrega.controlador;

import segundaEntrega.modelo.InicioSimulacion;
import segundaEntrega.vista.IVistaSimulacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ControladorSimulacion implements ActionListener, Observer {
    private InicioSimulacion inicioSimulacion;
    private IVistaSimulacion vista;

    public ControladorSimulacion(IVistaSimulacion vista, InicioSimulacion inicioSimulacion)
    {
        this.inicioSimulacion = inicioSimulacion;
        this.vista = vista;
        this.vista.addActionListener(this);
        this.inicioSimulacion.getAmbulancia().addObserver(this);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String comando = e.getActionCommand();

        if (comando.equals("Iniciar")) {
            // Le dice al modelo que comience la simulación (inicie los hilos)
            this.inicioSimulacion.inicia();

        } else if (comando.equals("Finalizar")) {
            this.inicioSimulacion.finalizar();
        }
    }

    @Override
    public void update(Observable o, Object nuevoestado) {
        vista.setEstadoAmbulancia("Estado: " + nuevoestado.toString()); //esto creo q esta mal pero no se como hacerlo bien
    }
}
