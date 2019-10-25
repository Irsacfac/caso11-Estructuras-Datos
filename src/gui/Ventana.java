package gui;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import otros.IConstants;

public class Ventana extends JFrame implements IConstants{
	private JPanel buttonsPanel;
	private JPanel textPanel;
	private JTextField textbar;
	private JButton botonPalabra;
	private JButton botonRango;
	private JButton botonURL;
	
	public Ventana() {
		this("No title");
	}

	public Ventana(String pTitle) {
		super(pTitle);
		this.setLayout(null);
		this.setSize(PANTALLA_ANCHURA, PANTALLA_ALTURA);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setExtendedState(Frame.NORMAL);
        
        initComponents();
        
        this.setVisible(true);
	}
	
	private void initComponents() {
		buttonsPanel = new JPanel();
		buttonsPanel.setBounds(0, PANTALLA_ALTURA/2, PANTALLA_ANCHURA, PANTALLA_ALTURA/2);
		buttonsPanel.setBackground(Color.CYAN);
		this.getContentPane().add(buttonsPanel);
		
		textPanel = new JPanel();
		textPanel.setBounds(0, 0, PANTALLA_ANCHURA, PANTALLA_ALTURA/2);
		textPanel.setLayout(null);
		textPanel.setBackground(Color.CYAN);
		this.getContentPane().add(textPanel);
		
		textbar = new JTextField();
		textbar.setBounds(X_TEXTLABEL, (int)PANTALLA_ALTURA/4, TEXTLABEL_ANCHURA, DEFAULT_BUTTON_ALTURA);
		textPanel.add(textbar);
		
		botonPalabra = new JButton("Buscar Palabra");
		buttonsPanel.add(botonPalabra);
		
		botonRango = new JButton("Buscar Rango");
		buttonsPanel.add(botonRango);
		
		botonURL = new JButton("Buscar URL");
		buttonsPanel.add(botonURL);
	}
}
