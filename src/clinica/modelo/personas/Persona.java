package clinica.modelo.personas;
/** clase abstracta que define los atributos de una persona */
public abstract class Persona {
    String dni;
    String N_A;
    String domicilio;
    String ciudad;
    String telefono;

    public Persona(String dni, String n_A, String domicilio, String ciudad,String telefono) {
        this.dni = dni;
        N_A = n_A;
        this.domicilio = domicilio;
        this.ciudad = ciudad;
        this.telefono = telefono;
    }

    public String getN_A() {
        return N_A;
    }

    public void setN_A(String n_A) {
        N_A = n_A;
    }

    public String getDni() {
        return dni;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
}


