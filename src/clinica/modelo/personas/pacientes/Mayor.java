package clinica.modelo.personas.pacientes;

/** Clase extendida de Paciente y diferenciada por rango etario. */
public class Mayor extends Paciente {

    @Override
    public Paciente decidirLugar(Paciente otro) {
        return otro.enfrentar(this);
    }

    /**  Devuelve la prioridad entre un mayor y un nino.
     * @param n
     * @return Nino
     */
    @Override
    public Paciente enfrentar(Nino n) {
        //Mayor vs Niño
        return this;
    }

    /**  Devuelve la prioridad entre un mayor y un joven.
     * @param j
     * @return Joven
     */
    @Override
    public Paciente enfrentar(Joven j) {
        //Mayor vs Joven
        return j;
    }

    /**  Devuelve la prioridad entre un mayor y otro mayor.
     * @param m
     * @return Mayor
     */
    @Override
    public Paciente enfrentar(Mayor m) {
        //Mayor vs Mayor
        return this;
    }



    public Mayor(String dni, String n_A, String domicilio, String ciudad, String telefono, int numhistclinica) {
        super(dni,n_A,domicilio,ciudad,telefono,numhistclinica);
    }
}
