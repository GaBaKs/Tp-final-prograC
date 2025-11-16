package segundaEntrega.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/** Vista principal de la simulación del sistema de ambulancias. */
public class JframeSimulacion extends JFrame implements IVistaSimulacion, KeyListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel Principal;
    private JPanel movimientosAsociado;
    private JTextArea txtAsociado;
    private JPanel panel;
    private JPanel operario;
    private JPanel estadoAmbulancia;
    private JPanel simulacion;
    private JPanel izquierda;
    private JButton btnIniciar;
    private JButton btnFinalizar;
    private JPanel sim;
    private JPanel panel_2;
    private JLabel lblCantSol;
    private JTextField cantSol;
    private JPanel panel_3;
    private JLabel lblEstadoAmbulancia;
    private JTextArea textAreaEstadoAmbulancia;
    private JLabel lblCantAsociados;
    private JTextField cantAso;
    private JScrollPane scrollPane_1;
    private JLabel lblN_M;
    private JTextField textFieldN_M;
    private JPanel panel_1;
    private JButton btnSolMan;
    private JPanel SolMan;


    public JframeSimulacion() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        this.contentPane = new JPanel();
        this.contentPane.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(this.contentPane);

        this.contentPane.setLayout(new BorderLayout(5, 5));

        this.Principal = new JPanel();
        this.Principal.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        this.contentPane.add(this.Principal, BorderLayout.CENTER);
        this.Principal.setLayout(new GridLayout(1, 1, 0, 0));

        this.panel = new JPanel();
        this.panel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        this.Principal.add(this.panel);

        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWeights = new double[]{1.0};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0};
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
        this.operario.setLayout(new GridLayout(2, 0, 0, 0));

        this.panel_1 = new JPanel();
        this.operario.add(this.panel_1);

        this.lblN_M = new JLabel("Nombre y apellido");
        this.panel_1.add(this.lblN_M);

        this.textFieldN_M = new JTextField();
        this.panel_1.add(this.textFieldN_M);
        this.textFieldN_M.setColumns(10);

        this.SolMan = new JPanel();
        this.operario.add(this.SolMan);

        this.btnSolMan = new JButton("Solicitar mantenimiento");
        this.SolMan.add(this.btnSolMan);

        this.estadoAmbulancia = new JPanel();
        this.estadoAmbulancia.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        this.izquierda.add(this.estadoAmbulancia);
        this.estadoAmbulancia.setBorder(new TitledBorder(null, "Ambulancia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        this.estadoAmbulancia.setLayout(new GridLayout(0, 1, 0, 0));

        this.panel_3 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) this.panel_3.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        this.estadoAmbulancia.add(this.panel_3);

        this.lblEstadoAmbulancia = new JLabel("Estado de la ambulancia:");
        this.lblEstadoAmbulancia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.lblEstadoAmbulancia.setVerticalAlignment(SwingConstants.BOTTOM);
        this.lblEstadoAmbulancia.setHorizontalAlignment(SwingConstants.LEFT);
        this.panel_3.add(this.lblEstadoAmbulancia);

        this.textAreaEstadoAmbulancia = new JTextArea(2, 15);
        this.textAreaEstadoAmbulancia.setEditable(false);
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


        this.simulacion.setLayout(new BorderLayout());

        this.sim = new JPanel();
        this.simulacion.add(this.sim, BorderLayout.CENTER);

        this.lblCantSol = new JLabel("Cantidad de solicitudes");
        this.sim.add(this.lblCantSol);

        this.cantSol = new JTextField();
        this.sim.add(this.cantSol);
        this.cantSol.setColumns(5);

        this.lblCantAsociados = new JLabel("Cantidad de asociados");
        this.sim.add(this.lblCantAsociados);

        this.cantAso = new JTextField();
        this.sim.add(this.cantAso);
        this.cantAso.setColumns(5);

        this.panel_2 = new JPanel();
        this.simulacion.add(this.panel_2, BorderLayout.EAST);

        this.btnIniciar = new JButton("Iniciar");
        this.panel_2.add(this.btnIniciar);

        this.btnFinalizar = new JButton("Finalizar");
        this.panel_2.add(this.btnFinalizar);

        this.movimientosAsociado = new JPanel();
        this.movimientosAsociado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        this.contentPane.add(this.movimientosAsociado, BorderLayout.EAST);
        this.movimientosAsociado.setBorder(new TitledBorder(null, "Notificaciones del Asociado", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        this.movimientosAsociado.setLayout(new GridLayout(1,1,0,0));
        this.scrollPane_1 = new JScrollPane();
        this.movimientosAsociado.add(this.scrollPane_1);


        this.txtAsociado = new JTextArea(5, 25);
        this.txtAsociado.setEditable(false);
        this.scrollPane_1.setViewportView(this.txtAsociado);
        this.txtAsociado.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        this.movimientosAsociado.setPreferredSize(new java.awt.Dimension(600, 200));

        pack();
        setSize(900, 400);
        setLocationRelativeTo(null);
    }

    /** Obtiene el campo de texto donde el usuario ingresa la cantidad de solicitudes. */
    public JTextField getCantSolicitudes(){
        return this.cantSol;
    }


    /** Actualiza la visualización del estado actual de la ambulancia. */
    public void setEstadoAmbulancia(String estado)
    {
        this.textAreaEstadoAmbulancia.setText(estado);
        assert estado!=null:"no puede tener un string nulo";
    }

    /** Agrega una notificación o movimiento generado por la ambulancia */
    public void addAccionAmbulancia(String accion) {
        this.txtAsociado.append(accion + "\n");
    }

    // para manejar la ventana
    /**Hace visible la ventana de simulación.*/
    public void arranca() {
        this.setVisible(true);
    }

    /** Cierra y libera los recursos asociados a la ventana.*/
    public void cerrar() {
        this.dispose();
    }

    /**  Agrega un mensaje al listado de movimientos generados por los asociados. */
    public void appendMovimientosAsociados(String mensaje)
    {
        this.txtAsociado.append(mensaje+"\n");
    }

    /** Devuelve el campo donde se ingresa el nombre y apellido del operario. */
    public JTextField getNombreyApellido() {
        return this.textFieldN_M;
    }

    public JTextField getCantAsociados(){
        return this.cantAso;
    }


    //para conectar al controlador
    /** Registra un ActionListener para los botones principales de la vista. */
    @Override
    public void addActionListener(ActionListener l) {
        this.btnIniciar.addActionListener(l);
        this.btnFinalizar.addActionListener(l);
        this.btnSolMan.addActionListener(l);
    }

    /** Evento de teclado ejecutado al soltar una tecla. */
    public void keyPressed(KeyEvent e)
    {
    }

    public void keyReleased(KeyEvent e)
    {
        try
        {
            int h = Integer.parseInt(this.getCantSolicitudes().getText());
            this.btnIniciar.setEnabled(true);
        } catch (NumberFormatException exception)
        {
            this.btnIniciar.setEnabled(false);

        }
    }

    public void keyTyped(KeyEvent e)
    {
    }

}