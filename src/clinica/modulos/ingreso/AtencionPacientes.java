package clinica.modulos.ingreso;

import clinica.modelo.pacientes.Paciente;

public class AtencionPacientes {
	private int cont=0;
	SalaEsperaPrivada salaesperaprivada;
	Patio patio;
	public int numeroOrden() 
	{
		cont++;
	    return cont;
	}
	
	public void ingresaPaciente(Paciente p1)
	{
			p1.setnumorden(numeroOrden());
			ingresaCola(p1);
	}



    public void ingresaCola(Paciente p1) {
		if (salaesperaprivada.salaLibre())
			salaesperaprivada.setPaciente(p1);

		else {

            Paciente actual = salaesperaprivada.getPaciente();
            Paciente ganador = p1.decidirLugar(actual);
            Paciente perdedor = (ganador == p1) ? actual : p1;

            if (ganador!=actual)
                salaesperaprivada.setPaciente(ganador);
            patio.appendPaciente(perdedor);

		}
	}
	public AtencionPacientes() {
		
		
	}
	
	

}
