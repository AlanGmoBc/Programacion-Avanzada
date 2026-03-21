package modelo;

public class Bebidas extends NewProductos {

    private String tipoCarga = "Carga Líquida / Pesada";
    
    public Bebidas(String folio, String nombre, int stock, double precio, String rutaImagen) {
        super(folio, nombre, "Abarrotes", stock, precio, rutaImagen);
    }

    @Override
    public String getInformacionLogistica() {
        return "Manejo: " + tipoCarga;
    }

    public String getTipoCarga() {
        return tipoCarga;
    }
}