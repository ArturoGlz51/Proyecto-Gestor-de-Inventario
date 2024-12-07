import java.sql.*;

/**
 * Clase que proporciona la conexión a la base de datos utilizando el controlador JDBC de MySQL.
 */
public class Conexion_DB {
    private static final String url = "jdbc:mysql://localhost:3306/productos";
    private static final String user = "root";
    private static final String password = "Diego273.";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
