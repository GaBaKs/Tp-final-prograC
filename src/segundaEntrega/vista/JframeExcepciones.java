package segundaEntrega.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;

/**
 * Ventana utilizada para la visualización de mensajes de error dentro del
 * sistema de gestión de la clínica.
 */
public class JframeExcepciones extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldError;
    private JButton btnNewButton;



    public JframeExcepciones() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 610, 160);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(this.contentPane);

        this.textFieldError = new JTextField();
        this.textFieldError.setEditable(false);
        this.textFieldError.setText("ERROR:");
        this.textFieldError.setFont(new Font("Segoe UI", Font.BOLD, 17));
        this.contentPane.add(this.textFieldError);
        this.textFieldError.setColumns(10);

        this.btnNewButton = new JButton("Aceptar");
        this.contentPane.add(this.btnNewButton);

    }

}
