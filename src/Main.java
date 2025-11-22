import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Crear inventario con presupuesto y espacio
        Inventario inventario = new Inventario(5000.0, 100.0);

        // Crear productos perecederos
        ProductoPerecedero leche = new ProductoPerecedero(
                "Leche Entera", 1, 1.25, 15, 10, 50,
                LocalDate.now().plusDays(5)
        );

        ProductoPerecedero yogurt = new ProductoPerecedero(
                "Yogurt Natural", 2, 0.75, 8, 5, 30,
                LocalDate.now().plusDays(10)
        );

        // Crear productos no perecederos
        ProductoNoPerecedero arroz = new ProductoNoPerecedero(
                "Arroz Integral", 3, 2.50, 25, 15, 100,
                "Granos", 12
        );

        ProductoNoPerecedero atun = new ProductoNoPerecedero(
                "Atún en Lata", 4, 3.20, 12, 8, 40,
                "Conservas", 24
        );

        // Agregar productos al inventario
        inventario.agregarProducto(leche);
        inventario.agregarProducto(yogurt);
        inventario.agregarProducto(arroz);
        inventario.agregarProducto(atun);

        // Mostrar todos los productos
        System.out.println("=== INVENTARIO COMPLETO ===");
        inventario.listarProductos();

        // Verificar productos que necesitan reabastecimiento
        System.out.println("\n=== PRODUCTOS QUE NECESITAN REABASTECIMIENTO ===");
        inventario.productosNecesitanReabastecimiento()
                .forEach(System.out::println);

        // Verificar productos próximos a expirar
        System.out.println("\n=== PRODUCTOS PRÓXIMOS A EXPIRAR ===");
        inventario.productosProximosAExpiracion()
                .forEach(p -> System.out.println(p.getNombre() +
                        " - Días restantes: " + p.diasParaExp()));

        // Mostrar valor total del inventario
        System.out.println("\n=== VALOR TOTAL DEL INVENTARIO ===");
        System.out.printf("$%.2f%n", inventario.valorTotalInventario());

        // Probar funcionalidades específicas
        System.out.println("\n=== PRUEBAS ESPECÍFICAS ===");
        System.out.println("La leche " + (leche.estaExp() ? "está expirada" : "no está expirada"));
        System.out.println("Fecha fin garantía del arroz: " + arroz.calcularFechaFinGarantia());

        // Simular venta de productos
        System.out.println("\n=== SIMULACIÓN DE VENTAS ===");
        if (leche.retirarStock(5)) {
            System.out.println("Venta de 5 unidades de leche realizada");
        }
        System.out.println("Stock actual de leche: " + leche.getCantidadStock());
    }
}