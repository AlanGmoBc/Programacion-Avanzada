package controller;

import model.Producto;
import model.ProductoDAO;
import view.MainFrame;

public class Control {

    private MainFrame vista;
    private ProductoDAO dao;

    public Control(MainFrame vista) {
        this.vista = vista;
        this.dao = new ProductoDAO();
    }

    public void guardar(String codigo, String nombre, String precioTexto) {

        try {

            double precio = Double.parseDouble(precioTexto);

            Producto p = new Producto(codigo, nombre, precio);

            dao.add(p);

            vista.mostrar("Guardado correctamente");

        } catch (Exception e) {
            vista.mostrar("Error al guardar");
        }
    }

    public void listar() {

        try {

            String texto = "";

            for (String s : dao.all()) {
                texto += s + "\n";
            }

            vista.mostrar(texto);

        } catch (Exception e) {
            vista.mostrar("Error al listar");
        }
    }
}