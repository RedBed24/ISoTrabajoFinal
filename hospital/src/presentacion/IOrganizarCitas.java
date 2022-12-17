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

public class IOrganizarCitas extends JFrame {

	private JPanel contentPane; // Formulario
	private JTextField textFieldDNIPaciente; // Campo de texto del DNI del paciente
	private JTextField textFieldDNIDoctor; // Campo de texto del DNI del doctor
	private JTextField textFieldFechaHora; // Campo de texto de la fecha y hora
	private JTextField textFieldPrioridad; // Campo de texto de la prioridad del paciente
	
	/**
	 * Creación de la estructura
	 */
	
	public IOrganizarCitas() {
		
		// Ventana emergente "IOrganizarCitas"

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null); /* Nos permite colocar los componentes del formulario exactamente donde queramos
									  * aunque no nos permita redimensionar la ventana. */
		setContentPane(contentPane);
		setTitle("IOrganizarCitas");
		setResizable(false); // Nuestra ventana no dispone de mecanismo de redimensión por lo que hacemos que no se pueda maximizar.
		setBounds(200, 200, 437, 330); // Dimensiones fijas del formulario al abrirlo.
		setLocationRelativeTo(null); // Tras fijar las dimensiones, hacemos que el formulario se abra en el centro de la pantalla.
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solamente la ventana emergente.
		
		// Etiquetas
		
		JLabel lblDNIPaciente = new JLabel("DNI Paciente");
		lblDNIPaciente.setBounds(50, 30, 100, 16);
		contentPane.add(lblDNIPaciente);
		
		JLabel lblDNIDoctor = new JLabel("DNI Doctor");
		lblDNIDoctor.setBounds(55, 80, 100, 16);
		contentPane.add(lblDNIDoctor);
		
		JLabel lblFechaHora = new JLabel("Fecha y Hora");
		lblFechaHora.setBounds(50, 130, 100, 16);
		contentPane.add(lblFechaHora);
		
		JLabel lblPrioridad = new JLabel("Prioridad");
		lblPrioridad.setBounds(58, 180, 100, 16);
		contentPane.add(lblPrioridad);
		
		
		// Valores de los atributos correspondientes a los campos de texto
		
		textFieldDNIPaciente = new JTextField();
		textFieldDNIPaciente.setBounds(180, 30, 200, 28);
		textFieldDNIPaciente.setColumns(10);
		contentPane.add(textFieldDNIPaciente);

		textFieldDNIDoctor = new JTextField();
		textFieldDNIDoctor.setBounds(180, 80, 200, 28);
		textFieldDNIDoctor.setColumns(10);
		contentPane.add(textFieldDNIDoctor);
		
		textFieldFechaHora = new JTextField();
		textFieldFechaHora.setBounds(180, 130, 200, 28);
		textFieldFechaHora.setColumns(10);
		contentPane.add(textFieldFechaHora);
		
		textFieldPrioridad = new JTextField();
		textFieldPrioridad.setBounds(180, 180, 200, 28);
		textFieldPrioridad.setColumns(10);
		contentPane.add(textFieldPrioridad);
		
		// Botón Organizar citas
		
		JButton btnOrganizarCitas = new JButton("Organizar citas");
		btnOrganizarCitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Registra al usuario introducido (o no) e informa de la situación.
				/*try {
					// Se crea un objeto Usuario con lo escrito en las casillas correspondientes al login y al password.
					Trabajador u = new Trabajador(textFieldLogin.getText(), textFieldPassword.getText());
					
					// Introduce el usuario en la base de datos.
					u.insert();
					
					// Si no ha saltado una excepción anteriormente, la cuenta del usuario se ha creado correctamente.
					textPaneEstado.setText("Usuario creado correctamente.");
					
				} catch (InvalidLoginException e) {
					textPaneEstado.setText("No se cumple el mínimo de caracteres en el login. Debe tener al menos 4 caracteres.");
				} catch (InvalidPasswordException e) {
					textPaneEstado.setText("No se cumple el minimo de caracteres en el password. Debe tener al menos 4 caracteres.");
				} catch (Exception e) {
					textPaneEstado.setText("No se ha podido crear el usuario porque ya existe uno con ese login.");
				}*/

			}
		});
		btnOrganizarCitas.setBounds(220, 240, 141, 29); // Dimensiones fijas
		contentPane.add(btnOrganizarCitas);

		// Botón Limpiar
		
		JButton buttonLimpiar = new JButton("Limpiar");
		buttonLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Se limpian los campos de texto y el panel.
				textFieldDNIPaciente.setText("");
				textFieldDNIDoctor.setText("");
				textFieldFechaHora.setText("");
				textFieldPrioridad.setText("");
			}
		});
		buttonLimpiar.setBounds(60, 240, 141, 29); // Dimensiones fijas
		contentPane.add(buttonLimpiar);
	}
}
