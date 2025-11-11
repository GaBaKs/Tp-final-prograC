package segundaEntrega.controlador;

import segundaEntrega.modelo.Inicio;
import segundaEntrega.vista.IVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorInicio implements ActionListener {

    private Inicio inicio;
    private IVista vista;



    public ControladorInicio(Inicio inicio, IVista vista)
    {
        this.acumulador = acumulador;
        this.vista = vista;
        this.vista.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
