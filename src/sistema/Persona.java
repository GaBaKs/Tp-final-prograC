package sistema;

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

}


