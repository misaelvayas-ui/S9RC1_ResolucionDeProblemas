import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ProductoPerecedero extends Producto{

    private LocalDate fechaExpiracion;

    public ProductoPerecedero(String nombre, int id,double precio, int cantidadstock, int stockMinimo, int stockMaximo, LocalDate fechaExpiracion) {
        super(nombre, id, precio, cantidadstock, stockMinimo, stockMaximo);
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
    public  boolean necesitaReabastecimiento() {
        // Productos perecederos necesitan reabas... urgentemente
        return super.necesitaReabastecimiento() || diasParaExp() <= 7;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("| Expira: %s (%d dias)", fechaExpiracion, diasParaExp());
    }
}
