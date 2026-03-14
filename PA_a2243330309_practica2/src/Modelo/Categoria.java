package Modelo;

public class Categoria {
    private String idCategoria;
    private String nombre;

    public Categoria(String id, String nombre) {
        this.idCategoria = id;
        this.nombre = nombre;
    }

    public String getIdcategoria() { return idCategoria; }
    
    @Override
    public String toString() { return nombre; }
}