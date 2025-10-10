package clinica.modelo.habitacion;

import clinica.modelo.personas.pacientes.Paciente;

/**
 * Clase abstracta que representa una habitacion en la clinica.
 */
public abstract class Habitacion {
    /** Numero de habitacio*/
    protected int numero;
    /** Costo de asignacion de la habitacion, costo fijo designado por la clinica */
    protected double costoAsignacion; // costo fijo al asignar la habitación
    /** Paciente dentro de la habitacion, si no lo hubiese este seria {@code null} */
    protected Paciente paciente;      // paciente internado (puede ser null)
    /** Atributo que indica si la habitacion esta ocupada o no */
    protected boolean ocupada;

    //CONSTRUCTOR
    public Habitacion(int numero, double costoAsignacion) {
        this.numero = numero;
        this.costoAsignacion = costoAsignacion;
        this.ocupada = false;
    }

    /**
     *  Declara el metodo abstracto para calcular costos
     * @param diasInternacion
     * @return CostoTotal, un dato de tipo double
     */
    public abstract double calcularCostoTotal(int diasInternacion);

    /**
     * Asigna una habitacion a un paciente, si la esta se encuentra ocupada lanza una excepcion del tipo IllegalStateException
     * @param paciente
     * @throws IllegalStateException
     */
    public void asignarPaciente(Paciente paciente) throws IllegalStateException {
        if (ocupada) {
            throw new IllegalStateException("La habitación ya está ocupada");
        }
        this.paciente = paciente;
        this.ocupada = true;
    }

    /**
     * Libera la habitacion poniendo al paciente en {@code null} y el atributo booleano que indica si esta ocupada en false
     */
    public void liberarHabitacion() {
        this.paciente = null;
        this.ocupada = false;
    }

    //GETTERS
    public boolean isOcupada() {
        return ocupada;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public int getNumero() {
        return numero;
    }

    public abstract String getTipo();
}
