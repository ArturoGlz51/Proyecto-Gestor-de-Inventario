import java.sql.*;
import java.util.*;
public class ProductManager {
    public static List<Producto> cargarProductos() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT codigo,descripcion,cantidad FROM producto";

        try (Connection conexion = Conexion_DB.getConnection();
        Statement stmt = conexion.createStatement();
        ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()){
                Producto producto = crearProductoDesdeRs(rs);
                if (producto != null){
                    productos.add(producto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    private static Producto crearProductoDesdeRs(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setCodigo(rs.getString("codigo"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setCantidad(rs.getInt("cantidad"));
        return producto;
    }

    public static void borrarProducto(Producto producto) {
        String query = "DELETE FROM producto WHERE codigo = '" + producto.getCodigo() + "'";
        try (Connection conexion = Conexion_DB.getConnection();
             Statement stmt = conexion.createStatement();
            ){
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void agregarProducto(Producto producto) {
        String query = "INSERT INTO producto (codigo, descripcion, cantidad) VALUES (?,?,?)";

        try (Connection conexion = Conexion_DB.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(query)) {

            // Establecer los valores de los parámetros en la consulta
            stmt.setString(1, producto.getCodigo());  // Suponiendo que 'codigo' es un String
            stmt.setString(2, producto.getDescripcion());  // Suponiendo que 'descripcion' es un String
            stmt.setInt(3, producto.getCantidad());  // Suponiendo que 'cantidad' es un int
            // Ejecutar la consulta de inserción
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void ingresarInventario(Producto producto, int cantidad) {
        String query = "UPDATE producto SET cantidad = ? WHERE codigo = " + producto.getCodigo() + "";

        try (Connection conexion = Conexion_DB.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, producto.getCantidad());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
