package Parte3;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import Modelo.*;

public class Practica03_a extends JInternalFrame {
    private JTable tabla;
    private JLabel lblImagen;
    private ListaInsumos li;
    private ListaCategorias lc;

    public Practica03_a(ListaInsumos li, ListaCategorias lc) {
        super("Gestión de Insumos", true, true, true, true);
        this.li = li;
        this.lc = lc;
        setSize(700, 400);
        setLayout(new BorderLayout());

        // Tabla en el centro
        tabla = new JTable(li.getmodelo(lc));
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Panel lateral para la imagen
        JPanel panelImagen = new JPanel();
        panelImagen.setBorder(BorderFactory.createTitledBorder("Vista previa"));
        lblImagen = new JLabel("Sin imagen", SwingConstants.CENTER);
        lblImagen.setPreferredSize(new Dimension(150, 150));
        panelImagen.add(lblImagen);
        add(panelImagen, BorderLayout.EAST);

        // Evento de selección en la tabla
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabla.getSelectedRow() != -1) {
                String id = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
                cargarImagen(id);
            }
        });
    }

    private void cargarImagen(String id) {
        try {
            String ruta = "Imagenes/" + id + ".png";
            ImageIcon icono = new ImageIcon(ruta);
            // Redimensionar imagen
            Image img = icono.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(img));
            lblImagen.setText("");
        } catch (Exception e) {
            lblImagen.setIcon(null);
            lblImagen.setText("No encontrada");
        }
    }
}