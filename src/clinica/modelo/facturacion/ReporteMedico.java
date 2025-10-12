package clinica.modelo.facturacion;

import clinica.modelo.personas.medicos.IMedico;

import java.time.LocalDate;
import java.util.ArrayList;

/**Clase que representa los reportes emitidos por los medicos una ve realizada la consulta.*/
public class ReporteMedico {
    /**Historial de facturas emitidas por la clinica.*/
    ArrayList<Factura> facturas;


    /**
     * Genera el reporte medico correspondiente a la consulta realizada.
     * @param facturas
     * @param medico
     * @param desde
     * @param hasta
     * @return toString
     */
    public String generaReporteMedico(ArrayList<Factura> facturas,IMedico medico, LocalDate desde,LocalDate hasta){
            int i=0;
            double acum=0;
            StringBuilder sb = new StringBuilder();
            sb.append("REPORTE MEDICO\n");
            sb.append("================================================\n");
            sb.append("Medico: "+medico.getNombre()+"\n");
             while(i<facturas.size() && facturas.get(i).getFechaEgreso().isBefore(desde))       // llega hasta la 1ra factura con fecha de egreso mas grande que desde
                i++;
             while (i<facturas.size() &&  facturas.get(i).getFechaEgreso().isBefore(hasta)) {
                 ArrayList<IMedico> medcon=facturas.get(i).getMedicosConsultados();
                 for (IMedico m1 : medcon){
                     if (m1==medico){
                         sb.append("Fecha: ").append(facturas.get(i).getFechaEgreso()).append("\n");
                         sb.append("Paciente: ").append(facturas.get(i).getPaciente().getN_A());
                         double valorHonorario=m1.honorario()*facturas.get(i).getIncrementoHonorario();
                         sb.append("\t Valor de la consulta: ").append(valorHonorario).append("\n");
                         acum+=valorHonorario;
                     }
                 }

                 i++;
             }
             sb.append("\t \t Total: ").append(acum).append("\n");
        sb.append("================================================\n");

            return sb.toString();
    }

}
