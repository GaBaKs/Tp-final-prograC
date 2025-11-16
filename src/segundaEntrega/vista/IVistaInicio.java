package segundaEntrega.vista;

import java.awt.event.ActionListener;

/**
 * Define el contrato para la Vista de Inicio de la aplicación.
 * <p>
 */
public interface IVistaInicio {
    void addActionListener(ActionListener l);
    void arranca();
    void cerrar();

}
