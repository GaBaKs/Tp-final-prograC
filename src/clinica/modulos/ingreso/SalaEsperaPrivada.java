package clinica.modulos.ingreso;
import clinica.modelo.personas.pacientes.Paciente;

/** Representa la sala de espera privada, donde solo puede haber un paciente. */
public class SalaEsperaPrivada {
		
		private Paciente paciente;

		public void setPaciente(Paciente paciente) {

            this.paciente = paciente;
		}

        public Paciente getPaciente()
        {
            return this.paciente;
        }

        /** Comprueba si la sala de espera privada esta libre. */
        public boolean salaLibre ()
        {
            return this.paciente == null;
        }
}
