package excepciones;

/** Excepcion que indica que la persona ya esta registrada en la base de datos de la clinica, evita duplicarlas. */
public class DuplicadoException extends RuntimeException {
    public DuplicadoException(String message)
    {
        super(message);
    }
}
