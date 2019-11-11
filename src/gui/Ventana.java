package gui;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import otros.IConstants;
import otros.Manager;
import otros.Word;

public class  Ventana extends JFrame implements IConstants{
	private Manager miManager;
	private JPanel buttonsPanel;
	private JPanel textPanel;
	private JTextField textbar;
	private JButton botonPalabra;
	private JButton botonRango;
	private JButton botonURL;
	
	public Ventana(Manager pManager) {
		this("No title", pManager);
	}

	public Ventana(String pTitle, Manager pManager) {
		super(pTitle);
		this.setLayout(null);
		this.setSize(PANTALLA_ANCHURA, PANTALLA_ALTURA);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setExtendedState(Frame.NORMAL);
        miManager = pManager;
        
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
		botonPalabra.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				botonPalabraActionPerformed();
			}
		});
		
		botonRango = new JButton("Buscar Rango");
		buttonsPanel.add(botonRango);
		botonRango.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				botonRangoActionPerformed();
			}
		});
		
		botonURL = new JButton("Buscar URL");
		buttonsPanel.add(botonURL);
		botonURL.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				botonURLActionPerformed();
			}
		});

	}

	private void botonURLActionPerformed() {
		String content = getTxtBarText();
		ArrayList<String> matches = miManager.buscarURL(content);
		for (int index = 1; index-1 < matches.size(); index++){
			System.out.println(index + ")" + matches.get(index-1));
		}
	}

	private void botonRangoActionPerformed() {
		String content = getTxtBarText();
		String[] numberStrings = content.split(",");
		int[] rangeNumbers = stringToInt(numberStrings);
		ArrayList<String> matches = miManager.buscarRango(rangeNumbers[0], rangeNumbers[1]);
		System.out.println("Matches: ");
		if(matches != null) {
			for (String url : matches){
				System.out.println(url);
			}
		}else System.out.println("Out of range");
	}

	private void botonPalabraActionPerformed() {
		String content = getTxtBarText();
		String[] words = content.split(",");
		ArrayList<String> matches;
		if (words.length == 1){
			 matches = miManager.buscarPalabra(content);
		} else {
			 matches = miManager.buscarPalabras(words);
		}
		if (matches.size()>0){
			System.out.println("Matches: ");
			for (String url : matches){
				System.out.println(url);
			}
		} else System.out.println("No matches, sorry!");


	}

	private String getTxtBarText(){
		return textbar.getText();
	}

	private int[] stringToInt(String[] pNumbers){
		int[] numbers = new int[2];
		try {
			numbers[0] = Integer.parseInt(pNumbers[0]);
			numbers[1] = Integer.parseInt(pNumbers[1]);
		}catch (java.lang.NumberFormatException e) {
			return numbers;
		}
		return numbers;
	}
}
