package clinica.modelo.personas.pacientes;

import excepciones.PacienteInvalidoException;

/** Esta clase aplica el patron factory sobre los pacientes.*/
public class FactoryPaciente {

    /**
     * Crea un nuevo paciente, en el caso que el rango etario introducido no exista se lanza una excepcion.
     * @param tipopaciente
     * @param dni
     * @param n_A
     * @param domicilio
     * @param ciudad
     * @param telefono
     * @param numhistclinica
     * @return Paciente
     * @throws PacienteInvalidoException
     */
    public static Paciente creaPaciente(String tipopaciente, String dni, String n_A, String domicilio, String ciudad, String telefono, int numhistclinica) throws PacienteInvalidoException
    {
        if (tipopaciente.equalsIgnoreCase("Nino")) {
            return new Nino(dni,n_A,domicilio,ciudad,telefono,numhistclinica);
        } else if (tipopaciente.equalsIgnoreCase("Joven")) {
            return new Joven(dni,n_A,domicilio,ciudad,telefono,numhistclinica);
        } else if (tipopaciente.equalsIgnoreCase("Mayor")) {
            return new Mayor(dni,n_A,domicilio,ciudad,telefono,numhistclinica);
        }
        else throw new PacienteInvalidoException("El tipo de paciente ingresado es invalido");
    }
}
