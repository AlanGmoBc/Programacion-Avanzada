package Parte1_Vista;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class Practica02_b extends JFrame implements ActionListener {
	private JPanel PanelPrincipal;
    private JButton Bsalir;
    
    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
    		public void run() {
    			try {
    				Practica02_b frame = new Practica02_b();
    				frame.setVisible(true);
    			}catch(Exception e) {
    				e.printStackTrace();
    			}
    		}
    	});
    }
    
    public Practica02_b() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(100, 100, 450, 300);
    	setTitle("Frame Practica02_b");
    	PanelPrincipal = new JPanel();
    	PanelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
    	setContentPane(PanelPrincipal);
    	PanelPrincipal.setLayout(null);
        Bsalir = new JButton("Salir");
        Bsalir.setBounds(145, 124, 89, 23);
        Bsalir.addActionListener(this);
        PanelPrincipal.add(Bsalir);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Bsalir) {
            JOptionPane.showMessageDialog(null, "Saliendo...");
            dispose();
        }
    }
}