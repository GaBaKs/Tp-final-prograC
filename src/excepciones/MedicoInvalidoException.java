package excepciones;

/**Excepcion que indica que la especialidad del medico no es valida.*/
public class MedicoInvalidoException extends RuntimeException {
    public MedicoInvalidoException(String message) {
        super(message);
    }
}
