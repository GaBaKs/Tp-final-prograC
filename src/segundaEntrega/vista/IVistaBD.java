package segundaEntrega.vista;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface IVistaBD {
    void addActionListener(ActionListener l);
    void arranca();
    void cerrar();
    JTextField getNombre();
    JTextField getApellido();
    JTextField getCalle();
    JTextField getCiudad();
    JTextField getTelefono();
    JTextField getDNI();
    JTextField getNumero();


}
