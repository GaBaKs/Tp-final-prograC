package segundaEntrega.vista;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface IVistaSimulacion {
    void addActionListener(ActionListener l);
    void arranca();
    void cerrar();
    public JTextField getCantSolicitudes();
    public void addAccionAmbulancia(String accion);
    public void setEstadoAmbulancia(String estado);
    //get selected person ?? ni idea
    //actualizar lista

}
