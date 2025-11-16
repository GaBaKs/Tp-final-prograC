package segundaEntrega.persistencia.basededatos;
import segundaEntrega.modelo.negocio.Ambulancia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexión a la base de datos implementando un patrón Singleton.
 * <p>
 * Esta clase asegura que solo exista una única instancia de sí misma para
 * centralizar el acceso y la obtención de la conexión
 * a la base de datos del sistema.
 * </p>
 */
public class ConexionBD {
    /** {@link ConexionBD } */
    private static ConexionBD instance;
    /** {@link Connection } */
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/grupo_4";
    private static final String USER = "progra_c";
    private static final String PASS = "progra_c";

    private ConexionBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Provee el punto de acceso global a la instancia única de la clase.
     * </p>
     * @return La única instancia de {@code ConexionBD}.
     */
    public static ConexionBD getInstance() {
        if (instance == null) {
            instance = new ConexionBD();
        }
        return instance;
    }

    /**
     * Obtiene una conexión activa a la base de datos.
     * <p>
     * Si la conexión actual es {@code null} o está cerrada, intenta
     * establecer una nueva conexión con los parámetros dados.
     * </p>
     *
     * @return Un objeto {@link Connection} activo.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASS);
        }
        return connection;
    }

    /**
     * Cierra la conexión activa a la base de datos.
     * <p>
     * Verifica si la conexión no es nula y está abierta antes de
     * intentar cerrarla.
     * </p>
     */
    public void desconectar() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
