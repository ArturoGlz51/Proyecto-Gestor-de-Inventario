import java.sql.*;
public class Test_ConexionDB {
    private static final String url = "jdbc:mysql://localhost:3306/productos";
    private static final String user = "root";
    private static final String password = "Diego273.";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al conectarse a la base de datos: " + e.getMessage());
        }
    }
}
