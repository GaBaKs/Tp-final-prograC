package clinica.modelo.habitacion;

import clinica.modelo.personas.pacientes.Paciente;
import java.util.ArrayList;
import java.lang.reflect.Array;

/**
 * Clase abstracta que representa una habitacion en la clinica.
 */
public abstract class Habitacion
{
    /** Numero de habitacio*/
    protected int numero;
    /** Costo de asignacion de la habitacion, costo fijo designado por la clinica */
    protected double costoAsignacion; // costo fijo al asignar la habitación

    //CONSTRUCTOR
    public Habitacion(int numero, double costoAsignacion)
    {
        this.numero = numero;
        this.costoAsignacion = costoAsignacion;
    }

    /**
     *  Declara el metodo abstracto para calcular costos
     * Como pre-condicion la variable diasInternacion va a ser >=0
     * El metodo va a devolver un costo total >=0
     * @param diasInternacion
     * @return CostoTotal, un dato de tipo double
     */
    public abstract double calcularCostoTotal(int diasInternacion);


    public abstract String getTipo();
}
