package dominio;

import java.sql.SQLException;
import java.util.Vector;

/**
 * Debemos tener alg�n tipo de informaci�n para saber cu�ndo un doctor puede ser citado:
 * Ya sea porque todos los doctores tengan un horario fijo y sean las citas quienes indiquen que ese Doctor est� ocupado en esa fecha
 * O porque tengamos un sistema m�s complejo de Agenda personal del doctor
 */
public class Doctor extends PersonalSanitario {
	private String especialidad;
	private String consulta;
	public Vector<Cita> realiza = new Vector<Cita>();
	
	public Doctor(final String DNI) {
		super(DNI);
	}

	public String diagnosticar(final String DNIpaciente, final String diagnostico) throws Exception {
		try {
			final Cita citaActual= Cita.READ(DNIpaciente, this);

			citaActual.setDiagnostico(diagnostico);
			
			if (citaActual.UPDATE())
				return "Operación realizada satisfactoriamente";

			return "Error al actualizar la cita.";
		} catch (SQLException e) {
			return "Ha ocurrido un error con la base de datos.";
		} catch (NullPointerException e) {
			return e.getMessage();
		}
	}

	public String cancelarDiagnostico(String DNIpaciente) throws Exception {
		try {
			final Cita citaActual= Cita.READ(DNIpaciente, this);
			
			if (citaActual.DELETE()) 
				return "Operación realizada satisfactoriamente";

			return "Error al borrar la cita.";
		} catch (SQLException e) {
			return "Ha ocurrido un error con la base de datos.";
		} catch (NullPointerException e) {
			return e.getMessage();
		}
	}

	/**
	 * Informaci�nADevolver
	 * Para cada Cita
	 *         Informaci�nADevolver+= Cita.toString()
	 * devolver Informaci�nADevolver
	 */
	public String informacionDetalladaCitas() {
		throw new UnsupportedOperationException();
	}

	public String cuandoEstoOcupado() {
		throw new UnsupportedOperationException();
	}

	public boolean estoOcupadoEn(String fechayHora) {
		throw new UnsupportedOperationException();
	}

}