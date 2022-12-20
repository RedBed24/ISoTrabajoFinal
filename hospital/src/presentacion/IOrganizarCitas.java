package presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dominio.*;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class IOrganizarCitas extends JFrame {

	private JPanel contentPane; // Formulario
	private JTextField textFieldDNIPaciente; // Campo de texto del DNI del paciente
	private JTextField textFieldDNIDoctor; // Campo de texto del DNI del doctor
	private JTextField textFieldFechaHoraInicial; // Campo de texto de la fecha y hora
	private JTextField textFieldFechaHoraFinal; // Campo de texto de la fecha y hora
	private JTextPane textPaneEstado; // Panel de texto
	
	/**
	 * Creación del formulario
	 */
	
	public IOrganizarCitas() {
		
		// Ventana emergente "IOrganizarCitas"

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null); /* Nos permite colocar los componentes del formulario exactamente donde queramos
									  * aunque no nos permita redimensionar la ventana. */
		setContentPane(contentPane);
		setTitle("IOrganizarCitas");
		setResizable(false); // Nuestra ventana no dispone de mecanismo de redimensi�n por lo que hacemos que no se pueda maximizar.
		setBounds(200, 200, 437, 480); // Dimensiones fijas del formulario al abrirlo.
		setLocationRelativeTo(null); // Tras fijar las dimensiones, hacemos que el formulario se abra en el centro de la pantalla.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra solamente la ventana emergente.
		
		// Etiquetas
		
		JLabel lblDNIPaciente = new JLabel("DNI Paciente");
		lblDNIPaciente.setBounds(50, 30, 100, 16);
		contentPane.add(lblDNIPaciente);
		
		JLabel lblDNIDoctor = new JLabel("DNI Doctor");
		lblDNIDoctor.setBounds(55, 80, 100, 16);
		contentPane.add(lblDNIDoctor);
		
		JLabel lblFechaHora = new JLabel("Fecha y Hora de inicio");
		lblFechaHora.setBounds(20, 130, 150, 36);
		contentPane.add(lblFechaHora);
		
		JLabel lblFormato = new JLabel("Formato de fecha y hora");
		lblFormato.setForeground(Color.GREEN);
		lblFormato.setBounds(40, 160, 150, 36);
		contentPane.add(lblFormato);
		
		JLabel lblEjemploHora = new JLabel("Ejemplo: "+DateFormat.getDateTimeInstance().format(new Date()));
		lblEjemploHora.setForeground(Color.GREEN);
		lblEjemploHora.setBounds(200, 160, 170, 36);
		contentPane.add(lblEjemploHora);
		
		JLabel lblFechaHoraFinal = new JLabel("Fecha y Hora de final");
		lblFechaHoraFinal.setBounds(20, 190, 150, 36);
		contentPane.add(lblFechaHoraFinal);

		JLabel lblPrioridad = new JLabel("Prioridad");
		lblPrioridad.setBounds(58, 240, 100, 16);
		contentPane.add(lblPrioridad);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setForeground(Color.RED);
		lblEstado.setBounds(180, 340, 200, 30);
		lblEstado.setFont(new Font("Arial",3, 20));
		contentPane.add(lblEstado);
		
		// Valores de los atributos correspondientes a los campos de texto
		
		textFieldDNIPaciente = new JTextField();
		textFieldDNIPaciente.setBounds(180, 30, 200, 28);
		textFieldDNIPaciente.setColumns(10);
		contentPane.add(textFieldDNIPaciente);

		textFieldDNIDoctor = new JTextField();
		textFieldDNIDoctor.setBounds(180, 80, 200, 28);
		textFieldDNIDoctor.setColumns(10);
		contentPane.add(textFieldDNIDoctor);
		
		textFieldFechaHoraInicial = new JTextField();
		textFieldFechaHoraInicial.setBounds(180, 130, 200, 28);
		textFieldFechaHoraInicial.setColumns(10);
		contentPane.add(textFieldFechaHoraInicial);
		
		textFieldFechaHoraFinal= new JTextField();
		textFieldFechaHoraFinal.setBounds(180, 195, 200, 28);
		textFieldFechaHoraFinal.setColumns(10);
		contentPane.add(textFieldFechaHoraFinal);
		
		textPaneEstado= new JTextPane();
		textPaneEstado.setEditable(false);
		textPaneEstado.setBounds(6, 370, 407, 60);
		contentPane.add(textPaneEstado);
		
		// Lista desplegable de las prioridades
		
		String[] prioridades = {"", "Leve", "Moderado", "Severo", "Vital"};
		JComboBox<String> listaDesplegablePrioridades = new JComboBox<String>(prioridades);
		contentPane.add(listaDesplegablePrioridades);
		listaDesplegablePrioridades.setBounds(180, 235, 200, 28);
		
		// Bot�n Organizar citas
		
		JButton btnOrganizarCitas = new JButton("Organizar citas");
		btnOrganizarCitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Registra la cita(o no) e informa de la situación.
				try {
					if (!validateDNI(textFieldDNIPaciente.getText())) throw new IllegalArgumentException("Error, el DNI del paciente no cumple el formato de los DNIs.");
					if (!validateDNI(textFieldDNIDoctor.getText())) throw new IllegalArgumentException("Error, el DNI del doctor no cumple el formato de los DNIs.");
					
					final DateFormat d= DateFormat.getDateTimeInstance();
					final Date fechaInicio= d.parse(textFieldFechaHoraInicial.getText());
					final Date fechaFin= d.parse(textFieldFechaHoraFinal.getText());

					if (fechaInicio.after(fechaFin)) throw new IllegalArgumentException("Error, la fecha de inicio está después de la fecha de fin.");
					if (fechaInicio.before(new Date())) throw new IllegalArgumentException("Error, la fecha especificada ya ha pasado.");
					if (fechaFin.getTime()-fechaInicio.getTime() < 10*60000) throw new IllegalArgumentException("Error, la duración de la cita debe ser mayor de 10 minutos.");
					if (fechaFin.getTime()-fechaInicio.getTime() > 60*60000) throw new IllegalArgumentException("Error, la duración de la cita no debe superar la hora.");

					final Paciente.PrioridadPaciente prioridad;
					switch (listaDesplegablePrioridades.getItemAt(listaDesplegablePrioridades.getSelectedIndex())) {
						case "Vital": prioridad= dominio.Paciente.PrioridadPaciente.VITAL; break;
						case "Severo": prioridad= dominio.Paciente.PrioridadPaciente.SEVERO; break;
						case "Moderado": prioridad= dominio.Paciente.PrioridadPaciente.MODERADO; break;
						case "Leve": prioridad=dominio.Paciente.PrioridadPaciente.LEVE; break;
						default: throw new IllegalArgumentException("Error, no se ha introducido una de las posibles prioridades.");
					}

					textPaneEstado.setText(dominio.ControlAdministrativo.organizarCitasAutomatico(textFieldDNIPaciente.getText(), textFieldDNIDoctor.getText(), fechaInicio, fechaFin, prioridad));
				} catch (Exception e) {
					textPaneEstado.setText(e.getMessage());
				}
			}
		});
		btnOrganizarCitas.setBounds(220, 290, 141, 29); // Dimensiones fijas
		contentPane.add(btnOrganizarCitas);

		// Bot�n Limpiar
		
		JButton buttonLimpiar = new JButton("Limpiar");
		buttonLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Se limpian los campos de texto, la lista desplegable y el panel.
				textFieldDNIPaciente.setText("");
				textFieldDNIDoctor.setText("");
				textFieldFechaHoraInicial.setText("");
				textFieldFechaHoraFinal.setText("");
				listaDesplegablePrioridades.setSelectedIndex(0);
				textPaneEstado.setText("");
			}
		});
		buttonLimpiar.setBounds(60, 290, 141, 29); // Dimensiones fijas
		contentPane.add(buttonLimpiar);
	}
	
	private static boolean validateDNI(final String DNI) {
		if (DNI.length()!= 9 || !Character.isUpperCase(DNI.charAt(8))) return false;
		for (int i= 0; i< 8; i++)
			if (!Character.isDigit(DNI.charAt(i)))
				return false;
			
		return true;
	}
}