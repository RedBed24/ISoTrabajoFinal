package dominio;

import java.util.Date;

public class ControlAdministrativo {

	/**
	 * Paciente paciente= READ pacientes WHERE DNI='DNIpaciente' 
	 * Si no existe paciente 
	 *         Exception "No existe el paciente especificado" 
	 * 
	 * paciente.actualizarDatosAdministrativos(info) 
	 * 
	 * devolver "Datos a�adidos satisfactoriamente"
	 */
	public String actualizarDatosPaciente(String DNIpaciente, String[] info) {
		throw new UnsupportedOperationException();
	}

	public static String organizarCitasAutomatico(final String DNIpaciente, final String DNIDoctor, final Date fechaInicio, final Date fechaFin, Paciente.PrioridadPaciente prioridad) {
		try {
			Paciente paciente;
			try {
				paciente= (Paciente) Paciente.READ(DNIpaciente);
			} catch (NullPointerException e) {
				System.out.println("El paciente no existía, se ha añadido automáticamente a la base de datos. Será necesario añadir información administrativa.");
				paciente= new Paciente(DNIpaciente);
				paciente.CREATE();
			}
			if (paciente.estaOcupadoEn(fechaInicio, fechaFin)) throw new IllegalArgumentException("El paciente está ocupado durante la fecha especificada.");
			
			paciente.setPrioridad(prioridad);

			if (paciente.getPrioridad()== Paciente.PrioridadPaciente.VITAL) { 
				  //Ingreso ingreso= new Ingreso(paciente); 
				  //paciente.asignar(ingreso);
			}

			// class cast exception,
			final Doctor doctor= (Doctor) Doctor.READ(DNIDoctor);

			if (doctor.estaOcupadoEn(fechaInicio, fechaFin)) throw new IllegalArgumentException("El doctor está ocupado durante la fecha especificada.");

			final Cita cita= new Cita(fechaInicio, fechaFin, paciente, doctor);
			if (!cita.CREATE()) return "No se ha podido guardar la cita en la base de datos...";
			
			return "Se ha guardado la cita correctamente";

		} catch (Exception e) { 
			return e.getMessage();
		} 
	}

	/**
	 * Le pedir� a Paciente la operaci�n READ con el DNI especificado y est� devolver� los datos de este paciente, (crearemos el paciente) y se le actualiza los datos de direcci�n o tel�fono (el que no sea null ya que uno de los dos ser� null siempre).
	 */
	public String obtenerMedioComunicacion(String DNIpaciente) {
		throw new UnsupportedOperationException();
	}
}