package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;





public class Engine implements ActionListener{
	// componentes básicos principales
	private JFrame frame;
	private JPanel contentPanel;// panel que ocupa toda la ventana
	private JPanel displeyPanel;// panel norte que contiene el display
	private JPanel buttonPanel;// panel sur que contiene los botones
	private JTextField display;// display
	
	private JPanel basepanel;//
	private JTextField infoPanel;
	private JPanel marcaPanel;
	// Botones de la calculadora v1
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
	//botones calculadora v2
	private JButton A;
	private JButton B;
	private JButton C;
	private JButton D;
	private JButton E;
	private JButton F;
	private JButton hexBoton;
	private JButton decBoton;
	private JButton octBoton;
	private JButton binBoton;
	private JButton casio;
	private JButton info;
	private JButton owner;
	
	
	
	//variable para controlar la base actual
	private int BaseActual;//por defecto decimal
	

	// tipos de boton: númericos o de operación
	private enum ButtonType {
		REGULAR, OPERATOR,BASE,HEXADECIMAL, EXTRAS, MARCA
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
			
			this.basepanel= new JPanel();
			this.infoPanel = new JTextField("¿En qué base desea operar?",30);
			this.marcaPanel= new JPanel();
	
			
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
			//botones version 2
			this.hexBoton= new JButton("HEX");
			this.decBoton = new JButton("DEC");
			this.binBoton= new JButton("BIN");
			this.octBoton= new JButton("OCT");
			this.info = new JButton("INFO");
			this.owner= new JButton("OWNER");
			this.casio= new JButton("CASIO");
			this.A= new JButton("A");
			this.B= new JButton("B");
			this.C= new JButton("C");
			this.D= new JButton("D");
			this.E= new JButton("E");
			this.F= new JButton("F");
			//Estilo botones
			setDisplayAndPanelStyles();
				
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
			this.displeyPanel.setLayout(new BorderLayout());
				
			this.buttonPanel.setLayout(new GridLayout(8,4, 5, 5));// cuadrícula de 4 filas y 8 columnas
			// configurar el campo del texto del display
			this.basepanel.setLayout(new BorderLayout());
			this.display.setFont(new Font("Arial", Font.BOLD,30));
			this.displeyPanel.add(this.display);
			//panel superior donde se muestra la marca y la info 
			this.marcaPanel.add(this.casio);
			this.basepanel.add(this.marcaPanel,BorderLayout.EAST);
			this.basepanel.add(this.infoPanel,BorderLayout.WEST);
			
		
			// añadir botones al panel de botones fila 1
			this.buttonPanel.add(this.binBoton);
			this.buttonPanel.add(this.octBoton);
			this.buttonPanel.add(this.decBoton);
			this.buttonPanel.add(this.hexBoton);
			//fila 2
			this.buttonPanel.add(this.A);
			this.buttonPanel.add(this.B);
			this.buttonPanel.add(this.C);
			this.buttonPanel.add(this.info);
			//fila3
			this.buttonPanel.add(this.D);
			this.buttonPanel.add(this.E);
			this.buttonPanel.add(this.F);
			this.buttonPanel.add(this.owner);
			//fila 4
			this.buttonPanel.add(n7);
			this.buttonPanel.add(n8);
			this.buttonPanel.add(n9);
			this.buttonPanel.add(this.resta);
			//fila 5	
			this.buttonPanel.add(n4);
			this.buttonPanel.add(n5);
			this.buttonPanel.add(n6);
			this.buttonPanel.add(this.suma);
			//fila6
			this.buttonPanel.add(n1);
			this.buttonPanel.add(n2);
			this.buttonPanel.add(n3);
			this.buttonPanel.add(this.multiplica);
			//fila 7
			this.buttonPanel.add(n0);
			this.buttonPanel.add(this.divide);
			this.buttonPanel.add(this.porcentaje);
			this.buttonPanel.add(this.elevado);
			//fila 8
			this.buttonPanel.add(this.igual);	
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
			
			setFeaturesButton(this.A,ButtonType.HEXADECIMAL);
			setFeaturesButton(this.B,ButtonType.HEXADECIMAL);
			setFeaturesButton(this.C,ButtonType.HEXADECIMAL);
			setFeaturesButton(this.D,ButtonType.HEXADECIMAL);
			setFeaturesButton(this.E,ButtonType.HEXADECIMAL);
			setFeaturesButton(this.F,ButtonType.HEXADECIMAL);
			
