package segundaEntrega.modelo;

import excepciones.DuplicadoException;
import segundaEntrega.modelo.negocio.Ambulancia;
import segundaEntrega.modelo.negocio.Asociado;
import segundaEntrega.persistencia.DAOAsociadoYDTO.DAOAsociado;
import segundaEntrega.persistencia.DAOAsociadoYDTO.DTOAsociado;
import segundaEntrega.persistencia.DAOAsociadoYDTO.IDAOAsociado;

import java.util.ArrayList;

/**
 * Representa el Modelo de Negocio que centraliza la lógica de gestión de Asociados
 * y la persistencia de datos.
 * <p>
 * Esta clase actúa como el único punto de contacto entre los Controladores, el DTO y el DAO,
 * manteniendo una lista de trabajo en memoria {@code listaAsociadosMemoria} sincronizada
 * con la Base de Datos para operaciones de validación rápidas.
 */

    public class ModeloBD
    {
        private IDAOAsociado dao;
        private ArrayList<Asociado> listaAsociadosMemoria; //aca estan todos los asociados
        private Ambulancia ambulanciaCompartida; // Para crear nuevos Asociados

        public ModeloBD(Ambulancia ambulancia) {
            this.dao = new DAOAsociado();
            this.ambulanciaCompartida = ambulancia;
            this.listaAsociadosMemoria = new ArrayList<>();

            // Cargar datos al iniciar
            try {
                this.cargarDatosDesdeBD();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // --- Lógica de Negocio (habla con DAO) ---

        /**
         * Agrega un nuevo asociado al sistema, persistiendo el cambio en la Base de Datos
         * y actualizando la lista en memoria.
         * <p>
         * Este metodo incluye validación de DNI y chequeo de duplicados en memoria
         * antes de intentar la persistencia.
         * </p>
         */
        public void agregarAsociado(DTOAsociado dto) throws Exception {
            // Lógica de negocio (Validaciones)
            String dni = dto.getDni();
            if (dni == null || dni.trim().isEmpty()) {
                throw new Exception("El DNI no puede ser nulo o vacío");
            }

            // --- INICIO DE LA MODIFICACIÓN ---

            // 1. Creamos un objeto temporal para la comprobación
            // (Esto depende de que fromDTO() esté disponible en esta clase)
            Asociado temporal = fromDTO(dto);

            // 2. Comprobar duplicados en la lista de memoria
            // (Esto requiere que tengas .equals() implementado en Persona/Asociado)
            if (listaAsociadosMemoria.contains(temporal)) {
                // Usamos la excepción específica de tu proyecto
                throw new DuplicadoException("Ya existe un asociado con el DNI: " + dni);
            }

            // --- FIN DE LA MODIFICACIÓN ---

            // 3. Persistir en BD (si no hubo excepción)
            dao.altaAsociado(dto);

            // 4. Actualizar lista en memoria
            listaAsociadosMemoria.add(temporal);
        }

        public void eliminarAsociado(DTOAsociado dto) throws Exception {
            // 1. Eliminar de BD
            dao.bajaAsociado(dto);

            // 2. Actualizar lista en memoria (requiere .equals() en Asociado)
            listaAsociadosMemoria.remove(fromDTO(dto));
        }

        public void inicializarTablas() throws Exception {
            dao.inicializarDB();
            listaAsociadosMemoria.clear();
        }

        public void cargarDatosDesdeBD() throws Exception {
            listaAsociadosMemoria.clear();
            ArrayList<DTOAsociado> dtos = dao.getListaAsociados();
            for (DTOAsociado dto : dtos) {
                listaAsociadosMemoria.add(fromDTO(dto));
            }
        }

        public ArrayList<Asociado> getAsociados() {
            return this.listaAsociadosMemoria;
        }

        // --- Métodos de Mapeo (DTO <-> Modelo de Negocio) ---
        // pasa de dto a objeto asociado
        public Asociado fromDTO(DTOAsociado dto) {
            return new Asociado(
                    dto.getDni(),
                    dto.getNombreApellido(),
                    dto.getDomicilio(),
                    dto.getCiudad(),
                    dto.getTelefono(),
                    dto.getNumSolicitudes(),
                    this.getAmbulanciaCompartida() //Inyectamos la ambulancia del sistema
            );
        }

        // al reves
        public DTOAsociado toDTO(Asociado asociado) {
            return new DTOAsociado(
                    asociado.getDni(),
                    asociado.getN_A(),
                    asociado.getDomicilio(),
                    asociado.getCiudad(),
                    asociado.getTelefono(),
                    asociado.getNumasolicitudes()
            );
        }

        public Ambulancia getAmbulanciaCompartida() {
            return ambulanciaCompartida;
        }
    }
