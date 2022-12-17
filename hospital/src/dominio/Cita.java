package dominio;

import java.util.Date;

public class Cita {
	private String[] diagnostico;
	private Date fechaYHora;
	private long duracion;
	public Paciente tiene;
	public Doctor realiza;

	public Cita(String[] diagnostico, String fechaYHora, String duracion) {
		throw new UnsupportedOperationException();
	}

	public void setDiagnostico(String diagnostico) {
		throw new UnsupportedOperationException();
	}

	/**
	 * return descripcion+fecha+horas+especialidad+pacienteCitado
	 * // qu� informaci�n sobre el paciente citado le debemos dar?
	 * @return 
	 */
	public String toString() {
		throw new UnsupportedOperationException();
	}

	public boolean CREATE() {
		throw new UnsupportedOperationException();
	}

	public Cita READ(String fechaYHora, String DNIPaciente) {
		throw new UnsupportedOperationException();
	}

	public boolean UPDATE() {
		throw new UnsupportedOperationException();
	}

	public boolean DELETE() {
		throw new UnsupportedOperationException();
	}

	public void operation() {
		throw new UnsupportedOperationException();
	}
}
