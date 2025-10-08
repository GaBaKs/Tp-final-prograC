package clinica.modelo.pacientes;

public class Nino extends Paciente {

    public Nino(String dni, String n_A, String domicilio, String ciudad, String telefono, int numhistclinica) {
        super(dni,n_A,domicilio,ciudad,telefono,numhistclinica);
    }

    @Override
        public Paciente decidirLugar(Paciente otro) {
            return otro.enfrentar(this);
        }

        @Override
        public Paciente enfrentar(Nino n) {
            //Niño vs Niño
            return this;
        }

        @Override
        public Paciente enfrentar(Joven j) {
            //Niño vs Joven
            return this;
        }

        @Override
        public Paciente enfrentar(Mayor m) {
            //Niño vs Mayor
            return m;
        }


    @Override
    public void tipoPaciente() {

    }
}
