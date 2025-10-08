package clinica.modelo.habitacion;


import clinica.modelo.personas.pacientes.Paciente;

public abstract class Habitacion {
    protected int numero;
    protected double costoAsignacion; // costo fijo al asignar la habitación
    protected Paciente paciente;      // paciente internado (puede ser null)
    protected boolean ocupada;

    public Habitacion(int numero, double costoAsignacion) {
        this.numero = numero;
        this.costoAsignacion = costoAsignacion;
        this.ocupada = false;
    }

    public abstract double calcularCostoTotal(int diasInternacion);

    public void asignarPaciente(Paciente paciente) throws IllegalStateException {
        if (ocupada) {
            throw new IllegalStateException("La habitación ya está ocupada");
        }
        this.paciente = paciente;
        this.ocupada = true;
    }

    public void liberarHabitacion() {
        this.paciente = null;
        this.ocupada = false;
    }

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
