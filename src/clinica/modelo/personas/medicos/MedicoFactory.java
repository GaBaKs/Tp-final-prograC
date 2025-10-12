package clinica.modelo.personas.medicos;

import excepciones.MedicoInvalidoException;

/** Clase que aplica el patron factory para los medicos.*/
public class MedicoFactory {
    public MedicoFactory() {}

    /**
     * Genera un nuevo medico, en el caso que la especialidad introducida no exista se lanza una excepcion.
     * @param tipoMedico
     * @param dni
     * @param N_A
     * @param domicilio
     * @param ciudad
     * @param nMatricula
     * @param telefono
     * @return Medico
     * @throws MedicoInvalidoException
     */
    public Medico creaMedico(String tipoMedico,String dni,String N_A,String domicilio,String ciudad,int nMatricula,String telefono) throws MedicoInvalidoException
    {
        if(tipoMedico.equalsIgnoreCase("cirugia") )
        {
            return new Cirugia(dni,N_A,domicilio,ciudad,nMatricula,telefono);
        }

        if(tipoMedico.equalsIgnoreCase("clinico"))
        {
            return new Clinica(dni,N_A,domicilio,ciudad,nMatricula,telefono);
        }
        if(tipoMedico.equalsIgnoreCase("pediatra"))
        {
            return new Pediatra(dni,N_A,domicilio,ciudad,nMatricula,telefono);
        }
        throw new MedicoInvalidoException("El tipo de medico ingresado es invalido");
    }

}
