package excepciones;

/** Excepcion que indica que el paciente no se encuentra registrado en los archivos de la clinica. */
public class NoEstaEnSistemaException extends RuntimeException {
    public NoEstaEnSistemaException(String message)
    {
        super(message);
    }
}
