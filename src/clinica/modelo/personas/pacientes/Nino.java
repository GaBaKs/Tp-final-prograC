package clinica.modelo.personas.pacientes;

/** Clase extendida de Paciente y diferenciada por rango etario. */
public class Nino extends Paciente {

    public Nino(String dni, String n_A, String domicilio, String ciudad, String telefono, int numhistclinica) {
        super(dni,n_A,domicilio,ciudad,telefono,numhistclinica);
    }

    @Override
        public Paciente decidirLugar(Paciente otro) {
            return otro.enfrentar(this);
        }

    /**  Devuelve la prioridad entre un nino y otro.
     * @param n
     * @return Nino
     */
    @Override
        public Paciente enfrentar(Nino n) {
            //Niño vs Niño
            return this;
        }

    /**  Devuelve la prioridad entre un nino y un joven.
     * @param j
     * @return Joven
     */
        @Override
        public Paciente enfrentar(Joven j) {
            //Niño vs Joven
            return this;
        }

    /**  Devuelve la prioridad entre un nino y un mayor.
     * @param m
     * @return Mayor
     */
        @Override
        public Paciente enfrentar(Mayor m) {
            //Niño vs Mayor
            return m;
        }

}
