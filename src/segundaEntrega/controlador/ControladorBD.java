package segundaEntrega.controlador;

import segundaEntrega.modelo.ModeloBD;
import segundaEntrega.modelo.negocio.Asociado;
import segundaEntrega.patrones.facade.Sistema;
import segundaEntrega.persistencia.DAOAsociadoYDTO.DTOAsociado;
import segundaEntrega.vista.IVistaBD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ControladorBD actúa como intermediario entre la Vista y el Modelo,
 * gestionando las acciones del usuario relacionadas con la administración
 * de asociados en la base de datos.
 */
public class ControladorBD implements ActionListener {
    /** {@link IVistaBD } */
    private IVistaBD vista;
    /** {@link ModeloBD } */
    private ModeloBD modelo;

    public ControladorBD(IVistaBD ventana, ModeloBD modelo)
    {
        this.vista=ventana;
        this.modelo=modelo;
        this.vista.addActionListener(this);
        this.vista.actualizaLista(this.modelo.getAsociados());
    }

    /**
     * Maneja los eventos generados por la vista. Según el comando recibido,
     * ejecuta la operación correspondiente (alta, baja, borrar base, datos de prueba).
     *
     * @param e evento de acción generado por la vista
     */
    public void actionPerformed (ActionEvent e)
    {
        String comando = e.getActionCommand();

        if (comando.equalsIgnoreCase("Alta asociado")) {
            this.altaAsociado();
        } else if (comando.equalsIgnoreCase("Baja asociado")) {
            this.bajaAsociado();
        } else if (comando.equalsIgnoreCase("Agrega datos de prueba")){
            this.agregaDatosPrueba();
        } else if (comando.equalsIgnoreCase("Borrar DB")) {
            this.inicializarBaseDeDatos();
        }
    }

    /**
     * Realiza el alta de un nuevo asociado. Valida los campos de la vista,
     * construye un objeto Asociado, lo convierte en DTO y lo delega al modelo
     * para ser persistido. Actualiza la vista y muestra mensajes al usuario.
     */
    public void altaAsociado() {
        try {
            // Validar campos
            if (vista.getDNI().getText().isEmpty()) {
                throw new Exception("El DNI es obligatorio");
            }

            // Construir el objeto Asociado con datos de la Vista
            String nombreCompleto = vista.getNombre().getText() + " " + vista.getApellido().getText();
            String direccion = vista.getCalle().getText() + " " + vista.getNumero().getText();

            Asociado nuevoAsociado = new Asociado(
                    vista.getDNI().getText(),
                    nombreCompleto,
                    direccion,
                    vista.getCiudad().getText(),
                    vista.getTelefono().getText(),
                    1, // Solicitudes por defecto (o agregar campo en la vista)
                    modelo.getAmbulanciaCompartida() // Obtenemos la ambulancia del Sistema
            );

            // Delegar al Modelo (que guardará en memoria y BD)
            modelo.agregarAsociado(toDTO(nuevoAsociado));

            // Actualizar la vista y limpiar
            vista.actualizaLista(modelo.getAsociados());
            //esto es lo q hay q mostrat x pantalla
            JOptionPane.showMessageDialog(null, "Asociado agregado exitosamente");


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar: " + ex.getMessage());
        }
    }

    /**
     * Elimina el asociado seleccionado en la vista. Solicita al modelo que
     * realice la operación y luego actualiza la lista en pantalla.
     */
    public void bajaAsociado() {
        Asociado seleccionado = this.vista.getAsociadoSeleccionado();

        if (seleccionado == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un asociado de la lista para eliminar");
        } else {
            try {
                // Delegar al Modelo
                modelo.eliminarAsociado(toDTO(seleccionado));

                // Actualizar Vista
                vista.actualizaLista(modelo.getAsociados());
                JOptionPane.showMessageDialog(null, "Asociado eliminado");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage());
            }
        }
    }

    /**
     * Solicita confirmación al usuario para eliminar todos los datos
     * de la base de asociados. Si se confirma, reinicia las tablas mediante
     * el modelo y actualiza la vista.
     */
    public void inicializarBaseDeDatos() {
        try {
            // Pedir confirmación
            int opcion = JOptionPane.showConfirmDialog(
                    null, // Debería ser la vista (JFrame)
                    "¿Está seguro que desea borrar TODOS los asociados?\nEsta acción es irreversible.",
                    "Confirmar Inicialización",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (opcion == JOptionPane.YES_OPTION) {
                // Llamar al Modelo (que llamará al DAO)
                modelo.inicializarTablas();

                // Actualizar la vista (la lista ahora está vacía)
                vista.actualizaLista(modelo.getAsociados());
                JOptionPane.showMessageDialog(null, "Base de datos inicializada correctamente.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al inicializar la BD: " + e.getMessage());
        }
    }

    /**
     * Carga en la base de datos un conjunto fijo de asociados para pruebas.
     * Luego refresca la vista.
     */
    public void agregaDatosPrueba(){
        try {
            Asociado asociado1= new Asociado("12345623","Pipo","Florisbelo 2738","Mar Del Plata","2235379123",4,modelo.getAmbulanciaCompartida());
            modelo.agregarAsociado(toDTO(asociado1));
            Asociado asociado2= new Asociado("29371910","Pepe","Independencia 5792","Mar Del Plata","2235279023",4,modelo.getAmbulanciaCompartida());
            modelo.agregarAsociado(toDTO(asociado2));
            Asociado asociado3= new Asociado("45921891","Pepo","Chacabuco 3761","Mar Del Plata","2234379023",4,modelo.getAmbulanciaCompartida());
            modelo.agregarAsociado(toDTO(asociado3));
            Asociado asociado4= new Asociado("43861537","Papo","Brown 4829","Mar Del Plata","2235378023",4,modelo.getAmbulanciaCompartida());
            modelo.agregarAsociado(toDTO(asociado4));
            Asociado asociado5= new Asociado("46919022","Pupo","Calle 4 290","Mar Del Plata","2235379023",4,modelo.getAmbulanciaCompartida());
            modelo.agregarAsociado(toDTO(asociado5));

            // Actualizar la vista y limpiar
            vista.actualizaLista(modelo.getAsociados());
            //esto es lo q hay q mostrat x pantalla
            JOptionPane.showMessageDialog(null, "Asociado agregado exitosamente");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar: " + ex.getMessage());
        }
    }

    /**
     * inicialización de la base de datos.
     */
    public void inicializarBD() {
        try {
            // Pide confirmación a la Vista
            boolean confirmado = vista.mostrarConfirmacion(
                    "Confirmar Inicialización",
                    "¿Está seguro que desea borrar TODOS los asociados?\nEsta acción es irreversible."
            );

            if (confirmado) {
                // 2. Llamar al Modelo
                modelo.inicializarTablas();
                vista.actualizaLista(modelo.getAsociados());
                vista.mostrarMensaje("Base de datos inicializada correctamente.");
            }

        } catch (Exception e) {
            vista.mostrarError("Error al inicializar la BD: " + e.getMessage());
        }
    }

    /**
     * Convierte un objeto Asociado de la lógica interna a un DTO para la persistencia.
     *
     * @param asociado instancia de Asociado
     * @return DTOAsociado correspondiente
     */
    public DTOAsociado toDTO(Asociado asociado) {
        return modelo.toDTO(asociado);
    }

    /**
     * Convierte un DTOAsociado de la base de datos a un objeto Asociado de lógica.
     *
     * @param dto DTOAsociado origen
     * @return objeto Asociado equivalente
     */
    public Asociado fromDTO(DTOAsociado dto) {
        return modelo.fromDTO(dto);
    }

}
