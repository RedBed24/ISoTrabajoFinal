package dominio;

import java.util.Date;
import java.util.Vector;

import persistencia.Agente;

public class Paciente extends Persona {
	public enum PrioridadPaciente { MILD, SEVERE, VITAL, };
	private PrioridadPaciente prioridad;
	private String historialClinico;
	public Ingreso esta;
	
	public Paciente(final String DNI) {
		super(DNI);
	}

	public Paciente(PrioridadPaciente prioridad, String historial) {
		super("DNI");
		throw new UnsupportedOperationException();
	}

	public static Persona READ(String DNI) throws Exception {
		final Agente a= Agente.getAgente();
		final String columnas[]= { "DNI", };
		final String valores[]= { DNI, };

		Vector<Vector<Object>> posiblePaciente= a.select("pacientes", columnas, valores);
		
		if (posiblePaciente.size()!= 1) throw new NullPointerException("No se ha encontrado el trabajador buscado.");
		
		return new Paciente((String) posiblePaciente.get(0).get(0));
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

	public boolean estaOcupadoEn(final Date fechaInicio, final Date fechaFin) throws Exception {
		try {
			// si se puede leer una cita en esta fecha, es que el paciente ya estaba citado
			// realmente puede existir una cita entre medias si hacemos que las citas tengan una longitud mínima, no
			Cita.READ(this, fechaInicio);
			Cita.READ(this, fechaFin);
			return true;
		} catch (NullPointerException e) {
			// si no se puede leer una cita, el paciente no esta ocupado
			return false;
		}
	}

	public String cuandoEstaOcupado() {
		throw new UnsupportedOperationException();
	}

	public void setPrioridad(PrioridadPaciente prioridad) {
		this.prioridad= prioridad;
	}

	public PrioridadPaciente getPrioridad() {
		return prioridad;
	}
	
}