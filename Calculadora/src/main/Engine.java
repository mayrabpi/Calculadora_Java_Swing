package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Clase principal de la calculadora.
 * Implementa ActionListener para manejar los eventos de los botones 
 */
public class Engine implements ActionListener {
	
	private JFrame frame;
	private JPanel contentPanel;//panel que ocupa toda la ventana
	private JPanel displeyPanel;//panel norte que contiene el display
	private JPanel buttonPanel;//panel sur que contiene los botones
	private JTextField display;//display
	//Botones
	private JButton n0;
	private JButton n1;
	private JButton n2;
	private JButton n3;
	private JButton n4;
	private JButton n5;
	private JButton n6;
	private JButton n7;
	private JButton n8;
	private JButton n9;
	private JButton divide;
	private JButton multiplica;
	private JButton resta;
	private JButton suma;
	private JButton igual;
	private JButton borra;
	private JButton negativo;
	//tipos de boton numericos o de operación
	private enum ButtonType {REGULAR, OPERATOR};
	//Variables para almacenara temporalmente los valores 
	private int num1;
	private int num2;
	private int resultado;
	private char operacion;
	
	/**
	 * Constructora de la Calculadora
	 * Inicializa los componentes graficos y configura la interfaz
	 */
	public Engine() {
		//inicializamos el marco de la ventana
		this.frame = new JFrame("Calculadora");
		this.contentPanel= new JPanel();
		this.displeyPanel= new JPanel();
		this.buttonPanel= new JPanel();
		this.display = new JTextField();
		//inicializamos los botones
		this.n0 = new JButton("0");
		this.n1 = new JButton("1");
		this.n2 = new JButton("2");
		this.n3 = new JButton("3");
		this.n4 = new JButton("4");
		this.n5 = new JButton("5");
		this.n6 = new JButton("6");
		this.n7 = new JButton("7");
		this.n8 = new JButton("8");
		this.n9 = new JButton("9");
		this.divide = new JButton("/");
		this.multiplica = new JButton("*");
		this.resta = new JButton("-");
		this.suma= new JButton("+");
		this.igual= new JButton("=");
		this.borra= new JButton("C");
		this.negativo = new JButton("±");
		setSetting();
	
		
	}
	/**
	 * Metodo que configura las propiedades de la ventana y de los paneles 
	 */
	private void setSetting() {
		//configurar el layaut y agregar paneles
		this.contentPanel.setLayout(new BorderLayout());
		this.displeyPanel.setLayout(new BorderLayout());
		this.buttonPanel.setLayout(new GridLayout(5,4,5,5));
		
		this.displeyPanel.add(this.display);
		
		//añadir botones
		this.buttonPanel.add(n7);
		this.buttonPanel.add(n8);
		this.buttonPanel.add(n9);
		this.buttonPanel.add(this.suma);	
		this.buttonPanel.add(n4);
		this.buttonPanel.add(n5);
		this.buttonPanel.add(n6);
		this.buttonPanel.add(this.resta);	
		this.buttonPanel.add(n1);
		this.buttonPanel.add(n2);
		this.buttonPanel.add(n3);
		this.buttonPanel.add(this.multiplica);	
		this.buttonPanel.add(this.borra);	
		this.buttonPanel.add(n0);
		this.buttonPanel.add(this.negativo);
		this.buttonPanel.add(this.igual);
		this.buttonPanel.add(this.divide);	
		
		  // Agregar paneles al contenido principal
        contentPanel.add(displeyPanel, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.CENTER);
		
		
		
	      // Configurar el frame principal
        frame.setContentPane(contentPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
		
		
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
	
	}

}
