package clinica.modelo.personas.pacientes;

/** Clase extendida de Paciente y diferenciada por rango etario. */
public class Joven  extends Paciente {

    @Override
    public Paciente decidirLugar(Paciente otro) {
        return otro.enfrentar(this);
    }

    /**  Devuelve la prioridad entre un joven y un nino.
     *
     * @param n
     * @return Nino
     */
    @Override
    public Paciente enfrentar(Nino n) {
        //Joven vs Niño
        return n;
    }

    /**  Devuelve la prioridad entre un joven y otro.
     *
     * @param j
     * @return Joven
     */
    @Override
    public Paciente enfrentar(Joven j) {
        //Joven vs Joven
        return this;
    }

    /**  Devuelve la prioridad entre un joven y un mayor.
     * @param m
     * @return Mayor
     */
    @Override
    public Paciente enfrentar(Mayor m) {
        //Joven vs Mayor
        return this;
    }


    public Joven(String dni, String n_A, String domicilio, String ciudad, String telefono, int numhistclinica) {
        super(dni,n_A,domicilio,ciudad,telefono,numhistclinica);
    }

}
