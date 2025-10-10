package clinica.modelo.facturacion;

import clinica.modelo.personas.medicos.IMedico;
import clinica.modelo.personas.medicos.Medico;

import java.text.DecimalFormat;
import java.util.ArrayList;

/** clase que contiene todo lo que se necesita para facturar, tiene runa relacion de composicion con paciente */
public class DetalleConsulta {

    /**
     * en esta lista van los medicos de la consulta actual
     */
    ArrayList<Medico> medicosConsulta = new ArrayList<>();

    public void agregaMedico(Medico m1){
        medicosConsulta.add(m1);
    }

    public ArrayList<Medico> getMedicosConsulta() { return medicosConsulta; }


    public DetalleConsulta() {
        this.medicosConsulta= new ArrayList<>();
    }



}

