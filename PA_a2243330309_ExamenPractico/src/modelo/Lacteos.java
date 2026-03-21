package modelo;

public class Lacteos extends NewProductos {
	
    private String manejoFrio = "Requiere Cadena de Frío (Refrigerado)";
    
    public Lacteos(String folio, String nombre, int stock, double precio, String rutaImagen) {
        super(folio, nombre, "Abarrotes", stock, precio, rutaImagen);
    }

    @Override
    public String getInformacionLogistica() {
        return "Logística: " + manejoFrio;
    }

    public String getManejoFrio() {
        return manejoFrio;
    }
}