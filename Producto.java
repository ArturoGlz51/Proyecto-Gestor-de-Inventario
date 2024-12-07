public class Producto {
    private String codigo;
    private String descripcion;
    private int cantidad;

    /**
     * Constructor para crear un objeto Producto con el codigo, descripcion y cantidad proporcionados.
     * @param codigo el codigo del Producto (debe tener 10 caracteres)
     * @param descripcion la descripcion del Producto
     * @param cantidad la cantidad del Producto
     */
    public Producto(String codigo, String descripcion, int cantidad) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public Producto() {

    }

    /**
     * Obtiene el codigo del Producto.
     *
     * @return el codigo del producto
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Setter para el codigo del Producto
     *
     * @param codigo el codigo a ser asignado al producto.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene la descripción del Producto.
     *
     * @return la descripción del producto
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del Producto a partir del valor proporcionado.
     *
     * @param descripcion la nueva descripción a asignar al Producto
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Retorna la cantidad del Producto.
     *
     * @return la cantidad del Producto
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del Producto con el valor proporcionado.
     *
     * @param cantidad la nueva cantidad a asignar al Producto
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Retorna un String formateado para el codigo, cantidad y descripcion.
     *
     * @return Un String formateado representando el codigo, cantidad y descripcion del producto.
     */
    @Override
    public String toString() {
        return String.format("-Codigo: %s, Cantidad: %s, Descripcion: %s", codigo, cantidad, descripcion);
    }
}