			setFeaturesButton(this.hexBoton,ButtonType.BASE);
			setFeaturesButton(this.octBoton,ButtonType.BASE);
			setFeaturesButton(this.binBoton,ButtonType.BASE);
			setFeaturesButton(this.decBoton,ButtonType.BASE);
			
			setFeaturesButton(this.casio,ButtonType.MARCA);
			setFeaturesButton(this.info,ButtonType.EXTRAS);
			setFeaturesButton(this.owner,ButtonType.EXTRAS);
			// Añadir los paneles a la ventana principal
			
			
		
			this.contentPanel.add(this.basepanel,BorderLayout.NORTH);
			this.contentPanel.add(this.displeyPanel,BorderLayout.CENTER);
			this.contentPanel.add(this.buttonPanel,BorderLayout.SOUTH);

			// Configurar las propiedades de la ventana
			this.frame.setContentPane(contentPanel);
			this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// cerrar la aplicación al cerrar la ventana
			this.frame.setSize(600, 800);// tamaño de la ventana
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
			//base comun para todos los botones
			_button.setPreferredSize(new Dimension(35,55));
			_button.setFocusPainted(false);//quita el borde del focus
			_button.setBorder(BorderFactory.createRaisedBevelBorder());//borde efecto 3D
			_button.setFont(new Font("Segoe UI", Font.BOLD, 20)); // Fuente  moderna
			//_button.setFont(new Font("Arial", Font.BOLD, 20));// fuente grande y en negrita
			switch(_type) {
			case REGULAR:
				_button.setBackground( new Color(236,240,241));//fondo gris para numeros	
				break;
			case OPERATOR:
				_button.setBackground(new Color(119,136,153));
				_button.setForeground(Color.white);
				break;
			case BASE:
				_button.setBackground(new Color(52, 152, 219)); // Azul real	
				_button.setForeground(Color.white);
				
			    // Efecto hover
	            _button.addMouseListener(new java.awt.event.MouseAdapter() {
	                public void mouseEntered(java.awt.event.MouseEvent evt) {
	                    _button.setBackground(new Color(30, 70, 190));
	                }
	                public void mouseExited(java.awt.event.MouseEvent evt) {
	                    _button.setBackground(new Color(65, 105, 225));
	                }
	            });
				break;
			case HEXADECIMAL:
				_button.setBackground(new Color(132,206,250));	
				_button.setForeground(Color.white);	
				break;
			case EXTRAS:
				_button.setBackground(new Color(255,182,193));	
				//_button.setForeground(Color.white);
				break;
			case MARCA:
				_button.setPreferredSize(new Dimension(80,40));
				_button.setBackground(new Color (44,62,80));
				_button.setForeground(new Color(236,240,241));
				 _button.setFont(new Font("Arial Black", Font.BOLD, 16));
				break;		
			}
			// Efecto de presionado para todos los botones
		    _button.addMouseListener(new java.awt.event.MouseAdapter() {
		        public void mousePressed(java.awt.event.MouseEvent evt) {
		            _button.setBorder(BorderFactory.createLoweredBevelBorder());
		        }
		        public void mouseReleased(java.awt.event.MouseEvent evt) {
		            _button.setBorder(BorderFactory.createRaisedBevelBorder());
		        }
		    });
		}
		/**
		 * metodo que establece el estilo del display y los paneles 
		 */
		public void setDisplayAndPanelStyles() {
		    // Estilo del display
		    this.display.setBackground(new Color(236, 240, 241)); // Fondo claro
		    this.display.setForeground(new Color(44, 62, 80));    // Texto oscuro   
		    // Estilo del panel de información
		    this.infoPanel.setBackground(new Color(189,195,199));
		    this.infoPanel.setForeground(new Color(44, 62, 80));
		    this.infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		    this.infoPanel.setFont( new Font("Arial", Font.BOLD,15));
		    
		    // Estilo de los paneles
		    this.contentPanel.setBackground(new Color(189, 195, 199));
		    this.displeyPanel.setBackground(new Color(189, 195, 199));
		    this.buttonPanel.setBackground(new Color(189, 195, 199));
		    this.basepanel.setBackground(new Color(189, 195, 199));
		    this.marcaPanel.setBackground(new Color(189, 195, 199));
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
			this.hexBoton.addActionListener(this);
			this.octBoton.addActionListener(this);
			this.binBoton.addActionListener(this);
			this.decBoton.addActionListener(this);
			this.A.addActionListener(this);
			this.B.addActionListener(this);
			this.C.addActionListener(this);
			this.D.addActionListener(this);
			this.E.addActionListener(this);
			this.F.addActionListener(this);
			this.info.addActionListener(this);
			this.casio.addActionListener(this);
			this.owner.addActionListener(this);
			
		}
		/**
		 * Convierte un numero de la base actua a decimal
		 * @param numero numero en la base actual a convertir
		 * @return numero convertido a decimal(base10)
		 */
		public int convertirADecimal(String numero, int baseOrigen) {
			switch(this.BaseActual) {
			case 2://binaroi
				return Integer.parseInt(numero,2);
			case 8://octal
				return Integer.parseInt(numero,8);
			case 16://hexadecimal
				return Integer.parseInt(numero,16);
			default://decimal
				return Integer.parseInt(numero,10);
			}
		}
		/**
		 * Metodo que convierte un numero decimal a la base numerica especificada 
		 * @param numero Numero decimal a convertir
		 * @param baseDestino
		 * @return Cadena que representa el numero en la base destino
		 */
		public String convertirABase(int numero, int baseDestino) {
	        switch (baseDestino) {
	            case 2:
	                return Integer.toBinaryString(numero).toUpperCase();
	            case 8:
	                return Integer.toOctalString(numero).toUpperCase();
	            case 16:
	                return Integer.toHexString(numero).toUpperCase();
	            default:
	                return String.valueOf(numero);
	        }
	    }		
		/**
		 * Convierte el resultado decimal a la base actual selecionada
		 * @param resultado resultado en decimal a convertir
		 * @return String con el numero convertido a la base actual
		 */
		public String convertirABaseActual(int resultado) {
			switch(this.BaseActual) {
			case 2:
				 return Integer.toBinaryString(resultado);
			case 8:
				 return Integer.toOctalString(resultado);
			case 16:
				 return Integer.toHexString(resultado);
			default:
				return  String.valueOf(resultado);
			}
		}

