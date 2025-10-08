package clinica.modelo.habitacion;

public class TerapiaIntensiva extends Habitacion {
    private double costoDiarioBase;

    public TerapiaIntensiva(int numero, double costoAsignacion, double costoDiarioBase) {
        super(numero, costoAsignacion);
        this.costoDiarioBase = costoDiarioBase;
    }

    @Override
    public double calcularCostoTotal(int diasInternacion) {
        // crecimiento potencial: por ejemplo, cuadrático (día²)
        double total = costoAsignacion;
        for (int i = 1; i <= diasInternacion; i++) {
            total += costoDiarioBase * Math.pow(i, 1.5); // más días → más costoso
        }
        return total;
    }

    @Override
    public String getTipo() {
        return "Terapia Intensiva";
    }
}

