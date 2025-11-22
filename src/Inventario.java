import java.util.*;
import java.util.stream.Collectors;

public class Inventario {
    private List<Producto> productos;
    private double presupuesto;
    private double espacioAlmacenamiento;
    private double espacioUtilizado;

    public Inventario(double presupuesto, double espacioAlmacenamiento){
        this.productos = new ArrayList<>();
        this.presupuesto = presupuesto;
        this.espacioAlmacenamiento = espacioAlmacenamiento;
        this.espacioUtilizado = 0;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public double getEspacioAlmacenamiento() {
        return espacioAlmacenamiento;
    }

    public void setEspacioAlmacenamiento(double espacioAlmacenamiento) {
        this.espacioAlmacenamiento = espacioAlmacenamiento;
    }

    public double getEspacioUtilizado() {
        return espacioUtilizado;
    }

    public void setEspacioUtilizado(double espacioUtilizado) {
        this.espacioUtilizado = espacioUtilizado;
    }

    public Producto buscarProducto(int id){
        for (Producto p:productos){
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }
    public void listarProductos(){
        for(Producto p: productos){
            System.out.println(p);
        }
    }
    public boolean agregarProducto(Producto producto) {
        productos.add(producto);
        return true;
    }

    public boolean eliminarProducto(int id){
        for (Producto p : productos) {
            if (p.getId() == id) {
                productos.remove(p);
                return true;
            }
        }
        return false;
    }
    public List<Producto> productosNecesitanReabastecimiento() {
        List<Producto> productosReabastecer = new ArrayList<>();
        for (Producto p : productos) {
            if (p.necesitaReabastecimiento()) {
                productosReabastecer.add(p);
            }
        }
        return productosReabastecer;
    }
    public double valorTotalInventario() {
        double total = 0;
        for (Producto p : productos) {
            total += p.getPrecio() * p.getCantidadStock();
        }
        return total;
    }
    public List<ProductoPerecedero> productosProximosAExpiracion() {
        return productos.stream()
                .filter(p -> p instanceof ProductoPerecedero)
                .map(p -> (ProductoPerecedero) p)
                .filter(pp -> pp.diasParaExp() <= 15)
                .collect(Collectors.toList());
    }

}
