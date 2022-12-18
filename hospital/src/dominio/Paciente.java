package dominio;

public class Paciente extends Persona {
	public enum PrioridadPaciente { VITAL, };
	private PrioridadPaciente prioridad;
	private String historialClinico;
	public Ingreso esta;

	public Paciente(PrioridadPaciente prioridad, String historial) {
		super("DNI");
		throw new UnsupportedOperationException();
	}

	public String actualizarDatosAdministrativos(String[] info) {
		throw new UnsupportedOperationException();
	}

	/**
	 * historialCl�nico+= entrada;
	 * 
	 * Update() en nuestra base de datos
	 * Update()  en el SESCAM
	 */
	public void actualizarHistorial(String entrada) {
		throw new UnsupportedOperationException();
	}

	public boolean estaOcupadoEn(String fechayHora) {
		throw new UnsupportedOperationException();
	}

	public String cuandoEstaOcupado() {
		throw new UnsupportedOperationException();
	}
}