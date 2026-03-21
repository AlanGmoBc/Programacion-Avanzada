package modelo;

public class LimpiezaHogar extends NewProductos {
    private String advertencia = "Químicos (Almacenamiento Aislado)";
    
    public LimpiezaHogar(String folio, String nombre, int stock, double precio, String rutaImagen) {
        super(folio, nombre, "Abarrotes", stock, precio, rutaImagen);
    }

    @Override
    public String getInformacionLogistica() {
        return "Advertencia: " + advertencia;
    }

    public String getAdvertencia() { return advertencia; }
}