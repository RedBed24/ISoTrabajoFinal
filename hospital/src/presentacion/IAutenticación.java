package presentacion;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dominio.*;

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
		setTitle(" IAutenticacion ");
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
		textPaneEstado.setEditable(false);
		textPaneEstado.setBounds(7, 240, 410, 90);
		contentPane.add(textPaneEstado);

		// Botón Iniciar sesión
		
		JButton buttonAceptar = new JButton("Iniciar sesion");
		buttonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Autentica al usuario e informa de la situación dada.
				try {
					final Trabajador trabajador = ControlAutenticacion.autenticar(textFieldLogin.getText(), textFieldPassword.getText());

					if (trabajador instanceof Doctor) {
						IDiagnosticar frameDiagnosticar = new IDiagnosticar((Doctor)trabajador);
						frameDiagnosticar.setVisible(true);
					} else {
						IOrganizarCitas frameCitas = new IOrganizarCitas();
						frameCitas.setVisible(true);
					}
					setVisible(false);
				} catch (Exception e) {
					textPaneEstado.setText(e.getMessage());
				}
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
		
	}

}