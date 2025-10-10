package clinica.modelo.personas.pacientes;

/** clase extendida de Paciente y diferenciada por rango etario */
public class Joven  extends Paciente {

    @Override
    public Paciente decidirLugar(Paciente otro) {
        return otro.enfrentar(this);
    }

    @Override
    public Paciente enfrentar(Nino n) {
        //Joven vs Niño
        return n;
    }

    @Override
    public Paciente enfrentar(Joven j) {
        //Joven vs Joven
        return this;
    }

    @Override
    public Paciente enfrentar(Mayor m) {
        //Joven vs Mayor
        return this;
    }


    public Joven(String dni, String n_A, String domicilio, String ciudad, String telefono, int numhistclinica) {
        super(dni,n_A,domicilio,ciudad,telefono,numhistclinica);
    }

}
