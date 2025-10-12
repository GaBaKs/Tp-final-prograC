package excepciones;
/**Excepcion que indica que el rango etario del paciente no es valido.*/
public class PacienteInvalidoException extends RuntimeException {
    public PacienteInvalidoException(String message) {
        super(message);
    }
}
