import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static List<Producto> productos = new ArrayList<Producto>();

    public static void main(String[] args) {
        int opcion;
        String seguir = "";
        cargarProductos();
        do {
            System.out.println("Bienvenido a tu Sistema de Gestor de Inventario");
            System.out.println(" ");
            System.out.println("Elija una opcion a realizar: ");
            System.out.println("1.- Agregar Producto");
            System.out.println("2.- Eliminar Producto");
            System.out.println("3.- Ingresar Producto");
            System.out.println("4.- Mostrar Producto");
            System.out.println("5.- Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    eliminarProducto();
                    break;
                case 3:
                    ingresarInventario();
                    break;
                case 4:
                    mostrarProductos();
                    break;
                case 5:
                    System.out.println("Cerrando Sistema...");
                    return;
            }
            System.out.println(" ");
            System.out.println("Desea seguir trabajando? (S/N)");
            seguir = sc.nextLine();
        } while (seguir.equals("S"));
    }


    /**
     * Solicita al usuario ingresar información de un nuevo producto, valida el código del producto,
     * la longitud del código, y si el código ya existe en la lista de productos. Una vez validado el
     * código, pide ingresar la descripción y cantidad del producto. Si la cantidad es menor a 0, muestra
     * un mensaje de error. Finalmente, crea un nuevo Producto con la información ingresada y lo agrega
     * a la lista de productos. Además, invoca el método en ProductManager para agregar el producto
     * a la base de datos.
     */
    public static void agregarProducto() {
        String codigo;
        boolean codigoRepetido;
        do {
            codigoRepetido = false;
            System.out.print("Ingrese el Codigo del producto: ");
            codigo = sc.nextLine();
            if (codigo.length() != 10) {
                System.out.println("El codigo debe ser de 10 caracteres.");
            }
            for (Producto producto : productos) {
                if (producto.getCodigo().equals(codigo)) {
                    codigoRepetido = true;
                }
            }
            if (codigoRepetido) {
                System.out.println("El codigo del producto ya existe.");
            }
        } while (codigo.length() != 10 || codigoRepetido);
        System.out.print("Ingrese la Descripcion del producto: ");
        String descripcion = sc.nextLine();
        System.out.print("Ingrese la cantidad del producto: ");
        int cantidad = sc.nextInt();
        sc.nextLine();
        if (cantidad < 0){
            System.out.println("Error, no puede haber una cantidad menor a 0.");
            return;
        }
        Producto tempProducto = new Producto(codigo, descripcion, cantidad);
        productos.add(tempProducto);
        ProductManager.agregarProducto(tempProducto);
        System.out.println("Producto agregado.");
    }

    /**
     * Método que muestra los códigos de los productos disponibles, solicita al usuario ingresar un código de producto
     * y procede a eliminar dicho producto de la lista. Si el código ingresado coincide con un producto existente,
     * se elimina tanto de la lista de productos como de la base de datos. En caso de éxito, se muestra un mensaje
     * indicando que el producto ha sido eliminado.
     */
    public static void eliminarProducto() {
        mostrarCodigos();
        System.out.print("Ingrese el codigo del producto: ");
        String codigo = sc.nextLine();
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                productos.remove(producto);
                ProductManager.borrarProducto(producto);
                System.out.println("Producto borrado.");
                return;
            }
        }
    }

    /**
     * Metodo que muestra los codigos de los productos disponibles y permite ingresar
     * la cantidad de un producto en inventario. Se solicita al usuario ingresar el codigo
     * del producto a ingresar, valida si el codigo existe en la lista de productos
     * y en caso afirmativo, permite ingresar la cantidad a sumar al inventario.
     * Si la cantidad ingresada es negativa, se muestra un mensaje de error.
     */
    public static void ingresarInventario(){
        mostrarCodigos();
        System.out.println("Ingrese el codigo del producto a ingresar: ");
        String codigo = sc.nextLine();
        for (Producto producto : productos) {
            if (!producto.getCodigo().equals(codigo)) {
                System.out.println("Código no encontrado.");
                return;
            }
            System.out.println("Ingrese la cantidad a ingresar: ");
            int cantidad = sc.nextInt();
            sc.nextLine();
            if (cantidad < 0){
                System.out.println("Error, no puede haber una cantidad menor a 0.");
                return;
            }
            int newCantidad = producto.getCantidad() + cantidad;
            producto.setCantidad(newCantidad);
            ProductManager.ingresarInventario(producto, newCantidad);
            System.out.println("Inventario correctamente agregado.");
        }
    }

    /**
     * Muestra los códigos de los productos disponibles en la lista de productos.
     * Recorre la lista de productos e imprime en consola cada código de producto.
     */
    public static void mostrarCodigos(){
        System.out.println("Codigos: ");
        for (Producto producto : productos) {
            System.out.println(producto.getCodigo());
        }
    }

    /**
     * Muestra en consola la lista de productos disponibles, recorriendo la lista e imprimiendo
     * la representación en texto de cada producto.
     */
    public static void mostrarProductos(){
        System.out.println("Productos: ");
        for (Producto producto : productos) {
            System.out.println(producto.toString());
        }
    }

    /**
     * Carga la lista de productos desde la base de datos, obteniendo el código, descripción
     * y cantidad de cada producto. La información cargada se asigna a la lista de productos.
     */
    public static void cargarProductos(){
        productos = ProductManager.cargarProductos();
    }
}
