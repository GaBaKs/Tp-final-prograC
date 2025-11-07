package ventana;

import segundaEntrega.vista.IVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;

public class Jframeprincipal extends JFrame implements IVista {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private final JTextField cantAsociados = new JTextField();
    private JTextField Cantop;
    private JButton gbc_btnComenzarSimulacion;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Jframeprincipal frame = new Jframeprincipal();
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
    public Jframeprincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 489, 587);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{253, 86, 0};
        gbl_contentPane.rowHeights = new int[]{70, 70, 45, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JLabel lblcantAso = new JLabel("Cantidad de Asociados");
        lblcantAso.setFont(new Font("Sitka Text", Font.PLAIN, 13));
        lblcantAso.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblcantAso = new GridBagConstraints();
        gbc_lblcantAso.fill = GridBagConstraints.VERTICAL;
        gbc_lblcantAso.insets = new Insets(0, 0, 5, 5);
        gbc_lblcantAso.gridx = 0;
        gbc_lblcantAso.gridy = 0;
        contentPane.add(lblcantAso, gbc_lblcantAso);
        GridBagConstraints gbc_cantAsociados = new GridBagConstraints();
        gbc_cantAsociados.fill = GridBagConstraints.HORIZONTAL;
        gbc_cantAsociados.insets = new Insets(0, 0, 5, 0);
        gbc_cantAsociados.gridx = 1;
        gbc_cantAsociados.gridy = 0;
        contentPane.add(cantAsociados, gbc_cantAsociados);
        cantAsociados.setColumns(10);

        JButton btnComenzarSimulacion = new JButton("Comenzar Simulacion");
        btnComenzarSimulacion.setBackground(new Color(0, 64, 0));
        btnComenzarSimulacion.setForeground(new Color(0, 0, 0));
        btnComenzarSimulacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        JLabel lblcantop = new JLabel("Cantidad de Operarios");
        lblcantop.setFont(new Font("Sitka Text", Font.PLAIN, 13));
        lblcantop.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblcantop = new GridBagConstraints();
        gbc_lblcantop.fill = GridBagConstraints.BOTH;
        gbc_lblcantop.insets = new Insets(0, 0, 5, 5);
        gbc_lblcantop.gridx = 0;
        gbc_lblcantop.gridy = 1;
        contentPane.add(lblcantop, gbc_lblcantop);

        Cantop = new JTextField();
        Cantop.setColumns(10);
        GridBagConstraints gbc_cantop = new GridBagConstraints();
        gbc_cantop.fill = GridBagConstraints.HORIZONTAL;
        gbc_cantop.insets = new Insets(0, 0, 5, 0);
        gbc_cantop.gridx = 1;
        gbc_cantop.gridy = 1;
        contentPane.add(Cantop, gbc_cantop);
        GridBagConstraints gbc_btnComenzarSimulacion = new GridBagConstraints();
        gbc_btnComenzarSimulacion.gridwidth = 2;
        gbc_btnComenzarSimulacion.fill = GridBagConstraints.BOTH;
        gbc_btnComenzarSimulacion.gridx = 0;
        gbc_btnComenzarSimulacion.gridy = 2;
        contentPane.add(btnComenzarSimulacion, gbc_btnComenzarSimulacion);

    }


    @Override
    public void addActionListener(ActionListener actionListener)
    {
        this.gbc_btnComenzarSimulacion.addActionListener(actionListener);

    }

}