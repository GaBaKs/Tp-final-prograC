package excepciones;

/**Excepcion que indica que el paciente no se encuentra en la lista de espera.*/
public class NoEstaEnListaDeEsperaException extends RuntimeException {
    public NoEstaEnListaDeEsperaException(String message)
    {
        super(message);
    }
}
