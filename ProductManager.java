import java.sql.*;
import java.util.*;

public class ProductManager {

    /**
     * Cargar la lista de productos desde la base de datos, obteniendo el código, descripción y cantidad de cada producto.
     * Los productos se almacenan en una lista y se devuelve esta lista una vez cargados los datos.
     *
     * @return lista de productos cargada desde la base de datos
     */
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

    /**
     * Crea un objeto Producto a partir de un ResultSet que contiene información de codigo, descripción y cantidad.
     *
     * @param rs el ResultSet que contiene los datos del producto
     * @return el objeto Producto creado con los datos del ResultSet
     * @throws SQLException si ocurre un error al acceder a los datos del ResultSet
     */
    private static Producto crearProductoDesdeRs(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setCodigo(rs.getString("codigo"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setCantidad(rs.getInt("cantidad"));
        return producto;
    }

    /**
     * Elimina un producto de la base de datos según el código proporcionado.
     *
     * @param producto el Producto a ser eliminado de la base de datos
     */
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

    /**
     * Inserta un nuevo producto en la base de datos.
     *
     * @param producto el objeto Producto a ser insertado en la base de datos
     */
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

    /**
     * Actualiza la cantidad de un producto en el inventario mediante la base de datos.
     *
     * @param producto el Producto cuya cantidad en el inventario se va a actualizar
     * @param cantidad la nueva cantidad del producto en el inventario
     */
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
