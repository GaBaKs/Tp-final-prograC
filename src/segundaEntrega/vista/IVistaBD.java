package segundaEntrega.vista;

import segundaEntrega.modelo.negocio.Asociado;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public interface IVistaBD {
    void addActionListener(ActionListener l);
    void arranca();
    void cerrar();

    public JTextField getNombre();
    public JTextField getApellido();
    public JTextField getCalle();
    public JTextField getCiudad();
    public JTextField getTelefono();
    public JTextField getDNI();
    public JTextField getNumero();

    public Asociado getAsociadoSeleccionado();

    public void actualizaLista(ArrayList<Asociado> asociadosSistema);
}
