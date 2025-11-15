package segundaEntrega.patrones.facade;
import segundaEntrega.controlador.ControladorBD;
import segundaEntrega.controlador.ControladorInicio;
import segundaEntrega.controlador.ControladorSimulacion;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;
import segundaEntrega.persistencia.DAOAsociadoYDTO.DAOAsociado;
import segundaEntrega.persistencia.DAOAsociadoYDTO.DTOAsociado;
import segundaEntrega.persistencia.DAOAsociadoYDTO.IDAOAsociado;

import java.util.ArrayList;

public class Sistema {
    private ArrayList<Asociado> asociadosSistema; //aca estan los asociados q pide el usuario
    private IDAOAsociado asociadoDAO;
    private Ambulancia ambulancia;
    private ControladorBD controladorBD;
    private ControladorInicio  controladorInicio;
    private ControladorSimulacion controladorSimulacion;




    public Sistema(ControladorBD controladorBD, ControladorInicio controladorInicio, ControladorSimulacion controladorSimulacion) {
        this.asociadosSistema = new ArrayList<>();
        this.asociadoDAO = new DAOAsociado();
        this.ambulancia = new Ambulancia(); // instancia unica del sistema
        this.controladorBD = controladorBD;
        this.controladorInicio= controladorInicio;
        this.controladorSimulacion = controladorSimulacion;

        try {
            cargarAsociadosDesdeBD();
        } catch (Exception e) {
            System.err.println("Error cargando asociados: " + e.getMessage());
        }
    }


    public void cargarAsociadosDesdeBD() throws Exception {
        this.asociadosSistema.clear();
        ArrayList<DTOAsociado> dtos = asociadoDAO.getListaAsociados();
        int cantsol=controladorSimulacion.getCantSolicitudes();
        for (DTOAsociado dto : dtos) {
            this.asociadosSistema.add(controladorBD.fromDTO(dto));
        }
    }

    public void guardarDatosAlCerrar() throws Exception {
        ArrayList<DTOAsociado> dtos = new ArrayList<>();
        for (Asociado asoc : this.asociadosSistema) {
            dtos.add(controladorBD.toDTO(asoc));
        }
        asociadoDAO.guardarTodo(dtos);
    }

    // --- MÉTODOS DE GESTIÓN (ABM) PARA EL CONTROLADOR ---

    public void agregarAsociado(Asociado nuevoAsociado) throws Exception {
        if (asociadosSistema.contains(nuevoAsociado)) { // Debes implementar equals en Asociado por DNI
            throw new Exception("El asociado ya existe");
        }

        // 1. Agregar a la lista en memoria
        asociadosSistema.add(nuevoAsociado);

        // 2. Persistir inmediatamente (Opcional, si quieres guardar en tiempo real)
        asociadoDAO.altaAsociado(controladorBD.toDTO(nuevoAsociado));
    }

    public void eliminarAsociado(Asociado asociado) throws Exception {
        // elimino d memoria
        if (asociadosSistema.remove(asociado)) {
            DTOAsociado dto = controladorBD.toDTO(asociado); //paso de objeto a dto
            // elimino de la base de datos
            asociadoDAO.bajaAsociado(dto); // Asumiendo que el DAO tiene eliminar por DNI
        } else {
            throw new Exception("Asociado no encontrado");
        }
    }

    public ArrayList<Asociado> getAsociadosSistema() {
        return asociadosSistema;
    }

    public Ambulancia getAmbulancia() {
        return ambulancia;
    }
}
