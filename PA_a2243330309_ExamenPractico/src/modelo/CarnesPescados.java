package modelo;

public class CarnesPescados extends NewProductos {
    private String conservacion = "Congelados / Frescos";
    
    public CarnesPescados(String folio, String nombre, int stock, double precio, String rutaImagen) {
        super(folio, nombre, "Abarrotes", stock, precio, rutaImagen);
    }

    @Override
    public String getInformacionLogistica() {
        return "Conservación: " + conservacion;
    }

    public String getConservacion() { return conservacion; }
}