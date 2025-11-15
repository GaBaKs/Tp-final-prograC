package segundaEntrega.vista;

import segundaEntrega.modelo.negocio.Asociado;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public interface IVistaBD {
    void addActionListener(ActionListener l);
    void arranca();
    void cerrar();

    /**
     * Muestra un diálogo de confirmación al usuario.
     * @param titulo El título de la ventana.
     * @param mensaje El mensaje a mostrar.
     * @return true si el usuario presionó "Sí", false en cualquier otro caso.
     */
    boolean mostrarConfirmacion(String titulo, String mensaje);

    /**
     * Muestra un mensaje de error.
     * @param mensaje El error a mostrar.
     */
    void mostrarError(String mensaje);

    /**
     * Muestra un mensaje de información.
     * @param mensaje El mensaje a mostrar.
     */
    void mostrarMensaje(String mensaje);

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
