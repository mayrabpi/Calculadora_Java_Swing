package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class VentanaEmergente extends JDialog {
    public VentanaEmergente(String mensaje) {
        // Configurar la ventana
        setTitle("Información");
        setModal(true);
        
        // Panel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Área de texto con el mensaje
        JTextArea textArea = new JTextArea(mensaje);
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setBackground(new Color(240, 240, 240));
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Botón de cerrar
        JButton closeButton = new JButton("Cerrar");
        closeButton.addActionListener(e -> dispose());
        
        panel.add(textArea, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);
        
        setContentPane(panel);
        setSize(300, 200);
        setLocationRelativeTo(null);
    }
}

