public class ProductoNoPerecedero extends Producto{
    private String tipo;
    private int garantiaMeses;
    public ProductoNoPerecedero(String nombre, int id, double precio,
                                int cantidadStock, int stockMinimo, int stockMaximo,
                                String tipo, int garantiaMeses) {
        super(nombre, id, precio, cantidadStock, stockMinimo, stockMaximo);
        this.tipo = tipo;
        this.garantiaMeses = garantiaMeses;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getGarantiaMeses() { return garantiaMeses; }
    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    public String calcularFechaFinGarantia() {
        return java.time.LocalDate.now()
                .plusMonths(garantiaMeses)
                .toString();
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: " + tipo;
    }
}
