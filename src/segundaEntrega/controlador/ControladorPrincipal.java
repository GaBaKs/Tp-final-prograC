package segundaEntrega.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import segundaEntrega.modelo.InicioSimulacion;
import segundaEntrega.vista.IVistaPrincipal;
import segundaEntrega.vista.IVistaSimulacion;
import segundaEntrega.vista.JframeSimulacion;

import javax.swing.*;

public class ControladorPrincipal implements ActionListener {
    private IVistaPrincipal vistaprincipal;
    private InicioSimulacion iniciosimulacion;

    public ControladorPrincipal(IVistaPrincipal vistaprincipal, InicioSimulacion iniciosimulacion) {
        this.vistaprincipal = vistaprincipal;
        this.iniciosimulacion = iniciosimulacion;
        this.vistaprincipal.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("Comenzar simulacion")) {
            //pido la cant de asociados q quiero usar en la simulacion
            JTextField cantAsociados = this.vistaprincipal.getCantidadAsociados();
            //aca deberia preguntar la cantidad de operarios tambien? no me termino de quedar claro, o usamos solo el boton?

            this.iniciosimulacion.configurarSimulacion(cantAsociados); //esto todavia no lo tenemos igual asi q ni bola

            //creo simulacion
            IVistaSimulacion vistaSim = new JframeSimulacion();

           //creo el controlador d la simulacion
            ControladorSimulacion contsim = new ControladorSimulacion(vistaSim, iniciosimulacion);

            // muestro la nueva vista y cierro esta
            this.vistaprincipal.cerrar();
            vistaSim.arranca();
        }
    }
}

