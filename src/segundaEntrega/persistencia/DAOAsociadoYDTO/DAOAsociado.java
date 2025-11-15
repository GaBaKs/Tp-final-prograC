package segundaEntrega.persistencia.DAOAsociadoYDTO;

import segundaEntrega.persistencia.basededatos.ConexionBD;

import java.sql.*;
import java.util.ArrayList;


/**
 * Implementación concreta del Data Access Object (DAO) para la entidad Asociado.
 * <p>
 * Esta clase implementa la interfaz {@link IDAOAsociado} y centraliza todas las
 * operaciones de persistencia para los asociados.
 * </p>
 */
public class DAOAsociado implements IDAOAsociado
{
    @Override
    public void update(DTOAsociado dto) throws Exception
    {
        String sql = "UPDATE asociados SET nombre=?, domicilio=?, ciudad=?, telefono=?, numSolicitudes=? WHERE dni=?";
        Connection con = null;
        PreparedStatement ps = null;

        try
        {
            con = ConexionBD.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, dto.getNombreApellido());
            ps.setString(2, dto.getDomicilio());
            ps.setString(3, dto.getCiudad());
            ps.setString(4, dto.getTelefono());
            ps.setInt(5, dto.getNumSolicitudes());
            ps.setString(6, dto.getDni());
            ps.executeUpdate();
        } finally
        {
            if (ps != null) ps.close();
        }

    }


    @Override
    public ArrayList<DTOAsociado> getListaAsociados() throws Exception {
        ArrayList<DTOAsociado> lista = new ArrayList<>();
        String sql = "SELECT * FROM asociados";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = ConexionBD.getInstance().getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql); //


            while (rs.next()) {
                DTOAsociado dto = new DTOAsociado();
                // Llenado del DTO con datos del ResultSet
                dto.setDni(rs.getString("dni"));
                dto.setNombreApellido(rs.getString("nombre"));
                dto.setDomicilio(rs.getString("domicilio"));
                dto.setCiudad(rs.getString("ciudad"));
                dto.setTelefono(rs.getString("telefono"));
                dto.setNumSolicitudes(rs.getInt("numSolicitudes"));

                lista.add(dto);
            }
        } finally {
            // Cierre de recursos en orden inverso
            if (rs != null) rs.close();
            if (st != null) st.close();
        }
        return lista;
    }

    /**
     * Elimina un asociado de la base de datos utilizando su DNI como clave.
     *
     * @param dto El DTO {@link DTOAsociado} que contiene el DNI del asociado a eliminar.
     * @throws Exception Si ocurre un error de SQL.
     */
    @Override
    public void bajaAsociado(DTOAsociado dto) throws Exception
    {
        String sql = "DELETE FROM asociados WHERE dni = ?";
        Connection con = null;
        PreparedStatement ps = null;

        try
        {
            con = ConexionBD.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, dto.getDni()); // Usamos el ID del DTO
            ps.executeUpdate();
        } finally
        {
            if (ps != null) ps.close();
        }
    }

    /**
     * Inserta un nuevo asociado en la base de datos.
     *
     * @param dto El DTO {@link DTOAsociado} con todos los datos del nuevo asociado.
     * @throws Exception Si ocurre un error de SQL.
     */
    @Override
    public void altaAsociado(DTOAsociado dto) throws Exception {
        String sql = "INSERT INTO asociados (dni, nombre, domicilio, ciudad, telefono, numSolicitudes) VALUES (?, ?, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConexionBD.getInstance().getConnection();
            ps = con.prepareStatement(sql);

            // Mapeo de atributos del DTO a la consulta SQL
            ps.setString(1, dto.getDni());
            ps.setString(2, dto.getNombreApellido());
            ps.setString(3, dto.getDomicilio());
            ps.setString(4, dto.getCiudad());
            ps.setString(5, dto.getTelefono());
            ps.setInt(6, dto.getNumSolicitudes());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al insertar asociado: " + e.getMessage());
        } finally {
            if (ps != null) ps.close();
        }
    }

    /**
     * Reinicia la tabla de asociados en la base de datos.
     * </p>
     * @throws Exception Si ocurre un error de SQL.
     */
    @Override
    public void inicializarDB() throws Exception {
        String dropSql = "DROP TABLE IF EXISTS asociados";

        String createSql = "CREATE TABLE asociados (" +
                "dni VARCHAR(20) PRIMARY KEY, " +
                "nombre VARCHAR(100) NOT NULL, " +
                "domicilio VARCHAR(100), " +
                "ciudad VARCHAR(100), " +
                "telefono VARCHAR(50), " +
                "numSolicitudes INT NOT NULL" +
                ")";

        Connection con = null;
        Statement st = null;

        try {
            con = ConexionBD.getInstance().getConnection();
            st = con.createStatement();

            // Eliminar la tabla (como pide el enunciado)
            st.execute(dropSql);

            // Crear la tabla nueva
            st.execute(createSql);

        } catch (SQLException e) {
            throw new Exception("Error al inicializar la base de datos: " + e.getMessage());
        } finally {
            if (st != null) st.close();
            // No cerramos la conexión, la gestiona el Singleton
        }
    }

    /**
     * Reemplaza todos los datos de la tabla 'asociados' con los datos de la lista proporcionada.
     * </p>
     *
     * @param lista La lista de {@link DTOAsociado} que representa el estado final de la tabla.
     * @throws Exception Si ocurre un error de SQL.
     */
    @Override
    public void guardarTodo(ArrayList<DTOAsociado> lista) throws Exception {
        Connection con = null;
        PreparedStatement psDelete = null;
        PreparedStatement psInsert = null;

        try {
            con = ConexionBD.getInstance().getConnection();

            // desactivamos el guardado automático
            con.setAutoCommit(false);

            // borrar todos los datos existentes en la tabla
            String sqlDelete = "DELETE FROM asociados";
            psDelete = con.prepareStatement(sqlDelete);
            psDelete.executeUpdate();

            // insertar los datos de la lista (uno por uno)
            String sqlInsert = "INSERT INTO asociados (dni, nombre, domicilio, ciudad, telefono, numSolicitudes) VALUES (?, ?, ?, ?, ?, ?)";
            psInsert = con.prepareStatement(sqlInsert);

            for (DTOAsociado dto : lista) {
                psInsert.setString(1, dto.getDni());
                psInsert.setString(2, dto.getNombreApellido());
                psInsert.setString(3, dto.getDomicilio());
                psInsert.setString(4, dto.getCiudad());
                psInsert.setString(5, dto.getTelefono());
                psInsert.setInt(6, dto.getNumSolicitudes());

                // ejecutamos la inserción de este asociado
                psInsert.executeUpdate();
            }

            // confirmamos los cambios
            con.commit();

        } catch (SQLException e) {
            // si hubo error deshace toodo p no perder datos
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw new Exception("Error al guardar todo: " + e.getMessage());
        } finally {
            // Restauramos el autoCommit por si se reutiliza la conexión ?? ni puta idea
            if (con != null) con.setAutoCommit(true);

            if (psDelete != null) psDelete.close();
            if (psInsert != null) psInsert.close();
        }
    }
}
