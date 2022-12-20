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
	private JTextPane textPaneDiagnostico; // Panel de texto para el diagnóstico
	private JTextPane textPaneEstado; // Panel de texto para el estado

	/**
	 * Creaci�n del formulario
	 */

	public IDiagnosticar(final Doctor doctor) {
		
		// Ventana emergente "IDiagnosticar"
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);  /* Nos permite colocar los componentes del formulario exactamente donde queramos
									   * aunque no nos permita redimensionar la ventana. */
		setContentPane(contentPane);
		setTitle("IDiagnosticar");
		setResizable(false); // Nuestra ventana no dispone de mecanismo de redimensi�n por lo que hacemos que no se pueda maximizar.
		setBounds(200, 200, 437, 600); // Dimensiones fijas del formulario al abrirlo.
		setLocationRelativeTo(null); // Tras fijar las dimensiones, hacemos que el formulario se abra en el centro de la pantalla.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra solamente la ventana emergente
		
		// Etiquetas
		
		JLabel lblDiagnostico = new JLabel("Diagnostico");
		lblDiagnostico.setForeground(Color.RED);
		lblDiagnostico.setBounds(160, 10, 200, 30);
		lblDiagnostico.setFont(new Font("Arial",3, 20));
		contentPane.add(lblDiagnostico);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setForeground(Color.RED);
		lblEstado.setBounds(180, 390, 200, 30);
		lblEstado.setFont(new Font("Arial",3, 20));
		contentPane.add(lblEstado);
		
		// Valores de los atributos correspondientes al panel
			
		textPaneDiagnostico = new JTextPane();
		textPaneDiagnostico.setToolTipText("Esta informacion sera una nueva entrada del historial clinico del paciente");
		textPaneDiagnostico.setEditable(true);
		textPaneDiagnostico.setBounds(6, 50, 407, 330);
		contentPane.add(textPaneDiagnostico);

		textPaneEstado= new JTextPane();
		textPaneEstado.setEditable(false);
		textPaneEstado.setBounds(6, 420, 407, 60);
		contentPane.add(textPaneEstado);
		
		// Botón Diagnosticar
		
		JButton btnDiagnosticar = new JButton("Diagnosticar");
		btnDiagnosticar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) { // Añaade el diagnóstico a la cita
				try {
					textPaneEstado.setText(doctor.diagnosticar(textPaneDiagnostico.getText()));
				} catch (Exception e) {
					textPaneEstado.setText(e.getMessage());
				}
			}
		});
		btnDiagnosticar.setBounds(260, 515, 141, 29); // Dimensiones fijas
		contentPane.add(btnDiagnosticar);
		
		// Botón Cancelar
		
		JButton btnCancelar = new JButton("Cancelar la cita");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					textPaneEstado.setText(doctor.cancelarDiagnostico());
				} catch (Exception e) {
					textPaneEstado.setText(e.getMessage());
				}
			}
		});
		btnCancelar.setBounds(40, 515, 141, 29); // Dimensiones fijas
		contentPane.add(btnCancelar);
	}
}