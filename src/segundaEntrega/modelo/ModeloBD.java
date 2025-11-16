package segundaEntrega.modelo;

import excepciones.DuplicadoException;
import segundaEntrega.controlador.ControladorBD;
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
        /** {@link IDAOAsociado } */
        private IDAOAsociado dao;
        /** arraylist de {@link Asociado } */
        private ArrayList<Asociado> listaAsociadosMemoria; //aca estan todos los asociados
        /** {@link Ambulancia } */
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
            String dni = dto.getDni();
            if (dni == null || dni.trim().isEmpty()) {
                throw new Exception("El DNI no puede ser nulo o vacío");
            }
            // Objeto temporal para la comprobación
            Asociado temporal = fromDTO(dto);

            // Comprobar duplicados en la lista de memoria
            // (Para esto tenemos equals implementado en persona)
            if (listaAsociadosMemoria.contains(temporal)) {
                throw new DuplicadoException("Ya existe un asociado con el DNI: " + dni);
            }
            // Persistir en BD (si no hubo excepción)
            dao.altaAsociado(dto);

            // Actualizar lista en memoria
            listaAsociadosMemoria.add(temporal);
        }

        /**
         * Elimina un asociado del sistema, borrándolo de la Base de Datos y
         * actualizando la lista en memoria de forma atómica.
         * @param dto El objeto {@code DTOAsociado} con la clave del asociado a eliminar.
         * @throws Exception Si ocurre un error al eliminar en el DAO.
         */
        public void eliminarAsociado(DTOAsociado dto) throws Exception {
            // Eliminar de BD
            dao.bajaAsociado(dto);

            // Actualizar lista en memoria
            listaAsociadosMemoria.remove(fromDTO(dto));
        }

        /**
         * Inicializa o vacía las tablas de la Base de Datos y limpia la lista en memoria.
         * @throws Exception Si ocurre un error al inicializar la base de datos.
         */
        public void inicializarTablas() throws Exception {
            dao.inicializarDB();
            listaAsociadosMemoria.clear();
        }

        /**
         * Carga todos los asociados desde la Base de Datos, mapea sus datos
         * y reemplaza el contenido de la lista en memoria.
         * @throws Exception Si el DAO falla al obtener la lista de asociados.
         */
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
        /**
         * Mapea un objeto de transferencia de datos (DTO) a un objeto del modelo de negocio (Asociado).
         * @param dto El {@code DTOAsociado} de origen.
         * @return El objeto {@code Asociado} creado.
         */
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

        /**
         * Mapea un objeto del modelo de negocio (Asociado) a un objeto de transferencia de datos (DTO).
         * @param asociado El objeto {@code Asociado} de origen.
         * @return El {@code DTOAsociado} creado.
         */
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
