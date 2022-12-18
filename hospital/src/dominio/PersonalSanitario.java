package dominio;

import java.util.Vector;

/**
 * Cuando un personal sanitario no es una instancia de Doctor, se considera un enfermero
 */
public class PersonalSanitario extends Trabajador {
	// informe
	public Vector<String> realiza = new Vector<String>();
	
	public PersonalSanitario(final String DNI) {
		super(DNI);
	}

	/**
	 * Paciente paciente= READ pacientes WHERE DNI='DNIpaciente'
	 * Si no existe paciente
	 *         Exception "No existe el paciente especificado"
	 * 
	 * paciente.actualizarHistorial(nuevaEntrada)
	 * 
	 * devolver "Se ha actualizado correctamente el historial del paciente"
	 */
	public String actualizarHistorialClinico(String DNIpaciente, String descripcion) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Paciente paciente = read paciente where DNI=DNIpaciente
	 * Paciente existe?
	 * 
	 * Informe informe= new Informe(descripcion, paciente, this);
	 * 
	 * informe.CREATE()
	 * 
	 * mostrar "informe creado: " +informe.toString()
	 */
	public String crearInformeRutinario(String DNIpaciente, String descripcion) {
		throw new UnsupportedOperationException();
	}

	/**
	 * listainformes.add(informe);
	 */
	public void asignar(String informe) {
		throw new UnsupportedOperationException();
	}
}