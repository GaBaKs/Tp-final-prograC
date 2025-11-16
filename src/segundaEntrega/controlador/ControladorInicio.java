package segundaEntrega.controlador;

import segundaEntrega.modelo.ModeloSimulacion;
import segundaEntrega.modelo.ModeloBD;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador principal para la ventana de inicio ({@link IVistaInicio}).
 * <p>
 * Actúa como el punto de entrada de la aplicación, gestionando la navegación
 * a los módulos de simulación y base de datos.
 * <p>
 * Es responsable de:
 * <ul>
 * <li>Escuchar los eventos de acción (botones) de la vista de inicio.</li>
 * <li>Instanciar los modelos principales ({@link ModeloBD}, {@link ModeloSimulacion}).</li>
 * <li>Cerrar la vista de inicio y lanzar los controladores y vistas
 * de los módulos seleccionados.</li>
 */
public class ControladorInicio implements ActionListener {
    /** {@link ModeloSimulacion } */
    private ModeloSimulacion inicioSimulacion;
    /** {@link IVistaInicio } */
    private IVistaInicio vista;
    /** {@link Ambulancia } */
    private Ambulancia ambulancia;
    /** {@link ModeloBD } */
    private ModeloBD modeloBD;
    /** {@link ModeloSimulacion } */
    private ModeloSimulacion modeloSimulacion;



    public ControladorInicio(ModeloSimulacion inicioSimulacion, IVistaInicio vista)
    {
        this.inicioSimulacion = inicioSimulacion;
        this.vista = vista;
        this.vista.addActionListener(this);
        this.ambulancia = Ambulancia.getInstance(); // Usamos el Singleton
        this.modeloBD = new ModeloBD(this.ambulancia);
        this.modeloSimulacion = new ModeloSimulacion(this.ambulancia); // Este modelo usará la ambulancia
    }


    /**
     * Maneja los eventos de acción provenientes de la vista de inicio.
     * <p>
     * Determina qué módulo lanzar basándose en el
     * comando recibido.
     * </p>
     * @param e El {@link ActionEvent} disparado (ej. clic en un botón).
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("Modulo de simulacion")) {
            // oculto la vista de inicio
            this.vista.cerrar();

            IVistaSimulacion vistasimulacion = new JframeSimulacion();
            vistasimulacion.disableSolMan();
            // Pasamos el modelo de simulación y la lista de asociados del modeloBD
            ControladorSimulacion contprincipal = new ControladorSimulacion(vistasimulacion, this.modeloSimulacion,this.modeloBD.getAsociados());


            MiObserverVista miObserverVista = new MiObserverVista(vistasimulacion, this.ambulancia);


            // muestro la vista de la simulacion
            vistasimulacion.arranca();

        } else if (comando.equals("Modulo base de datos")) {
            // oculto la vista de inicio
            this.vista.cerrar();

            IVistaBD vistaBD = new JframeBD();
            // Le pasamos el modelo de base de datos
            ControladorBD controladorbd = new ControladorBD(vistaBD, this.modeloBD);
            //muestro la vista de la base de datos
            vistaBD.arranca();
        }
    }

}

