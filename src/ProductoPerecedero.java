import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ProductoPerecedero extends Producto{

    private LocalDate fechaExpiracion;

    public ProductoPerecedero(String nombre, int id, int cantidadstock, int stockMinimo, int stockMaximo, double precio, LocalDate fechaExpiracion) {
        super(nombre, id, cantidadstock, stockMinimo, stockMaximo, precio);
        this.fechaExpiracion = fechaExpiracion;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExp) {
        this.fechaExpiracion = fechaExpiracion;
    }

    //Metodos

    public boolean estaExp() {
        return LocalDate.now().isAfter(fechaExpiracion);
    }

    public long diasParaExp() {
        return ChronoUnit.DAYS.between(LocalDate.now(), fechaExpiracion);
    }

    @Override
    public  boolean necesitaReabas() {
        // Productos perecederos necesitan reabas... urgentemente
        return super.necesitaReabas() || diasParaExp() <= 7;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("| Expira: %s (%d dias)", fechaExpiracion, diasParaExp());
    }
}
