package segundaEntrega.controlador;

import segundaEntrega.modelo.negocio.Asociado;
import segundaEntrega.patrones.facade.Sistema;
import segundaEntrega.vista.IVistaBD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControladorBD implements ActionListener {
    private IVistaBD vista;
    private Sistema modelo;

    public ControladorBD(IVistaBD ventana, Sistema modelo)
    {
        this.vista=ventana;
        this.modelo=modelo;
        this.vista.addActionListener(this);
        this.vista.actualizaLista(this.modelo.getAsociadosSistema());
    }

    public void actionPerformed (ActionEvent e)
    {
        String comando = e.getActionCommand();

        if (comando.equalsIgnoreCase("Alta asociado")) {
            this.altaAsociado();
        } else if (comando.equalsIgnoreCase("Baja asociado")) {
            this.bajaAsociado();
        } else if (comando.equalsIgnoreCase("Borrar DB")) {

        }
    }

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
                    modelo.getAmbulancia() // Obtenemos la ambulancia del Sistema
            );

            // Delegar al Modelo (que guardará en memoria y BD)
            modelo.agregarAsociado(nuevoAsociado);

            // 4. Actualizar la vista y limpiar
            vista.actualizaLista(modelo.getAsociadosSistema());
            JOptionPane.showMessageDialog(null, "Asociado agregado exitosamente");
            // Aquí podrías llamar a un método vista.limpiarCampos()

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar: " + ex.getMessage());
        }
    }

    public void bajaAsociado() {
        Asociado seleccionado = this.vista.getAsociadoSeleccionado();

        if (seleccionado == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un asociado de la lista para eliminar");
        } else {
            try {
                // Delegar al Modelo
                modelo.eliminarAsociado(seleccionado);

                // Actualizar Vista
                vista.actualizaLista(modelo.getAsociadosSistema());
                JOptionPane.showMessageDialog(null, "Asociado eliminado");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage());
            }
        }
    }

}
