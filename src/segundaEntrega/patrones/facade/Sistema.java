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

/**
 * La clase Sistema actúa como el núcleo lógico de la aplicación.
 *
 * Centraliza:
 * - La carga y persistencia de datos de asociados (a través del DAO).
 * - El acceso a la instancia única de Ambulancia (patrón Singleton).
 * - La comunicación con los controladores principales de la aplicación.
 * - El manejo de las colecciones internas de asociados.
 */
public class Sistema {
    private ArrayList<Asociado> asociadosSistema; //aca estan los asociados q pide el usuario
    private IDAOAsociado asociadoDAO;
    private Ambulancia ambulancia;
    private ControladorBD controladorBD;
    private ControladorInicio  controladorInicio;
    private ControladorSimulacion controladorSimulacion;



    /**
     * Constructor principal del sistema.
     * @param controladorBD controlador del módulo base de datos
     * @param controladorInicio controlador de la pantalla de inicio
     * @param controladorSimulacion controlador del módulo de simulación
     */
    public Sistema(ControladorBD controladorBD, ControladorInicio controladorInicio, ControladorSimulacion controladorSimulacion) {
        this.asociadosSistema = new ArrayList<>();
        this.asociadoDAO = new DAOAsociado();
        this.ambulancia = Ambulancia.getInstance(); // instancia unica del sistema
        this.controladorBD = controladorBD;
        this.controladorInicio= controladorInicio;
        this.controladorSimulacion = controladorSimulacion;

        try {
            cargarAsociadosDesdeBD();
        } catch (Exception e) {
            System.err.println("Error cargando asociados: " + e.getMessage());
        }
    }


    /**
     * Carga todos los asociados almacenados en la base de datos.
     *
     * Se realiza el siguiente proceso:
     * - Se limpia la lista interna en memoria.
     * - Se obtienen los DTO desde el DAO.
     * - Se transforman los DTO en objetos Asociado mediante el ControladorBD.
     *
     * @throws Exception si ocurre un error durante la lectura o conversión de datos.
     */
    public void cargarAsociadosDesdeBD() throws Exception {
        this.asociadosSistema.clear();
        ArrayList<DTOAsociado> dtos = asociadoDAO.getListaAsociados();
        int cantsol=controladorSimulacion.getCantSolicitudes();
        for (DTOAsociado dto : dtos) {
            this.asociadosSistema.add(controladorBD.fromDTO(dto));
        }
    }

    /**
     * Guarda todos los datos de los asociados cuando el sistema se cierra.
     *
     * Convierte los asociados de memoria a DTOs y los persiste mediante el DAO.
     *
     * @throws Exception si se produce un error al guardar en la base de datos.
     */
    public void guardarDatosAlCerrar() throws Exception {
        ArrayList<DTOAsociado> dtos = new ArrayList<>();
        for (Asociado asoc : this.asociadosSistema) {
            dtos.add(controladorBD.toDTO(asoc));
        }
        asociadoDAO.guardarTodo(dtos);
    }

    // --- MÉTODOS DE GESTIÓN (ABM) PARA EL CONTROLADOR ---
    /**
     * Agrega un nuevo asociado al sistema.
     *
     * @param nuevoAsociado el asociado a agregar
     * @throws Exception si el asociado ya existe en la lista
     */
    public void agregarAsociado(Asociado nuevoAsociado) throws Exception {
        if (asociadosSistema.contains(nuevoAsociado)) { // Debes implementar equals en Asociado por DNI
            throw new Exception("El asociado ya existe");
        }

        // 1. Agregar a la lista en memoria
        asociadosSistema.add(nuevoAsociado);

        // 2. Persistir inmediatamente (Opcional, si quieres guardar en tiempo real)
        asociadoDAO.altaAsociado(controladorBD.toDTO(nuevoAsociado));
    }

    /**
     * Elimina un asociado tanto de la memoria como de la base de datos.
     *
     * @param asociado el asociado a eliminar
     * @throws Exception si el asociado no existe en el sistema
     */
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

    /**
     * Obtiene la lista completa de asociados cargados en memoria.
     *
     * @return lista de asociados
     */
    public ArrayList<Asociado> getAsociadosSistema() {
        return asociadosSistema;
    }

    /**
     * Devuelve la instancia única de la ambulancia del sistema.
     *
     * @return instancia de Ambulancia
     */
    public Ambulancia getAmbulancia() {
        return ambulancia;
    }
}
