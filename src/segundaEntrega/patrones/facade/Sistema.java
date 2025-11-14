package segundaEntrega.patrones.facade;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;
import segundaEntrega.persistencia.DAOAsociadoYDTO.DAOAsociado;
import segundaEntrega.persistencia.DAOAsociadoYDTO.DTOAsociado;
import segundaEntrega.persistencia.DAOAsociadoYDTO.IDAOAsociado;

import java.util.ArrayList;

public class Sistema {
    private ArrayList<Asociado> asociadosSistema;
    private IDAOAsociado asociadoDAO;
    private Ambulancia ambulancia;

    public Sistema() {
        this.asociadosSistema = new ArrayList<>();
        this.asociadoDAO = new DAOAsociado();
        this.ambulancia = new Ambulancia(); // Instancia única del sistema

        try {
            cargarAsociadosDesdeBD();
        } catch (Exception e) {
            System.err.println("Error cargando asociados: " + e.getMessage());
        }
    }

    // pasa de dto a objeto asociado
    private Asociado fromDTO(DTOAsociado dto) {
        return new Asociado(
                dto.getDni(),
                dto.getNombreApellido(),
                dto.getDomicilio(),
                dto.getCiudad(),
                dto.getTelefono(),
                dto.getNumSolicitudes(),
                this.ambulancia // Inyectamos la ambulancia del sistema
        );
    }

    // al reves
    private DTOAsociado toDTO(Asociado asociado) {
        return new DTOAsociado(
                asociado.getDni(),
                asociado.getN_A(),
                asociado.getDomicilio(),
                asociado.getCiudad(),
                asociado.getTelefono(),
                asociado.getNumasolicitudes()
        );
    }

    public void cargarAsociadosDesdeBD() throws Exception {
        this.asociadosSistema.clear();
        ArrayList<DTOAsociado> dtos = asociadoDAO.getListaAsociados();
        for (DTOAsociado dto : dtos) {
            this.asociadosSistema.add(fromDTO(dto));
        }
    }

    public void guardarDatosAlCerrar() throws Exception {
        ArrayList<DTOAsociado> dtos = new ArrayList<>();
        for (Asociado asoc : this.asociadosSistema) {
            dtos.add(toDTO(asoc));
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
        asociadoDAO.altaAsociado(toDTO(nuevoAsociado));
    }

    public void eliminarAsociado(Asociado asociado) throws Exception {
        // elimino d memoria
        if (asociadosSistema.remove(asociado)) {
            DTOAsociado dto = this.toDTO(asociado); //paso de objeto a dto
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
