package modelo;
public class Producto {
    public String sku, nombre, categoria, unidad, proveedor, transporte; // Añadidos estos dos
    public double precio, ganancia;
    public int stock;

    public Producto(String sku, String nombre, String categoria, double precio, double ganancia, int stock, String unidad, String proveedor, String transporte) {
        this.sku = sku;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.ganancia = ganancia;
        this.stock = stock;
        this.unidad = unidad;
        this.proveedor = proveedor;
        this.transporte = transporte;
    }
}