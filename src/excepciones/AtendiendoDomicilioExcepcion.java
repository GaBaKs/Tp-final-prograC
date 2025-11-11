package excepciones;

/** Excepcion que indica que la ambulancia se encuentra atendiendo a un asociado a domicilio. */
public class AtendiendoDomicilioExcepcion extends RuntimeException {
    public AtendiendoDomicilioExcepcion(String message) {
        super(message);
    }
}
