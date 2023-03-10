package dominio;

import java.util.Date;
import java.util.Vector;

import persistencia.Agente;

public class Doctor extends PersonalSanitario {
	
	public Doctor(final String DNI) {
		super(DNI);
	}

	public String diagnosticar(final String diagnostico) throws Exception {
		final Cita citaActual = Cita.READ(this, new Date());

		citaActual.setDiagnostico(diagnostico);
		
		return citaActual.UPDATE() ? "Diagnóstico añadido satisfactoriamente" : "Error al actualizar la cita";
	}

	public String cancelarDiagnostico() throws Exception {
		final Cita citaActual= Cita.READ(this, new Date());
		
		return citaActual.DELETE() ? "Cita eliminada satisfactoriamente" : "Error al eliminar la cita" ;
	}

	public static Persona READ(final String DNI) throws Exception {
		final Agente a = Agente.getAgente();
		final String columnas[] = { "DNI", };
		final String valores[] = { DNI, };

		final Vector<Vector<Object>> posiblesDoctor = a.select(NOMBRE_TABLA_TRABAJADORES, columnas, valores);
		
		if (posiblesDoctor.size( ) != 1) throw new NullPointerException("No se ha encontrado el doctor buscado.");
		
		if (!((String) posiblesDoctor.get(0).get(3)).equalsIgnoreCase("doctor")) throw new IllegalArgumentException("El DNI "+DNI+" no es de un doctor.");

		return new Doctor((String) posiblesDoctor.get(0).get(0));
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

}