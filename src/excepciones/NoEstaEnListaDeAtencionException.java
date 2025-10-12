package excepciones;

/**Excepcion que indica que el paciente no se encuentra en la lista de atencion.*/
public class NoEstaEnListaDeAtencionException extends RuntimeException
{
    public NoEstaEnListaDeAtencionException(String message)
    {
        super(message);
    }
}
