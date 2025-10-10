package clinica.modelo.personas.medicos;

public class MedicoFactory {
    public MedicoFactory() {}

    /**clase que aplica el patron factory sobre los medicos*/
    public Medico creaMedico(String tipoMedico,String dni,String N_A,String domicilio,String ciudad,int nMatricula,String telefono)
    {
        if(tipoMedico == null) { return null; }

        if(tipoMedico.equalsIgnoreCase("cirugia") ) { return new Cirugia(dni,N_A,domicilio,ciudad,nMatricula,telefono);}

        if(tipoMedico.equalsIgnoreCase("clinico")) { return new Clinica(dni,N_A,domicilio,ciudad,nMatricula,telefono);}

        if(tipoMedico.equalsIgnoreCase("pediatra")) { return new Pediatra(dni,N_A,domicilio,ciudad,nMatricula,telefono);}

        return null;  // devuelvo nulo si ingreso una especialidad para el medico que no esta existe


    }
}
