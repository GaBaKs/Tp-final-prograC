package segundaEntrega.persistencia.DAOAsociadoYDTO;

import java.io.Serializable;

/**
 * Esta clase implementa {@link Serializable} y representa un Objeto de Transferencia de Datos (DTO) para la entidad Asociado.
 * </p>
 */
public class DTOAsociado implements Serializable
{
    private String dni;
    private String nombreApellido;
    private String domicilio;
    private String ciudad;
    private String telefono;
    private int numSolicitudes;

    public DTOAsociado(){}

    public DTOAsociado(String dni, String nombreApellido, String domicilio, String ciudad, String telefono, int numSolicitudes) {
        this.dni = dni;
        this.nombreApellido = nombreApellido;
        this.domicilio = domicilio;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.numSolicitudes = numSolicitudes;
    }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getNombreApellido() { return nombreApellido; }
    public void setNombreApellido(String nombreApellido) { this.nombreApellido = nombreApellido; }
    public String getDomicilio() { return domicilio; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public int getNumSolicitudes() { return numSolicitudes; }
    public void setNumSolicitudes(int numSolicitudes) { this.numSolicitudes = numSolicitudes; }
}
