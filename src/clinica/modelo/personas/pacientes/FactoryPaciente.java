package clinica.modelo.personas.pacientes;

public class FactoryPaciente {
    public static Paciente creaPaciente(String tipopaciente, String dni, String n_A, String domicilio, String ciudad, String telefono, int numhistclinica)
    {
        if (tipopaciente == null) {
            return null;
        }
        if (tipopaciente.equalsIgnoreCase("Nino")) {
            return new Nino(dni,n_A,domicilio,ciudad,telefono,numhistclinica);
        } else if (tipopaciente.equalsIgnoreCase("Adulto")) {
            return new Joven(dni,n_A,domicilio,ciudad,telefono,numhistclinica);
        } else if (tipopaciente.equalsIgnoreCase("Mayor")) {
            return new Mayor(dni,n_A,domicilio,ciudad,telefono,numhistclinica);
        }
        return null;
    }
}