		/**
		 * Metodo que realiza las operaciones matemáticas en función del operador: suma(+), resta(-),etc
		 * el metodo primero convierte los numeros a decimal, realiza la operacion y luego convierte de vuelta a la base acts
		 */
		public void operacion() {
		    String texto = this.display.getText();
		    String regex = "(-?[0-9A-Fa-f]+)([%+-x/^])(-?[0-9A-Fa-f]+)";
		    Pattern pattern = Pattern.compile(regex);
		    Matcher matcher = pattern.matcher(texto);

		    if (matcher.matches()) {
		        
		            // Convertir los números de la base actual a decimal para operar
		            this.num1 = convertirADecimal(matcher.group(1), this.BaseActual);
		            this.operacion = matcher.group(2).charAt(0);
		            this.num2 = convertirADecimal(matcher.group(3),this.BaseActual);
		            
		            // Realizar la operación
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
		                    if (this.num2 == 0) {
		                        this.display.setText("No se puede dividir por 0");
		                        return;
		                    }
		                    this.resultado = this.num1 / this.num2;
		                    break;
		                case '%':
		                    this.resultado = (this.num1 * this.num2) / 100;
		                    break;
		                case '^':
		                    this.resultado = (int) Math.pow(this.num1, this.num2);
		                    break;
		            }
		            
		            // Convertir el resultado a la base actual antes de mostrarlo
		            String resultadoEnBaseActual = convertirABaseActual(this.resultado);
		            this.display.setText(resultadoEnBaseActual.toUpperCase()); // Convertir a mayúsculas para hexadecimal
		      
		    } else {
		        this.display.setText("Error de formato");
		    }
		}

		/**
		 * Metodo que cambia de base y escribe en el texfield de info la base en la que se esta operando
		 * @param nuevaBase
		 */
		public void setBase(int nuevaBase) {
			this.BaseActual = nuevaBase;
			
			String baseText="";
			switch(nuevaBase){
				case 2:
					baseText="Base: BINARIO- números binarios(0-1)";		
					break;
				case 8:
					baseText="Base: Octal Ingrese números octales(0-7)";
					break;
				case 10:
					baseText="Base: Decimal Ingese números decimales(0-9)";
					break;
				case 16:
					baseText="Base: Hexadecimal Ingrese número hexadecimal(0-9,A-F)";
				break;		
			}
			this.infoPanel.setText(baseText);//añase el texto a infoPanel
			
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

		
			// boton "C" borra limpia el display
			if (source == this.borra) {
				this.display.setText("");
				this.num1 = 0;
				this.num2 = 0;
				this.resultado = 0;
				this.operacion = ' ';
			} else if (source == this.igual) {// botón "="
			    operacion();
			} else if (source == this.botonRetroceso) {// borra uno a uno los números u operadores
				if (!this.display.getText().isEmpty()) {// si el display no esta vacio
					this.display.setText(this.display.getText().substring(0, this.display.getText().length() - 1));
				}
			} else if (source == this.factorial) {// boton (!) para calcular el factorial de un numero entero
				int numero = Integer.parseInt(display.getText());
				int resultado = factorial(numero);
				this.display.setText(String.valueOf(resultado));
			} else if ( source==this.casio) {//boton casio cuando lo pulsas te lleva  a la pagina oficial de casio
				try {
					Desktop.getDesktop().browse(new URI("https://www.casio.com/es/scientific-calculators/"));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			} else if(source==this.info){
				String mensaje="Calculadora cietífica";
				new VentanaEmergente(mensaje).setVisible(true);
				
			}else if (source == this.owner) {
				String mensaje ="Desarrollado por:Mayra Barrantes Pi\nContacto: https://github.com/mayrabpi";
				new VentanaEmergente(mensaje).setVisible(true);
			}else if (source== this.binBoton|| source == this.octBoton|| source==this.hexBoton|| source==this.decBoton) {
				
				//obtener el texto actual 
                String resultadoActual= this.display.getText();
				
				//si hay texto en la pantalla
				if(!resultadoActual.isEmpty()&&!resultadoActual.contains("+")
						&&!resultadoActual.contains("-")&&!resultadoActual.contains("x")
						&&!resultadoActual.contains("/")&&!resultadoActual.contains("%")&&!resultadoActual.contains("%")&&!resultadoActual.contains("^")) {
					//detectar la base actual de un número
					int baseOrigen = detectarBaseActual(resultadoActual);
					
					//convertimos a decimal
					int numeroDecimal = convertirADecimal(resultadoActual,baseOrigen);//por parametro el resulatdo actual y la base en la está
					//botones cambio de base
				 if(source == this.binBoton) {
					setBase(2);
					this.display.setText(convertirABase(numeroDecimal,2));
				}else if (source==this.octBoton) {
					setBase(8);
					this.display.setText(convertirABase(numeroDecimal,8));
				}else if (source ==this.hexBoton) {
					setBase(16);
					this.display.setText(convertirABase(numeroDecimal,16));
				}else if(source==this.decBoton) {
					setBase(10);
					this.display.setText(convertirABase(numeroDecimal,10));
					
				}
			 }else {
					if (source == this.binBoton) setBase(2);
		            else if (source == this.octBoton) setBase(8);
		            else if (source == this.hexBoton) setBase(16);
		            else if (source == this.decBoton) setBase(10);
				}
				
			
			}else if (source instanceof JButton) {// para cualquier otro boton añade el texto al display
				if(esEntradaValidaParaBaseActual(input_text)) {
				this.display.setText(this.display.getText() + input_text);
				}
			}
		}
		private int detectarBaseActual(String resultadoActual) {

			return 0;
		}
		/**
		 * verifica si la entrada es valida para la base numerica actual 
		 * @param entrada texto del boton presionado
		 * @return true si la entrada es valida para la base actual, false en caso contrario
		 */
		public boolean esEntradaValidaParaBaseActual(String entrada) {
			if("+-x/%^=C".contains(entrada)) {
				return true;
			}
			switch(this.BaseActual) {
			case 2://solo permite 01 para binario
				return entrada.matches("[01]");
			case 8://solo permite digitos del 0 al 7 para octal
				return entrada.matches("[0-7]");
			case 16://permite digitos y letras A-F para hexadecimal
				return entrada.matches("[0-9A-Fa-f]");
			default://solo permite digitos para decimal
				return entrada.matches("[0-9]");
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
/*else {// para cualquier otro boton añade el texto al display
				this.display.setText(this.display.getText() + input_text);
			}*/
