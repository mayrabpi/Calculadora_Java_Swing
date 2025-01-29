package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
/**
 * Clase Ventanaemergente que crea una ventana de gialogo con un mensaje de información
 */
public class VentanaEmergente extends JDialog {
	/**
	 * Constructor de la clase ventana emergente 
	 * @param mensaje el mensaje que se mostrar en la ventana emergente
	 */
    public VentanaEmergente(String mensaje) {
        // Configurar la ventana
        setTitle("Información");
        setModal(true);
        
        // Panel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Area de texto con el mensaje
        JTextArea textArea = new JTextArea(mensaje);
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);//ajuste de palabra
        textArea.setLineWrap(true);//si una linea no cabe en se creará otra linea
       // textArea.setBackground(new Color(240, 240, 240));
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        
        // Botón de cerrar
        JButton closeButton = new JButton("Cerrar");
        botoncerrar(closeButton);
     
        
        panel.add(textArea, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);
        
        setContentPane(panel);
        setSize(300, 200);
        setLocationRelativeTo(null);
    }
    /**
     * Metodo actionListener para cerrar la ventana cuando pulsa boton cerrar 
     * @param button
     */
    public void botoncerrar(JButton button) {
    	button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//método que cierra la ventana
				dispose();
			
				
			}
    		
    	});
    }
  
}

