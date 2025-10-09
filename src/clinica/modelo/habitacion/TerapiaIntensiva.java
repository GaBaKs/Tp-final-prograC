package clinica.modelo.habitacion;

public class TerapiaIntensiva extends Habitacion {
    private double costoTerInt;

    public TerapiaIntensiva(int numero, double costoAsignacion, double costoTerInt) {
        super(numero, costoAsignacion);
        this.costoTerInt = costoTerInt;
    }

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

