package dominio;

import java.util.Date;

public class ControlAdministrativo {
	public static String organizarCitasAutomatico(final String DNIpaciente, final String DNIDoctor, final Date fechaInicio, final Date fechaFin, Paciente.PrioridadPaciente prioridad) throws Exception {
		boolean nuevoPaciente= false;
		Paciente paciente;
		try {
			paciente = (Paciente) Paciente.READ(DNIpaciente);
		} catch (NullPointerException e) {
			paciente = new Paciente(DNIpaciente);
			paciente.CREATE();
			nuevoPaciente= true;
		}
		
		paciente.setPrioridad(prioridad);

		if (paciente.getPrioridad() == Paciente.PrioridadPaciente.VITAL) { 
			final Ingreso nuevoIngreso = new Ingreso(paciente);
			nuevoIngreso.CREATE();
			return "Se ha creado un nuevo ingreso"+ (nuevoPaciente ? ", Además, se ha añadido el paciente a la base de datos." : ".");
		}

		if (paciente.estaOcupadoEn(fechaInicio, fechaFin)) throw new IllegalArgumentException("El paciente está ocupado durante la fecha especificada.");

		final Doctor doctor = (Doctor) Doctor.READ(DNIDoctor);

		if (doctor.estaOcupadoEn(fechaInicio, fechaFin)) throw new IllegalArgumentException("El doctor está ocupado durante la fecha especificada"+ (nuevoPaciente ? ", Además, se ha añadido el paciente a la base de datos." : "."));

		final Cita cita = new Cita(fechaInicio, fechaFin, paciente, doctor);
		return cita.CREATE() ? "Se ha guardado la cita correctamente" : "No se ha podido guardar la cita en la base de datos..."+ (nuevoPaciente ? ", Además, se ha añadido el paciente a la base de datos." : ".");
	}
}