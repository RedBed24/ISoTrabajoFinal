package dominio;

import java.util.Date;
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

	public String diagnosticar(final String diagnostico) throws Exception {
		final Cita citaActual= Cita.READ(this, new Date());

		citaActual.setDiagnostico(diagnostico);
		
		return citaActual.UPDATE() ? "Operación realizada satisfactoriamente" : "Error al actualizar la cita.";
	}

	public String cancelarDiagnostico() throws Exception {
		final Cita citaActual= Cita.READ(this, new Date());
		
		return citaActual.DELETE() ? "Operación realizada satisfactoriamente" : "Error al borrar la cita." ;
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

	public boolean estaOcupadoEn(final Date fechaInicio, final Date fechaFin) throws Exception {
		try {
			// si se puede leer una cita en esta fecha, es que el paciente ya estaba citado
			Cita.READ(this, fechaInicio);
			Cita.READ(this, fechaFin);
			return true;
		} catch (NullPointerException e) {
			// si no se puede leer una cita, el paciente no esta ocupado
			return false;
		}
	}

}