package clinica.modelo.habitacion;

/**
 * Clase que se extiende de la clase abstracta Habitacion, representa un habitacion del tipo terapia intensiva
 */
public class TerapiaIntensiva extends Habitacion {
    /** Representa el valor de la habitacion */
    private double costoTerInt;

    public TerapiaIntensiva(int numero, double costoAsignacion, double costoTerInt) {
        super(numero, costoAsignacion);
        this.costoTerInt = costoTerInt;
    }

    /**
     * Sobreesribe el metodo para el calculo del costo total.
     * @param diasInternacion
     * @return CostoTotal
     */
    @Override
    public double calcularCostoTotal(int diasInternacion) {
        // crecimiento potencial: por ejemplo, cuadrático (día²)
        return costoAsignacion + Math.pow(costoTerInt,diasInternacion); // más días → más costoso
    }

    @Override
    public String getTipo() {
        return "Terapia Intensiva";
    }
}

