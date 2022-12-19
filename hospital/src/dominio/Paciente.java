package dominio;

import java.util.Date;
import java.util.Vector;

import persistencia.Agente;

public class Paciente extends Persona {
	public enum PrioridadPaciente { MILD, SEVERE, VITAL, };
	private PrioridadPaciente prioridad;
	public Ingreso ingreso;
	
	public Paciente(final String DNI) {
		super(DNI);
	}

	public boolean CREATE() throws Exception {
		final Agente a= Agente.getAgente();
		final String columns[]= { "DNI" };
		final String values[]= { dni };

		return a.insert("pacientes", columns, values)== 1;
	}

	public static Persona READ(String DNI) throws Exception {
		final Agente a= Agente.getAgente();
		final String columnas[]= { "DNI", };
		final String valores[]= { DNI, };

		Vector<Vector<Object>> posiblePaciente= a.select("pacientes", columnas, valores);
		
		if (posiblePaciente.size()!= 1) throw new NullPointerException("No se ha encontrado la persona con el DNI buscado.");
		
		return new Paciente((String) posiblePaciente.get(0).get(0));
	}

	public boolean estaOcupadoEn(final Date fechaInicio, final Date fechaFin) throws Exception {
		for (long horaAComprobar= fechaInicio.getTime(); horaAComprobar<= fechaFin.getTime(); horaAComprobar+= 9*60000) {
			try {
				// si se puede leer una cita en esta fecha, es que el paciente ya estaba citado
				Cita.READ(this, new Date(horaAComprobar));
				return true;
			} catch (NullPointerException e) {
				// si no se puede leer una cita, el paciente no esta ocupado
			}
		}
		return false;
	}

	public void setPrioridad(PrioridadPaciente prioridad) {
		this.prioridad= prioridad;
	}

	public PrioridadPaciente getPrioridad() {
		return prioridad;
	}
	
}