package Modelo;

public class Obra {
    private String idObra;
    private String nombreObra;
    private String ubicacion;

    public Obra(String id, String nombre, String ubicacion) {
        this.idObra = id;
        this.nombreObra = nombre;
        this.ubicacion = ubicacion;
    }

    // Getters
    public String getIdObra() { return idObra; }
    public String getNombreObra() { return nombreObra; }
    public String getUbicacion() { return ubicacion; }
}