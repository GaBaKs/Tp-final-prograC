package segundaEntrega.persistencia.DAOAsociadoYDTO;

import segundaEntrega.persistencia.basededatos.ConexionBD;

import java.sql.*;
import java.util.ArrayList;

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

    @Override
    public void inicializarDB() throws Exception {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS asociados (...)";
    }
}
