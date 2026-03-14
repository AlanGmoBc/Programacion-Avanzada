package Parte3;
import javax.swing.*;
import java.awt.*;
import Modelo.Categoria;

public class DlgCategoria extends JDialog {
    private JTextField tId, tNom;
    private Categoria catNueva;
    private boolean guardado = false;

    public DlgCategoria(Frame padre) {
        super(padre, "Datos de Categoría", true);
        setSize(300, 150);
        setLayout(new GridLayout(3, 2, 5, 5));
        setLocationRelativeTo(padre);

        add(new JLabel(" ID:")); tId = new JTextField(); add(tId);
        add(new JLabel(" Nombre:")); tNom = new JTextField(); add(tNom);

        JButton btnG = new JButton("Guardar");
        btnG.addActionListener(e -> {
            if(!tId.getText().isEmpty() && !tNom.getText().isEmpty()){
                catNueva = new Categoria(tId.getText(), tNom.getText());
                guardado = true;
                dispose();
            }
        });
        add(btnG);
        
        JButton btnC = new JButton("Cancelar");
        btnC.addActionListener(e -> dispose());
        add(btnC);
    }
    public boolean isGuardado() { return guardado; }
    public Categoria getCategoria() { return catNueva; }
}
