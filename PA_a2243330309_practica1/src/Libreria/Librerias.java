package Libreria;
import javax.swing.*;

public class Librerias {
    public void menuspadre(JMenuBar barra, boolean estado) {
        for (int i = 0; i < barra.getMenuCount(); i++) {
            barra.getMenu(i).setEnabled(estado);
        }
    }
}