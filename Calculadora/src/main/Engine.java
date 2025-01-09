package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private JButton porcentaje;
	private JButton borraUno;
	private JButton elevado;

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
		this.multiplica = new JButton("x");
		this.resta = new JButton("-");
		this.suma= new JButton("+");
		this.igual= new JButton("=");
		this.borra= new JButton("C");
		this.porcentaje = new JButton("%");
		this.borraUno = new JButton("←");
		//configurar la ventana
		setSetting();
		// Añadir ActionListener a los botones
		addActionEvent();
	
		
	}
	/**
	 * Metodo que configura las propiedades de la ventana y de los paneles 
	 */
	private void setSetting() {
		//configurar el layaut y agregar paneles
		this.contentPanel.setLayout(new BorderLayout());
		this.displeyPanel.setLayout(new BorderLayout());
		this.buttonPanel.setLayout(new GridLayout(5,4,5,5));
		//configurar display
		this.display.setFont(new Font("Arial",Font.BOLD,30));
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
		this.buttonPanel.add(n0);
		this.buttonPanel.add(this.borra);	
		this.buttonPanel.add(this.igual);
		this.buttonPanel.add(this.divide);
		this.buttonPanel.add(this.porcentaje);
		this.buttonPanel.add(this.borraUno);
		//establecer características de los botones 
		setFeaturesButton(this.n0, ButtonType.REGULAR);
		setFeaturesButton(this.n1, ButtonType.REGULAR);
		setFeaturesButton(this.n2, ButtonType.REGULAR);
		setFeaturesButton(this.n3, ButtonType.REGULAR);
		setFeaturesButton(this.n4, ButtonType.REGULAR);
		setFeaturesButton(this.n5, ButtonType.REGULAR);
		setFeaturesButton(this.n6, ButtonType.REGULAR);
		setFeaturesButton(this.n7, ButtonType.REGULAR);
		setFeaturesButton(this.n8, ButtonType.REGULAR);
		setFeaturesButton(this.n9, ButtonType.REGULAR);
		setFeaturesButton(this.divide, ButtonType.OPERATOR);
		setFeaturesButton(this.multiplica, ButtonType.OPERATOR);
		setFeaturesButton(this.suma, ButtonType.OPERATOR);
		setFeaturesButton(this.resta, ButtonType.OPERATOR);
		setFeaturesButton(this.igual, ButtonType.OPERATOR);
		setFeaturesButton(this.borra, ButtonType.OPERATOR);
		setFeaturesButton(this.porcentaje, ButtonType.OPERATOR);
		setFeaturesButton(this.borraUno,ButtonType.OPERATOR);
		
		
		
		  // Agregar paneles al contenido principal
        contentPanel.add(displeyPanel, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.CENTER);
        //contentPanel.add(this.igual, BorderLayout.SOUTH);
		
		
		
	      // Configurar el frame principal
        this.frame.setContentPane(contentPanel);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(400, 600);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }
	/**
	 * Metodo que establece el color de los botones y sus caracteristicas 
	 * @param _Button El boton a configurar
	 * @param _Type El tipo de boton (REGULAR, OPERATOR)
	 */
	private void setFeaturesButton(JButton _button, ButtonType _type ) {
		if(_type == ButtonType.REGULAR) {
			_button.setBackground(Color.LIGHT_GRAY);
		}else
			_button.setBackground(Color.orange);
		
		_button.setFont(new Font("Arial",Font.BOLD,20));	
	}
	/**
	 * Metodo que añade ActtionListener a los botones
	 */
	private void addActionEvent() {
		this.n0.addActionListener(this);
		this.n1.addActionListener(this);
		this.n2.addActionListener(this);
		this.n3.addActionListener(this);
		this.n4.addActionListener(this);
		this.n5.addActionListener(this);
		this.n6.addActionListener(this);
		this.n7.addActionListener(this);
		this.n8.addActionListener(this);
		this.n9.addActionListener(this);
		this.divide.addActionListener(this);
		this.multiplica.addActionListener(this);
		this.resta.addActionListener(this);
		this.suma.addActionListener(this);
		this.igual.addActionListener(this);
		this.borra.addActionListener(this);
		this.porcentaje.addActionListener(this);
		
	}
	/**
	 * Metodo que realiza las operaciones matemáticas 
	 */
	private void operacion() {
		switch (this.operacion) {
		case '+':
			this.resultado= this.num1+ this.num2;
			break;
		case '-':
			this.resultado= this.num1- this.num2;
			break;
		case 'x':
			this.resultado= this.num1* this.num2;
			break;
		case '/':
            this.resultado = this.num1 / this.num2;   
            break;
		case '%':
			this.resultado= this.num1*(this.num2/100);
			break;
			
		}
		this.display.setText(String.valueOf(this.resultado));//
		
	}
	/**
	 * Este metodo se ejecuta cada vez que se pulsa un boton y genera un evento de accion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//recoge el tipo de boton que se ha pulsado
		Object source = e.getSource();
		//obtiene el texto del boton presionado
		String input_text= e.getActionCommand();
		
		String texto = this.display.getText();
		String regex = "(-?\\d+)([%+-x/^])(-?\\d)";
		Pattern pattern= Pattern.compile(regex);
		Matcher matcher= pattern.matcher(texto);
		
		if(matcher.matches()) {
			this.num1= Integer.parseInt(matcher.group(1));
			this.operacion=matcher.group(2).charAt(0);
			this.num2=Integer.parseInt(matcher.group(3));
		}else {
			this.resultado=0;
		}
		//boton "C" borra
		if(source==this.borra) {
			this.display.setText("");
			this.num1=0;
			this.num2=0;
			this.resultado=0;
			this.operacion=' ';
		}else if(source== this.igual) {//botón "=" 
			if(this.operacion=='/' && this.num2==0) {//division por 0
				this.display.setText("Error");
			}else {
				operacion();
				this.display.setText(String.valueOf(this.resultado));
				
			}
			
			
		}else {//otros botones 
			this.display.setText(this.display.getText()+ input_text);
		}
		
	
	}
}
