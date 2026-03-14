package Parte3;
import javax.swing.*;
import java.awt.*;
import Modelo.Obra;

public class DlgObra extends JDialog {
    private JTextField tId, tNom, tUbi;
    private Obra obraNueva;
    private boolean guardado = false;

    public DlgObra(Frame padre) {
        super(padre, "Datos de la Obra", true);
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));
        setLocationRelativeTo(padre);

        add(new JLabel(" ID:")); tId = new JTextField(); add(tId);
        add(new JLabel(" Nombre:")); tNom = new JTextField(); add(tNom);
        add(new JLabel(" Ubicación:")); tUbi = new JTextField(); add(tUbi);

        JButton btnG = new JButton("Guardar");
        btnG.addActionListener(e -> {
            obraNueva = new Obra(tId.getText(), tNom.getText(), tUbi.getText());
            guardado = true;
            dispose();
        });
        add(btnG);
        setVisible(false);
    }
    public boolean isGuardado() { return guardado; }
    public Obra getObra() { return obraNueva; }
}