package controlador;

import modelo.Producto;
import persistencia.ArchivoCSV;
import vista.VentanaProductos;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InventarioControlador {
    private ArrayList<Producto> listaProductos;
    private VentanaProductos vista;
    private ArchivoCSV persistencia;

    public InventarioControlador(VentanaProductos vista) {
        this.vista = vista;
        this.persistencia = new ArchivoCSV();
        
        this.listaProductos = persistencia.importarCSV();
        
        initEvents();
        actualizarTabla(listaProductos);
    }

    private void initEvents() {
    	
        vista.btnBuscar.addActionListener(e -> aplicarFiltros());
        vista.btnLimpiar.addActionListener(e -> {
            limpiarInterfaz();
            actualizarTabla(listaProductos);
        });
    }

    private void aplicarFiltros() {
    	
        String idFiltro = vista.txtId.getText().toLowerCase().trim();
        String nombreFiltro = vista.txtNombre.getText().toLowerCase().trim();
        String categoriaFiltro = vista.cbCategoria.getSelectedItem().toString();
        
        List<Producto> filtrados = listaProductos.stream()
            .filter(p -> idFiltro.isEmpty() || p.getId().toLowerCase().contains(idFiltro))
            .filter(p -> nombreFiltro.isEmpty() || p.getNombre().toLowerCase().contains(nombreFiltro))
            .filter(p -> categoriaFiltro.equals("General") || p.getCategoria().equals(categoriaFiltro))
            .filter(p -> {
            	
                if (vista.rbActivo.isSelected()) return p.getStock() > 0;
                if (vista.rbInactivo.isSelected()) return p.getStock() <= 0;
                return true; 
            })
            .collect(Collectors.toList());

        actualizarTabla(new ArrayList<>(filtrados));
    }

    private void limpiarInterfaz() {
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtPrecio.setText("");
        vista.txtStock.setText("");
        vista.cbCategoria.setSelectedIndex(0);
        vista.rbActivo.setSelected(true);
    }

    private void actualizarTabla(ArrayList<Producto> lista) {
        vista.modeloTabla.setRowCount(0);
        
        for (Producto p : lista) {
            vista.modeloTabla.addRow(new Object[]{
                p.getId(), 
                p.getNombre(), 
                p.getCategoria(), 
                p.getPrecio(), 
                p.getStock(), 
                p.getEstado()
            });
        }
    }
}