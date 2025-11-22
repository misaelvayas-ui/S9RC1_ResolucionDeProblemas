import java.util.*;

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
    public void listaProductos(){
        for(Producto p: productos){
            System.out.println(p);
        }
    }


}
