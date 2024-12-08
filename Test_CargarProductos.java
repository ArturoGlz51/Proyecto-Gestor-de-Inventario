import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test_CargarProductos {

    public static void main(String[] args) {
        String query = "SELECT codigo,descripcion,cantidad FROM producto";
        ProductManager.cargarProductos();
        try (Connection conexion = Conexion_DB.getConnection();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Carga de Productos Exitosa.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
