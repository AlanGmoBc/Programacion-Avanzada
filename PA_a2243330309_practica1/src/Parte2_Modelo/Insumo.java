package Parte2_Modelo;

public class Insumo {
    private String idProducto;
    private String producto;
    private String idCategoria;

    public Insumo(String id, String nombre, String idCat) {
        this.idProducto = id;
        this.producto = nombre;
        this.idCategoria = idCat;
    }

    public String getIdProducto() { return idProducto; }
    public String getProducto() { return producto; }
    public String getIdCategoria() { return idCategoria; }

    public String toLinea() {
        return idProducto + "," + producto + "," + idCategoria;
    }
}