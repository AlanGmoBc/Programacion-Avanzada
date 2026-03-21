package modelo;

public abstract class NewProductos {
    protected String folio;      
    protected String nombre;
    protected String categoria;
    protected int stock;
    protected double precio;
    protected String rutaImagen; 

    public NewProductos(String folio, String nombre, String categoria, int stock, double precio, String rutaImagen) {
        this.folio = folio;
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
        this.rutaImagen = rutaImagen;
    }
    
    public String getFolio() { return folio; }
    
    public String getNombre() { return nombre; }
    
    public String getCategoria() { return categoria; }
    
    public int getCantidad() {
        return this.stock;
    }

    public int getStock() { 
        return stock; 
    }

    public double getPrecio() { 
        return precio; 
    }

    public String getRutaImagen() { 
        return rutaImagen; 
    }

    public abstract String getInformacionLogistica();
}