package excepciones;
 //creo que hace eso jasdjadsj
/** Excepcion que indica que el paciente esta repetido en algun registro. */
public class DuplicadoException extends RuntimeException {
    public DuplicadoException(String message)
    {
        super(message);
    }
}
