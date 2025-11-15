package segundaEntrega.controlador;

import segundaEntrega.modelo.ModeloBD;
import segundaEntrega.modelo.negocio.Asociado;
import segundaEntrega.patrones.facade.Sistema;
import segundaEntrega.persistencia.DAOAsociadoYDTO.DTOAsociado;
import segundaEntrega.vista.IVistaBD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControladorBD implements ActionListener {
    private IVistaBD vista;
    private ModeloBD modelo;

    public ControladorBD(IVistaBD ventana, ModeloBD modelo)
    {
        this.vista=ventana;
        this.modelo=modelo;
        this.vista.addActionListener(this);
        this.vista.actualizaLista(this.modelo.getAsociados());
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
                    modelo.getAmbulanciaCompartida() // Obtenemos la ambulancia del Sistema
            );

            // Delegar al Modelo (que guardará en memoria y BD)
            modelo.agregarAsociado(toDTO(nuevoAsociado));

            // 4. Actualizar la vista y limpiar
            vista.actualizaLista(modelo.getAsociados());
            //esto es lo q hay q mostrat x pantalla
            JOptionPane.showMessageDialog(null, "Asociado agregado exitosamente");


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
                modelo.eliminarAsociado(toDTO(seleccionado));

                // Actualizar Vista
                vista.actualizaLista(modelo.getAsociados());
                JOptionPane.showMessageDialog(null, "Asociado eliminado");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage());
            }
        }
    }

    public DTOAsociado toDTO(Asociado asociado) {
        return modelo.toDTO(asociado);
    }
    public Asociado fromDTO(DTOAsociado dto) {
        return modelo.fromDTO(dto);
    }

}
