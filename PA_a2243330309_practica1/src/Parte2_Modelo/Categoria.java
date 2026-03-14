package Parte2_Modelo;

public class Categoria {
    private String idCategoria;
    private String nombreCategoria;

    public Categoria(String id, String nombre) {
        this.idCategoria = id;
        this.nombreCategoria = nombre;
    }

    public String getIdcategoria() { return idCategoria; }
    public String getNombre() { return nombreCategoria; }

    @Override
    public String toString() {
        return nombreCategoria;
    }
}