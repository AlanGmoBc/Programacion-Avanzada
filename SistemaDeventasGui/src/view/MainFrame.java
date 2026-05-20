package view;

import javax.swing.*;
import java.awt.*;
import controller.Control;

public class MainFrame extends JFrame {

    JTextField c = new JTextField();
    JTextField n = new JTextField();
    JTextField p = new JTextField();
    JTextArea area = new JTextArea();

    public MainFrame() {

        setTitle("Sistema Ventas");
        setSize(400, 400);
        setLayout(new GridLayout(8,1));

        add(new JLabel("Codigo"));
        add(c);

        add(new JLabel("Nombre"));
        add(n);

        add(new JLabel("Precio"));
        add(p);

        JButton b = new JButton("Guardar");
        JButton l = new JButton("Listar");

        add(b);
        add(l);

        add(new JScrollPane(area));

        Control ctrl = new Control(this);

        // ✅ CORREGIDO (sin HTML raro)
        b.addActionListener(e -> 
            ctrl.guardar(c.getText(), n.getText(), p.getText())
        );

        l.addActionListener(e -> ctrl.listar());
    }

    // ✅ CORREGIDO (ahora sí hace algo)
    public void mostrar(String texto) {
        area.setText(texto);
    }
}