package presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dominio.*;
import excepciones.*;

import javax.swing.JLabel;
import javax.swing.JTextField;
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
	 * Creación del formulario
	 */

	public IDiagnosticar() {
		
		// Ventana emergente "Eliminar un usuario registrado"
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);  /* Nos permite colocar los componentes del formulario exactamente donde queramos
									   * aunque no nos permita redimensionar la ventana. */
		setContentPane(contentPane);
		setTitle("IDiagnosticar");
		setResizable(false); // Nuestra ventana no dispone de mecanismo de redimensión por lo que hacemos que no se pueda maximizar.
		setBounds(200, 200, 437, 600); // Dimensiones fijas del formulario al abrirlo.
		setLocationRelativeTo(null); // Tras fijar las dimensiones, hacemos que el formulario se abra en el centro de la pantalla.
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solamente la ventana emergente
		
		// Etiquetas
		
		JLabel lblEstado = new JLabel("Diagnóstico");
		lblEstado.setForeground(Color.RED);
		lblEstado.setBounds(160, 10, 200, 30);
		lblEstado.setFont(new Font("Arial",3, 20));
		contentPane.add(lblEstado);
		
		// Valores de los atributos correspondientes al panel
			
		textPaneEstado = new JTextPane();
		textPaneEstado.setToolTipText("Esta información será una nueva entrada del historial clínico del paciente");
		textPaneEstado.setEditable(true);
		textPaneEstado.setBounds(6, 50, 407, 450);
		contentPane.add(textPaneEstado);
		
		// Botón Diagnosticar
		
		JButton btnDiagnosticar = new JButton("Diagnosticar");
		btnDiagnosticar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) { // Elimina al usuario (o no) e informa de la situación.
				/*try {
					// Se crea un objeto Usuario con lo escrito en las casillas correspondientes al login y al password.
					Doctor u = new Doctor(textFieldLogin.getText(), textFieldPassword.getText());
					
					// Elimina al usuario (o no) de la base de datos e informa en el panel de texto la situación que se dé.
					if(u.eliminar())
						textPaneEstado.setText("Usuario eliminado correctamente");
					else
						textPaneEstado.setText("No se ha podido eliminar el usuario ya que no se encuentra registrado o no tiene esa contraseña.");
							
				} catch (InvalidLoginException e) {
					textPaneEstado.setText("No se cumple el mínimo de caracteres en el login, debe tener al menos 4 caracteres.");
				} catch (InvalidPasswordException e) {
					textPaneEstado.setText("No se cumple el mínimo de caracteres en el password, debe tener al menos 4 caracteres.");
				} catch (Exception e) {
					textPaneEstado.setText("No se ha podido eliminar el usuario por una razón inesperada.");
				}*/
				
			}
		});
		btnDiagnosticar.setBounds(260, 515, 141, 29); // Dimensiones fijas
		contentPane.add(btnDiagnosticar);
		
		// Botón Cancelar
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 // Cierra solamente la ventana emergente
			}
		});
		btnCancelar.setBounds(40, 515, 141, 29); // Dimensiones fijas
		contentPane.add(btnCancelar);
	}
}
