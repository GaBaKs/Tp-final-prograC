package excepciones;

/** Excepcion que indica que la ambulancia se encuentra trasladando a un asociado a la clinica. */
public class TrasladandoPacienteExcepcion extends RuntimeException {
    public TrasladandoPacienteExcepcion(String message) {
        super(message);
    }
}
