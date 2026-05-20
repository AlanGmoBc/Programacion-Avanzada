package Main;

import Controlador.Cejemplotabla;

public class Principal {
    static Cejemplotabla y;
    
    public static void main(String[] args) {
        try {
            y = new Cejemplotabla();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}