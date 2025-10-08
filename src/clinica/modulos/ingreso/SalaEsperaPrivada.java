package clinica.modulos.ingreso;
import clinica.modelo.pacientes.Paciente;

public class SalaEsperaPrivada {
		
		private Paciente paciente=null;

		public void setPaciente(Paciente paciente) {

            this.paciente = paciente;
		}

        public Paciente getPaciente()
        {
            return this.paciente;
        }

        public boolean salaLibre ()
        {
            return this.paciente == null;
        }
}
