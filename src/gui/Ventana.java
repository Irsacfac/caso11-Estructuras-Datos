package gui;

import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

import otros.IConstants;

public class Ventana extends JFrame implements IConstants{
	
	public Ventana() {
		this("No Title");
	}
	
	public Ventana(String pTitle) {
		super(pTitle);
		
		this.setLayout(null);
		this.setSize(PANTALLA_ANCHURA, PANTALLA_ALTURA);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setExtendedState(Frame.NORMAL);
        //this.addMouseListener(this);
        
        initComponents();
        
        this.setVisible(true);
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		this.getContentPane().add(panel);
	}
}
