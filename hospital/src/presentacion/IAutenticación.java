package presentacion;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dominio.*;
import excepciones.*;

import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class IAutenticación extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldLogin;
	private JTextField textFieldPassword;
	private JTextPane textPaneEstado;

	/** 
	 * Arranca la aplicación
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IAutenticación frame = new IAutenticación();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creación del formulario
	 */
	
	public IAutenticación() {
		
		// Ventana principal de la aplicación
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null); /* Nos permite colocar los componentes del formulario exactamente donde queramos
		   							  * aunque no nos permita redimensionar la ventana. */
		
		setContentPane(contentPane);
		setTitle(" IAutenticación ");
		setResizable(false); // Nuestra ventana no dispone de mecanismo de redimensión por lo que hacemos que no se pueda maximizar.
		setBounds(200, 200, 435, 375); // Dimensiones fijas del formulario al abrirlo.
		setLocationRelativeTo(null); // Tras fijar las dimensiones, hacemos que el formulario se abra en el centro de la pantalla.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación.
		
		// Etiquetas

		JLabel lblIntroduzcaElLogin = new JLabel("Introduzca el login y el password para acceder al sistema");
		lblIntroduzcaElLogin.setBounds(35, 19, 386, 43);
		lblIntroduzcaElLogin.setFont(new Font("Arial",3, 13));
		contentPane.add(lblIntroduzcaElLogin);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(90, 72, 69, 16);
		contentPane.add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(75, 120, 69, 16);
		contentPane.add(lblPassword);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setForeground(Color.RED);
		lblEstado.setBounds(180, 215, 69, 16);
		lblEstado.setFont(new Font("Arial",3, 20));
		contentPane.add(lblEstado);
		
		// // Valores de los atributos correspondientes a los campos de texto y el panel
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(150, 68, 190, 28);
		textFieldLogin.setColumns(10);
		contentPane.add(textFieldLogin);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(150, 116, 190, 28);
		textFieldPassword.setColumns(10);
		contentPane.add(textFieldPassword);
			
		textPaneEstado = new JTextPane();
		textPaneEstado.setToolTipText("Panel para mostrar el restultado de la comprobación de login o las excepciones lanzadas");
		textPaneEstado.setEditable(false);
		textPaneEstado.setBounds(7, 240, 410, 90);
		contentPane.add(textPaneEstado);

		// Botón Iniciar sesión
		
		JButton buttonAceptar = new JButton("Iniciar sesión");
		buttonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Autentica al usuario e informa de la situación dada.
				//try {
					// Se crea un objeto Usuario con lo escrito en las casillas correspondientes al login y al password.
					/*Trabajador t = new Trabajador(textFieldLogin.getText(), textFieldPassword.getText());
					
					// Comprueba si el usuario está en la base de datos e informa de la situación que se dé.
					if (t.read()) {
						textPaneEstado.setText("El login ha sido correcto.");
					} else {
						textPaneEstado.setText("El login ha sido incorrecto, puesto que no se ha encontrado registrado o no tiene esa contraseña.");
					}
					
				} catch (InvalidLoginException e) {
					textPaneEstado.setText("No se cumple el mínimo de caracteres en el login. Debe tener al menos 4 caracteres.");
				} catch (InvalidPasswordException e) {
					textPaneEstado.setText("No se cumple el mínimo de caracteres en el password. Debe tener al menos 4 caracteres.");
				} catch (Exception e) {
					textPaneEstado.setText("Ha ocurrido un error inesperado. Vuelva a intentarlo.");
				}*/
				// Abre la ventana emergente "Organizar Citas"
					IOrganizarCitas frameCitas = new IOrganizarCitas();
					frameCitas.setVisible(true);
					// Abre la ventana emergente "Diagnosticar al paciente"
					IDiagnosticar frameDiagnosticar = new IDiagnosticar();
					frameDiagnosticar.setVisible(true);
			}
		});
		buttonAceptar.setBounds(250, 170, 140, 29); // Dimensiones fijas
		contentPane.add(buttonAceptar);

		// Botón Limpiar
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {  // Se limpian los campos de texto y el panel.
				textPaneEstado.setText("");
				textFieldLogin.setText("");
				textFieldPassword.setText("");
			}
		});
		btnLimpiar.setBounds(40, 170, 140, 29); // Dimensiones fijas
		contentPane.add(btnLimpiar);
		
		/*
		// Botón Nuevo Usuario
		
		JButton btnNuevoUsuario = new JButton("Organizar citas");
		btnNuevoUsuario.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) { // Abre la ventana emergente "Dar de alta a un nuevo usuario"
				IOrganizarCitas frame = new IOrganizarCitas();
				frame.setVisible(true);
			}
		});
		btnNuevoUsuario.setBounds(264, 200, 140, 50); // Dimensiones fijas
		contentPane.add(btnNuevoUsuario);

		// Botón Eliminar Usuario
		
		JButton btnEliminarUsuario = new JButton("Diagnosticar al paciente");
		btnEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Abre la ventana emergente "Eliminar un usuario registrado"
				IDiagnosticar frame = new IDiagnosticar();
				frame.setVisible(true);
			}
		});
		btnEliminarUsuario.setBounds(264, 270, 140, 50); // Dimensiones fijas
		contentPane.add(btnEliminarUsuario);
		*/
	}

}
