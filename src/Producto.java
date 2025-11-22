import java.time.LocalDate;

public class Producto {
    private String nombre;
    private int id, cantidadstock, stockMinimo, stockMaximo;
    private double precio;
    private LocalDate fechaUltReabastecimiento;

    public Producto(String nombre, int id, int cantidadstock, int stockMinimo, int stockMaximo, double precio) {
        this.nombre = nombre;
        this.id = id;
        this.cantidadstock = cantidadstock;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
        this.precio = precio;
        this.fechaUltReabastecimiento = LocalDate.now();
    }

    //Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidadstock() {
        return cantidadstock;
    }

    public void setCantidadstock(int cantidadstock) {
        this.cantidadstock = cantidadstock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public int getStockMaximo() {
        return stockMaximo;
    }

    public void setStockMaximo(int stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaUltReabastecimiento() {
        return fechaUltReabastecimiento;
    }

    public void setFechaUltReabastecimiento(LocalDate fecha) {
        this.fechaUltReabastecimiento = fecha;
    }

    //Metodos de funcionalidad

    public boolean necesitaReabas() {
        return cantidadstock <= stockMinimo;
    }

    public void agregarReabas(int cantidad) {
        if (cantidad > 0) {
            cantidadstock += cantidad;
            if (cantidadstock > stockMaximo) {
                cantidadstock = stockMaximo;
            }
            fechaUltReabastecimiento = LocalDate.now();
        }
    }

    public boolean retirarStock(int cantidad) {
        if (cantidad > 0 && cantidad <= cantidadstock) {
            cantidadstock -= cantidad;
            return true;
        }
        return false;
    }

    public double calcularValorFinal() {
        return precio * cantidadstock;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | Precio: $%.2f | Stock: %d | Min: %d | Max: %d",
                id, nombre, precio, cantidadstock, stockMinimo, stockMaximo);
    }
}
