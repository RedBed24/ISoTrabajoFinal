package presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dominio.*;
import excepciones.*;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IDiagnosticar extends JFrame {

	private JPanel contentPane; // Formulario 
	private JTextPane textPaneEstado; // Panel de texto

	/**
	 * Creaci�n del formulario
	 */

	public IDiagnosticar(final Doctor doctor) {
		
		// Ventana emergente "Eliminar un usuario registrado"
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);  /* Nos permite colocar los componentes del formulario exactamente donde queramos
									   * aunque no nos permita redimensionar la ventana. */
		setContentPane(contentPane);
		setTitle("IDiagnosticar");
		setResizable(false); // Nuestra ventana no dispone de mecanismo de redimensi�n por lo que hacemos que no se pueda maximizar.
		setBounds(200, 200, 437, 600); // Dimensiones fijas del formulario al abrirlo.
		setLocationRelativeTo(null); // Tras fijar las dimensiones, hacemos que el formulario se abra en el centro de la pantalla.
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solamente la ventana emergente
		
		// Etiquetas
		
		JLabel lblEstado = new JLabel("Diagnostico");
		lblEstado.setForeground(Color.RED);
		lblEstado.setBounds(160, 10, 200, 30);
		lblEstado.setFont(new Font("Arial",3, 20));
		contentPane.add(lblEstado);
		
		// Valores de los atributos correspondientes al panel
			
		textPaneEstado = new JTextPane();
		textPaneEstado.setToolTipText("Esta informacion sera una nueva entrada del historial clinico del paciente");
		textPaneEstado.setEditable(true);
		textPaneEstado.setBounds(6, 50, 407, 450);
		contentPane.add(textPaneEstado);
		
		// Bot�n Diagnosticar
		
		JButton btnDiagnosticar = new JButton("Diagnosticar");
		btnDiagnosticar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) { // Elimina al usuario (o no) e informa de la situaci�n.
				try {
					doctor.diagnosticar(textPaneEstado.getText());
				} catch (Exception e) {
					// TODO: necesito mostrar la info de esto
					System.err.println(e);
				}
			}
		});
		btnDiagnosticar.setBounds(260, 515, 141, 29); // Dimensiones fijas
		contentPane.add(btnDiagnosticar);
		
		// Bot�n Cancelar
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					doctor.cancelarDiagnostico();
				} catch (Exception e) {
					// TODO: lo mismo
					System.err.println(e);
				}
			}
		});
		btnCancelar.setBounds(40, 515, 141, 29); // Dimensiones fijas
		contentPane.add(btnCancelar);
	}
}
