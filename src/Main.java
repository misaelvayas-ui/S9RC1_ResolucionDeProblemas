import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventario inventario = new Inventario(10000.0, 200.0);

        System.out.println("SISTEMA DE GESTION DE INVENTARIOS");
        System.out.println("Bienvenido\n");

        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("MENU PRINCIPAL");
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar producto");
            System.out.println("4. Modificar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Gestionar stock");
            System.out.println("7. Ver reportes");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> agregarProducto(scanner, inventario);
                case 2 -> listarProductos(inventario);
                case 3 -> buscarProducto(scanner, inventario);
                case 4 -> modificarProducto(scanner, inventario);
                case 5 -> eliminarProducto(scanner, inventario);
                case 6 -> gestionarStock(scanner, inventario);
                case 7 -> verReportes(inventario);
                case 8 -> ejecutando = false;
                default -> System.out.println("Opcion no valida");
            }
            System.out.println();
        }

        System.out.println("Sistema terminado");
        scanner.close();
    }

    private static void agregarProducto(Scanner scanner, Inventario inventario) {
        System.out.println("\nAGREGAR NUEVO PRODUCTO");

        System.out.print("Tipo (1-Perecedero, 2-No perecedero): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("ID: ");
        int id = scanner.nextInt();

        System.out.print("Precio: ");
        double precio = scanner.nextDouble();

        System.out.print("Cantidad en stock: ");
        int cantidad = scanner.nextInt();

        System.out.print("Stock minimo: ");
        int stockMin = scanner.nextInt();

        System.out.print("Stock maximo: ");
        int stockMax = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            System.out.print("Fecha de expiracion (Año-Mes-Dia): ");
            LocalDate fechaExpiracion = LocalDate.parse(scanner.nextLine());

            ProductoPerecedero producto = new ProductoPerecedero(
                    nombre, id, precio, cantidad, stockMin, stockMax, fechaExpiracion
            );

            if (inventario.agregarProducto(producto)) {
                System.out.println("Producto perecedero agregado exitosamente");
            } else {
                System.out.println("Error: No se pudo agregar el producto");
            }

        } else {
            System.out.print("Tipo de producto: ");
            String tipoProducto = scanner.nextLine();

            System.out.print("Meses de garantia: ");
            int garantia = scanner.nextInt();
            scanner.nextLine();

            ProductoNoPerecedero producto = new ProductoNoPerecedero(
                    nombre, id, precio, cantidad, stockMin, stockMax, tipoProducto, garantia
            );

            if (inventario.agregarProducto(producto)) {
                System.out.println("Producto no perecedero agregado exitosamente");
            } else {
                System.out.println("Error: No se pudo agregar el producto");
            }
        }
    }

    private static void listarProductos(Inventario inventario) {
        System.out.println("\nLISTA DE PRODUCTOS");
        if (inventario.totalProductos() == 0) {
            System.out.println("No hay productos en el inventario");
        } else {
            inventario.listarProductos();
        }
    }

    private static void buscarProducto(Scanner scanner, Inventario inventario) {
        System.out.println("\nBUSCAR PRODUCTO");
        System.out.print("Ingrese el ID del producto: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Producto producto = inventario.buscarProducto(id);
        if (producto != null) {
            System.out.println("Producto encontrado:");
            System.out.println(producto);

            if (producto instanceof ProductoPerecedero) {
                ProductoPerecedero pp = (ProductoPerecedero) producto;
                System.out.println("Dias para expirar: " + pp.diasParaExp());
            }
        } else {
            System.out.println("Producto no encontrado");
        }
    }

    private static void modificarProducto(Scanner scanner, Inventario inventario) {
        System.out.println("\nMODIFICAR PRODUCTO");
        System.out.print("ID del producto a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Producto producto = inventario.buscarProducto(id);
        if (producto == null) {
            System.out.println("Producto no encontrado");
            return;
        }

        System.out.println("Producto actual: " + producto);

        System.out.print("Nuevo nombre: ");
        producto.setNombre(scanner.nextLine());

        System.out.print("Nuevo precio: ");
        producto.setPrecio(scanner.nextDouble());

        System.out.print("Nuevo stock minimo: ");
        producto.setStockMinimo(scanner.nextInt());

        System.out.print("Nuevo stock maximo: ");
        producto.setStockMaximo(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Producto modificado exitosamente");
    }

    private static void eliminarProducto(Scanner scanner, Inventario inventario) {
        System.out.println("\nELIMINAR PRODUCTO");
        System.out.print("ID del producto a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (inventario.eliminarProducto(id)) {
            System.out.println("Producto eliminado exitosamente");
        } else {
            System.out.println("Error: Producto no encontrado");
        }
    }

    private static void gestionarStock(Scanner scanner, Inventario inventario) {
        System.out.println("\nGESTION DE STOCK");
        System.out.print("ID del producto: ");
        int id = scanner.nextInt();

        Producto producto = inventario.buscarProducto(id);
        if (producto == null) {
            System.out.println("Producto no encontrado");
            return;
        }

        System.out.println("Producto: " + producto.getNombre());
        System.out.println("Stock actual: " + producto.getCantidadStock());

        System.out.print("Accion (1-Agregar stock, 2-Retirar stock): ");
        int accion = scanner.nextInt();

        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        if (accion == 1) {
            producto.agregarStock(cantidad);
            System.out.println("Stock agregado. Nuevo stock: " + producto.getCantidadStock());
        } else if (accion == 2) {
            if (producto.retirarStock(cantidad)) {
                System.out.println("Stock retirado. Nuevo stock: " + producto.getCantidadStock());
            } else {
                System.out.println("Error: Stock insuficiente");
            }
        } else {
            System.out.println("Accion no valida");
        }
    }

    private static void verReportes(Inventario inventario) {
        System.out.println("\nREPORTES DEL INVENTARIO");

        System.out.println("Total de productos: " + inventario.totalProductos());
        System.out.printf("Valor total del inventario: $%.2f\n", inventario.valorTotalInventario());

        System.out.println("\nProductos que necesitan reabastecimiento:");
        var productosReabastecer = inventario.productosNecesitanReabastecimiento();
        if (productosReabastecer.isEmpty()) {
            System.out.println("No hay productos que necesiten reabastecimiento");
        } else {
            productosReabastecer.forEach(System.out::println);
        }

        System.out.println("\nProductos proximos a expirar:");
        var productosExpiracion = inventario.productosProximosAExpiracion();
        if (productosExpiracion.isEmpty()) {
            System.out.println("No hay productos proximos a expirar");
        } else {
            productosExpiracion.forEach(p ->
                    System.out.println(p.getNombre() + " - Dias: " + p.diasParaExp())
            );
        }
    }
}