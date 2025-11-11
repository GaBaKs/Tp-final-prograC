package segundaEntrega.vista;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface IVistaPrincipal{
    void addActionListener(ActionListener l);
    void arranca();
    void cerrar();

    JTextField getCantidadAsociados();
    //no entiendo que parte del programa esta en la vista principal, es donde se miuestra la base de datos??
    //de ser asi creo q irian los getters de los asociados y eso
}
