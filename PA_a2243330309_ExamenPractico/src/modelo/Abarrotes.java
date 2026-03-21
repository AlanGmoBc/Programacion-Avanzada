package modelo;

public class Abarrotes extends NewProductos {
	
    private String tipoCuidado = "No perecedero (Anaquel)";
    
    public Abarrotes(String folio, String nombre, int stock, double precio, String rutaImagen) {
        super(folio, nombre, "Abarrotes", stock, precio, rutaImagen);
    }
     
    @Override
    public String getInformacionLogistica() {
        return "Logística: " + tipoCuidado;
    }
    
    // Getter para que GSON pueda guardar este dato extra en el JSON
    public String getTipoCuidado() {
        return tipoCuidado;
    }
}