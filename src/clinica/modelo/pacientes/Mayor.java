package clinica.modelo.pacientes;

public class Mayor extends Paciente {

    @Override
    public Paciente decidirLugar(Paciente otro) {
        return otro.enfrentar(this);
    }

    @Override
    public Paciente enfrentar(Nino n) {
        //Mayor vs Niño
        return this;
    }

    @Override
    public Paciente enfrentar(Joven j) {
        //Mayor vs Joven
        return j;
    }

    @Override
    public Paciente enfrentar(Mayor m) {
        //Mayor vs Mayor
        return this;
    }



    public Mayor(String dni, String n_A, String domicilio, String ciudad, String telefono, int numhistclinica) {
        super(dni,n_A,domicilio,ciudad,telefono,numhistclinica);
    }

    @Override
    public void tipoPaciente() {

    }
}
