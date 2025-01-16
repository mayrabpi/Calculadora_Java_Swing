package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Engine implements ActionListener{
	// componentes básicos principales
	private JFrame frame;
	private JPanel contentPanel;// panel que ocupa toda la ventana
	private JPanel displeyPanel;// panel norte que contiene el display
	private JPanel buttonPanel;// panel sur que contiene los botones
	private JTextField display;// display
	private JPanel basepanel;//panel de los botones cambio de base 
	// Botones de la calculadora
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
	private JButton botonRetroceso;
	private JButton elevado;
	private JButton factorial;
	private JButton hexBoton;
	private JButton decBoton;
	private JButton octBoton;
	private JButton binButon;
	
	//variable para controlar la base actual
	private int currentBase = 10;//por defecto decimal
	

	// tipos de boton: númericos o de operación
	private enum ButtonType {
		REGULAR, OPERATOR
	};
	// Variables para almacenara temporalmente los números y el resultado
		private int num1;
		private int num2;
		private int resultado;
		private char operacion;// operador actual (+,-,*,/,etc)

		/**
		 * Constructora de la Calculadora Inicializa los componentes graficos y
		 * configura la interfaz
		 */
		public Engine() {
			// Crear la ventana principal y los paneles
			this.frame = new JFrame("Calculadora");
			this.contentPanel = new JPanel();
			this.displeyPanel = new JPanel();
			this.buttonPanel = new JPanel();
			this.display = new JTextField();
			// inicializar los botones y sus textos
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
			this.suma = new JButton("+");
			this.igual = new JButton("=");
			this.borra = new JButton("C");
			this.porcentaje = new JButton("%");
			this.botonRetroceso = new JButton("\u2190");
			this.elevado = new JButton("^");
			this.factorial = new JButton("!");
			this.hexBoton= new JButton("HEX");
			this.decBoton = new JButton("DEC");
			this.binButon= new JButton("BIN");
			this.octBoton= new JButton("OCT");
			// configurar la ventana
			setSetting();
			// Añadir ActionListener a los botones
			addActionEvent();
		}
		/**
		 * Metodo que configura las propiedades de la ventana y organiza los paneles
		 */
		public void setSetting() {
			// Usar diferentes diseños para organizar los paneles
			this.contentPanel.setLayout(new BorderLayout());
			this.displeyPanel.setLayout(new BorderLayout(40,50));
			this.buttonPanel.setLayout(new GridLayout(4, 5, 5, 5));// cuadrícula de 4 filas y 5 columnas
			// configurar el campo del texto del display
			this.display.setFont(new Font("Arial", Font.BOLD, 50));
			this.displeyPanel.add(this.display);
			

			// añadir botones al panel de botones
			this.buttonPanel.add(n7);
			this.buttonPanel.add(n8);
			this.buttonPanel.add(n9);
			this.buttonPanel.add(this.resta);
			this.buttonPanel.add(this.suma);
			this.buttonPanel.add(n4);
			this.buttonPanel.add(n5);
			this.buttonPanel.add(n6);
			this.buttonPanel.add(this.multiplica);
			this.buttonPanel.add(this.divide);
			this.buttonPanel.add(n1);
			this.buttonPanel.add(n2);
			this.buttonPanel.add(n3);
			this.buttonPanel.add(this.porcentaje);
			this.buttonPanel.add(this.elevado);
			this.buttonPanel.add(this.igual);
			this.buttonPanel.add(n0);
			this.buttonPanel.add(this.borra);
			this.buttonPanel.add(this.botonRetroceso);
			this.buttonPanel.add(this.factorial);

			// establecer características visuales de los botones
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
			setFeaturesButton(this.botonRetroceso, ButtonType.OPERATOR);
			setFeaturesButton(this.elevado, ButtonType.OPERATOR);
			setFeaturesButton(this.factorial, ButtonType.OPERATOR);

			// Añadir los paneles a la ventana principal
			this.contentPanel.add(this.displeyPanel, BorderLayout.NORTH);// display parte superior
			this.contentPanel.add(this.buttonPanel, BorderLayout.CENTER);// displey en el centro

			// Configurar las propiedades de la ventana
			this.frame.setContentPane(contentPanel);
			this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// cerrar la aplicación al cerrar la ventana
			this.frame.setSize(400, 500);// tamaño de la ventana
			this.frame.setLocationRelativeTo(null);// centrar ventana
			this.frame.setVisible(true);// mostrar ventana
		}
		

		/**
		 * Metodo que establece el color de los botones y sus caracteristicas
		 * 
		 * @param_Button El boton a configurar
		 * @param_Type El tipo de boton (REGULAR, OPERATOR)
		 */
		public void setFeaturesButton(JButton _button, ButtonType _type) {
			if (_type == ButtonType.REGULAR) {
				_button.setBackground(Color.LIGHT_GRAY);// fondo gris para números
			} else {// botones OPERATOR
				_button.setBackground(Color.pink);// fondo rosa para para operadores
				_button.setForeground(Color.WHITE);// texto banco
			}

			_button.setFont(new Font("Arial", Font.BOLD, 20));// fuente grande y en negrita
			_button.setBorder(BorderFactory.createLineBorder(Color.white));// bordes blancos
		}
		
		/**
		 * Metodo que añade ActtionListener a los botones(qué hacer cuando se pulsan)
		 */
		public void addActionEvent() {
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
			this.botonRetroceso.addActionListener(this);
			this.elevado.addActionListener(this);
			this.factorial.addActionListener(this);

		}

		/**
		 * Metodo que realiza las operaciones matemáticas en función del operador
		 */
		public void operacion() {
			switch (this.operacion) {
			case '+':
				this.resultado = this.num1 + this.num2;
				break;
			case '-':
				this.resultado = this.num1 - this.num2;
				break;
			case 'x':
				this.resultado = this.num1 * this.num2;
				break;
			case '/':
				this.resultado = this.num1 / this.num2;
				break;
			case '%':
				this.resultado = (this.num1 * this.num2) / 100;
				break;
			case '^':
				this.resultado = (int) Math.pow(this.num1, this.num2);
				break;
			}
			this.display.setText(String.valueOf(this.resultado));// mostrar el resultado

		}
		/**
		 * Este metodo detecta qué botón se presionó y realiza la accion correspondiente
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// recoge el tipo de boton que se ha pulsado
			Object source = e.getSource();
			// obtiene el texto del boton presionado
			String input_text = e.getActionCommand();

			String texto = this.display.getText();
			String regex = "(-?\\d+)([%+-x/^])(-?\\d+)";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(texto);

			if (matcher.matches()) {
				this.num1 = Integer.parseInt(matcher.group(1));
				this.operacion = matcher.group(2).charAt(0);
				this.num2 = Integer.parseInt(matcher.group(3));
			} else {
				this.resultado = 0;
			}
			// boton "C" borra limpia el display
			if (source == this.borra) {
				this.display.setText("");
				this.num1 = 0;
				this.num2 = 0;
				this.resultado = 0;
				this.operacion = ' ';
			} else if (source == this.igual) {// botón "="
				if (this.operacion == '/' && this.num2 == 0) {// control de error en division por 0
					this.display.setText("No se puede dividir por 0");
				} else {// si pulsa otra operación llamamos al metodo operación e imprimimos el
						// resultado
					operacion();
					this.display.setText(String.valueOf(this.resultado));
				}
			} else if (source == this.botonRetroceso) {// borra uno a uno los números u operadores
				if (!this.display.getText().isEmpty()) {// si el display no esta vacio
					this.display.setText(this.display.getText().substring(0, this.display.getText().length() - 1));
				}
			} else if (source == this.factorial) {// boton (!) para calcular el factorial de un numero entero
				int numero = Integer.parseInt(display.getText());
				int resultado = factorial(numero);
				this.display.setText(String.valueOf(resultado));
			} else {// para cualquier otro boton añade el texto al display
				this.display.setText(this.display.getText() + input_text);
			}
		}
		
		/**
		 * Metodo que calcula el factorial de un numero
		 * 
		 * @param numero al que se le calcula el factorial
		 * @return resultado
		 */
		public int factorial(int numero) {
			if (numero < 0) {
				this.display.setText("Introduce numero mayor a 0");
			}
			int resultado = 1;
			for (int i = 1; i <= numero; i++) {
				resultado *= i;
			}
			return resultado;
		}



	
	

}
