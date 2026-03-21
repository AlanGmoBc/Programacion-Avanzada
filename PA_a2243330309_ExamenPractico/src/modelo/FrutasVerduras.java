package modelo;

public class FrutasVerduras extends NewProductos {
    private String tipoPerecedero = "Perecedero (Venta por peso)";
    
    public FrutasVerduras(String folio, String nombre, int stock, double precio, String rutaImagen) {
        super(folio, nombre, "Abarrotes", stock, precio, rutaImagen);
    }

    @Override
    public String getInformacionLogistica() {
        return "Estado: " + tipoPerecedero;
    }

    public String getTipoPerecedero() { return tipoPerecedero; }
}