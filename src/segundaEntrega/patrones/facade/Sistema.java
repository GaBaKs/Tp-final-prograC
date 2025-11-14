package segundaEntrega.patrones.facade;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;
import segundaEntrega.persistencia.DAOAsociadoYDTO.DAOAsociado;
import segundaEntrega.persistencia.DAOAsociadoYDTO.DTOAsociado;
import segundaEntrega.persistencia.DAOAsociadoYDTO.IDAOAsociado;

import java.util.ArrayList;

public class Sistema {
    // ... tus atributos existentes (medicos, pacientes, etc.) ...

    // 1. Nuevos atributos para la Etapa 2
    private ArrayList<Asociado> asociadosSistema;
    private IDAOAsociado asociadoDAO;
    private Ambulancia ambulancia; // Necesaria para crear Asociados

    public Sistema() {
        // ... inicialización existente ...

        this.asociadosSistema = new ArrayList<>();
        this.asociadoDAO = new DAOAsociado();
        this.ambulancia = new Ambulancia(); // Instancia única del sistema

        // CARGA INICIAL AUTOMÁTICA
        try {
            cargarAsociadosDesdeBD();
        } catch (Exception e) {
            System.err.println("Error cargando asociados: " + e.getMessage());
        }
    }

    // --- MÉTODOS DE PERSISTENCIA Y CONVERSIÓN ---

    // Convierte de DTO (BD) a Modelo (Negocio)
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

    // Convierte de Modelo (Negocio) a DTO (BD)
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
        // 1. Eliminar de memoria
        if (asociadosSistema.remove(asociado)) {
            // 2. Eliminar de BD
            asociadoDAO.eliminar(asociado.getDni()); // Asumiendo que el DAO tiene eliminar por DNI
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
