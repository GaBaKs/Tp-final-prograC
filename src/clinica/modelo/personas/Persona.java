package clinica.modelo.personas;
/** Clase abstracta que define los atributos de una persona. */
public abstract class Persona {
    /**Representa el DNI de la persona.*/
    private String dni;
    /**Representa el nombre y apellido de la persona.*/
    private String N_A;
    /**Represnta el domicilio de la persona.*/
    private String domicilio;
    /**Representa la ciudad de la persona.*/
    private String ciudad;
    /**Representa le numero telefonico de la persona.*/
    private String telefono;

    public Persona(String dni, String n_A, String domicilio, String ciudad,String telefono) {
        this.dni = dni;
        N_A = n_A;
        this.domicilio = domicilio;
        this.ciudad = ciudad;
        this.telefono = telefono;
    }

    /** Devuelve el nombre de la persona.*/
    public String getN_A() {
        return N_A;
    }

    /** Setea el nombre de la persona. */
    public void setN_A(String n_A)
    {
        N_A = n_A;
    }

    public String getDni()
    {
        return dni;
    }

    public String getCiudad()
    {
        return ciudad;
    }

    public void setCiudad(String ciudad)
    {
        this.ciudad = ciudad;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    public String getDomicilio()
    {
        return domicilio;
    }

    public void setDomicilio(String domicilio)
    {
        this.domicilio = domicilio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Persona)) return false;
        Persona persona = (Persona) o;
        return this.dni.equals(persona.dni);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(dni);
    }
}


