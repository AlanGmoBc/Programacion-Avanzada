package modelo;

public class Producto {
    private String id;
    private String nombre;
    private String categoria;
    private int stock;
    private double precio;
    private String estado;
    
    public Producto(String id, String nombre, String categoria, int stock, double precio, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
        this.estado = estado;
    }
    
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public int getStock() { return stock; }
    public double getPrecio() { return precio; }
    public String getEstado() { return estado; }
    
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }
    
    public void setPrecio(double precio) { 
        this.precio = precio; 
    }
    
    public void setStock(int stock) { 
        this.stock = stock; 
    }
    
    public void setEstado(String estado) { 
        this.estado = estado; 
    }

    public void setCategoria(String categoria) { 
        this.categoria = categoria; 
    }
}