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
    }

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

    public static void mostrarCodigos(){
        System.out.println("Codigos: ");
        for (Producto producto : productos) {
            System.out.println(producto.getCodigo());
        }
    }

    public static void mostrarProductos(){
        System.out.println("Productos: ");
        for (Producto producto : productos) {
            System.out.println(producto.toString());
        }
    }

    public static void cargarProductos(){
        productos = ProductManager.cargarProductos();
    }
}