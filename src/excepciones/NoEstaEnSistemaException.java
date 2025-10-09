package excepciones;

public class NoEstaEnSistemaException extends RuntimeException {
    public NoEstaEnSistemaException(String message)
    {
        super(message);
    }
}
