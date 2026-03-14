package Parte1_Vista;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class Practica02_b2 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel PanelPrincipal;
	private JButton Bsalir;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Practica02_b2 frame = new Practica02_b2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Practica02_b2() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(100, 100, 450, 300);
    	setTitle("Frame Practica02_b");
    	PanelPrincipal = new JPanel();
    	PanelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
    	setContentPane(PanelPrincipal);
    	PanelPrincipal.setLayout(null);
        Bsalir = new JButton("Salir");
        Bsalir.setBounds(104, 87, 89, 23);
        Bsalir.setMnemonic(KeyEvent.VK_S);
        Bsalir.setDisplayedMnemonicIndex(0);
        Bsalir.addActionListener(this);
        PanelPrincipal.add(Bsalir);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
