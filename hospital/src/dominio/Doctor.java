package dominio;

import java.util.Vector;
import dominio.Cita;

/**
 * Debemos tener alg�n tipo de informaci�n para saber cu�ndo un doctor puede ser citado:
 * Ya sea porque todos los doctores tengan un horario fijo y sean las citas quienes indiquen que ese Doctor est� ocupado en esa fecha
 * O porque tengamos un sistema m�s complejo de Agenda personal del doctor
 */
public class Doctor extends PersonalSanitario {
	private String especialidad;
	private String consulta;
	public Vector<Cita> realiza = new Vector<Cita>();

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