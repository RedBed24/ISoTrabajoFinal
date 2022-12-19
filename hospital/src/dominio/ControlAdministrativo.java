package dominio;

import java.util.Date;

public class ControlAdministrativo {
	public static String organizarCitasAutomotico(final String DNIpaciente, final String DNIDoctor, final Date fechaInicio, final Date fechaFin, Paciente.PrioridadPaciente prioridad) throws Exception {
		Paciente paciente;
		try {
			paciente= (Paciente) Paciente.READ(DNIpaciente);
		} catch (NullPointerException e) {
			System.out.println("El paciente no existía, se ha añadido automáticamente a la base de datos. Será necesario añadir información administrativa.");
			paciente= new Paciente(DNIpaciente);
			paciente.CREATE();
		}
		
		paciente.setPrioridad(prioridad);

		if (paciente.getPrioridad()== Paciente.PrioridadPaciente.VITAL) { 
			final Ingreso nuevoIngreso= new Ingreso(paciente);
			nuevoIngreso.CREATE();
			return "Se ha creado un nuevo ingreso";
		}

		if (paciente.estaOcupadoEn(fechaInicio, fechaFin)) throw new IllegalArgumentException("El paciente está ocupado durante la fecha especificada.");

		final Doctor doctor= (Doctor) Doctor.READ(DNIDoctor);

		if (doctor.estaOcupadoEn(fechaInicio, fechaFin)) throw new IllegalArgumentException("El doctor está ocupado durante la fecha especificada.");

		final Cita cita= new Cita(fechaInicio, fechaFin, paciente, doctor);
		return cita.CREATE() ? "Se ha guardado la cita correctamente" : "No se ha podido guardar la cita en la base de datos...";
	}
}