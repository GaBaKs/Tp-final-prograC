package segundaEntrega.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class JframeSimulacion extends JFrame implements IVistaSimulacion {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel Principal;
    private JPanel accionesAmbulancia;
    private JTextArea txtAmbulancia;
    private JPanel panel;
    private JPanel operario;
    private JPanel estadoAmbulancia;
    private JPanel simulacion;
    private JPanel izquierda;
    private JButton btnIniciar;
    private JButton btnFinalizar;
    private JPanel panel_1;
    private JPanel panel_2;
    private JLabel lblNewLabel;
    private JTextField cantSol;
    private JPanel panel_3;
    private JLabel lblEstadoAmbulancia;
    private JTextArea textAreaEstadoAmbulancia;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JframeSimulacion frame = new JframeSimulacion();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public JframeSimulacion() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 930, 711);
        this.contentPane = new JPanel();
        this.contentPane.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(this.contentPane);
        this.contentPane.setLayout(new GridLayout(0, 2, 0, 0));

        this.Principal = new JPanel();
        this.Principal.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        this.contentPane.add(this.Principal);
        this.Principal.setLayout(new GridLayout(1, 2, 0, 0));

        this.panel = new JPanel();
        this.panel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        this.Principal.add(this.panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{452, 0};
        gbl_panel.rowHeights = new int[]{331, 331, 0, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
        this.panel.setLayout(gbl_panel);

        this.izquierda = new JPanel();
        this.izquierda.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        GridBagConstraints gbc_izquierda = new GridBagConstraints();
        gbc_izquierda.gridheight = 3;
        gbc_izquierda.fill = GridBagConstraints.BOTH;
        gbc_izquierda.insets = new Insets(0, 0, 5, 0);
        gbc_izquierda.gridx = 0;
        gbc_izquierda.gridy = 0;
        this.panel.add(this.izquierda, gbc_izquierda);
        this.izquierda.setLayout(new GridLayout(0, 1, 0, 0));

        this.operario = new JPanel();
        this.operario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        this.izquierda.add(this.operario);
        this.operario.setBorder(new TitledBorder(null, "Operario", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        this.estadoAmbulancia = new JPanel();
        this.estadoAmbulancia.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        this.izquierda.add(this.estadoAmbulancia);
        this.estadoAmbulancia.setBorder(new TitledBorder(null, "Ambulancia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        this.estadoAmbulancia.setLayout(new GridLayout(0, 1, 0, 0));

        this.panel_3 = new JPanel();
        this.estadoAmbulancia.add(this.panel_3);

        this.lblEstadoAmbulancia = new JLabel("Estado de la ambulancia:");
        this.lblEstadoAmbulancia.setVerticalAlignment(SwingConstants.BOTTOM);
        this.lblEstadoAmbulancia.setHorizontalAlignment(SwingConstants.LEFT);
        this.panel_3.add(this.lblEstadoAmbulancia);

        this.textAreaEstadoAmbulancia = new JTextArea();
        this.panel_3.add(this.textAreaEstadoAmbulancia);


        this.simulacion = new JPanel();
        this.simulacion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        this.simulacion.setBorder(new TitledBorder(null, "Simulacion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GridBagConstraints gbc_simulacion = new GridBagConstraints();
        gbc_simulacion.gridheight = 2;
        gbc_simulacion.insets = new Insets(0, 0, 5, 0);
        gbc_simulacion.fill = GridBagConstraints.BOTH;
        gbc_simulacion.gridx = 0;
        gbc_simulacion.gridy = 3;



        this.panel.add(this.simulacion, gbc_simulacion);
        this.simulacion.setLayout(new GridLayout(1, 0, 0, 0));

        this.panel_1 = new JPanel();
        this.simulacion.add(this.panel_1);

        this.lblNewLabel = new JLabel("Cantidad de solicitudes");
        this.panel_1.add(this.lblNewLabel);

        this.cantSol = new JTextField();
        this.panel_1.add(this.cantSol);
        this.cantSol.setColumns(10);

        this.panel_2 = new JPanel();
        this.simulacion.add(this.panel_2);

        this.btnIniciar = new JButton("Iniciar");
        this.panel_2.add(this.btnIniciar);

        this.btnFinalizar = new JButton("Finalizar");
        this.panel_2.add(this.btnFinalizar);

        this.accionesAmbulancia = new JPanel();
        this.accionesAmbulancia.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        this.contentPane.add(this.accionesAmbulancia);
        this.accionesAmbulancia.setBorder(new TitledBorder(null, "Acciones de la ambulancia", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        this.txtAmbulancia = new JTextArea();
        this.txtAmbulancia.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        this.txtAmbulancia.setText("Aca van escritas las acciones de la ambulancia");
        this.accionesAmbulancia.add(this.txtAmbulancia);

    }
    public JTextField getCantSolicitudes(){
        return this.cantSol; //esto eta bien asi tipo text field?
    }
    // para que el controlador actualice la vista(no se si esto deberia estar aca la verdad)
    public void setEstadoAmbulancia(String estado) {
        this.textAreaEstadoAmbulancia.setText(estado);
    }

    public void addAccionAmbulancia(String accion) {
        this.txtAmbulancia.append(accion + "\n");
    }

    // para manejar la ventana
    public void arranca() {
        this.setVisible(true);
    }

    public void cerrar() {
        this.dispose();
    }
    //para conectar al controlador
    @Override
    public void addActionListener(ActionListener l) {
        this.btnIniciar.addActionListener(l);
        this.btnFinalizar.addActionListener(l);
    } //??? q onda esto
}
