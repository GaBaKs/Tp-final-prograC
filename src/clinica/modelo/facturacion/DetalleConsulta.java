package clinica.modelo.facturacion;

import clinica.modelo.medicos.Medico;

import java.text.DecimalFormat;

public class DetalleConsulta {
    private Medico medico;
    private double honorarioCobrado; // honorario + 20%

    public DetalleConsulta(Medico medico) {
        this.medico = medico;
        this.honorarioCobrado = medico.honorario() * 1.20;
    }

    public double getSubtotal() {
        return honorarioCobrado;
    }

    public Medico getMedico() {
        return medico;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return String.format(
                "Médico: %-20s | Especialidad: %-10s | Subtotal: $%s",
                medico.getN_A(), medico.getEspecialidad(), df.format(honorarioCobrado)
        );
    }
}

