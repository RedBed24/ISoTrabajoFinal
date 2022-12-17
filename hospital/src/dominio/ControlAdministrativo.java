package dominio;

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

	/**
	 * String estado; 
	 * try {
	 * Paciente paciente= READ pacientes WHERE DNI='DNIpaciente' 
	 * Si no existe paciente 
	 *         paciente= crear nuevo paciente con solo su DNI
	 * Si paciente.ocupado(fecha) 
	 *         Exteption "El paciente ya cuenta con una cita en la fecha" 
	 * paciente.setPrioridad(prioridad) 
	 * 
	 * if prioridad== Vital 
	 *         ingreso= new Ingreso(paciente); 
	 *         paciente.asignar(ingreso);
	 *         VitalException 
	 * 
	 * Doctor doctor= READ doctores WHERE DNI='DNIdoctor' 
	 * Si no existe doctor 
	 *         Exception "Doctor no encontrado" 
	 * 
	 * Si doctor.ocupado(fecha) 
	 *         Exception "Doctor ocupado en la fecha especificada" 
	 * 
	 * Cita cita= crear Cita(fecha, paciente, doctor) 
	 * 
	 * Asignar cita a doctor 
	 * Asignar cita a paciente 
	 * 
	 * } catch (VitalException) { 
	 *         estado= "Se debe 
	 * } catch (Exception e) { 
	 *         estado= e.mesage; 
	 * } 
	 * devolver estado
	 */
	public String organizarCitasAutomotico(String DNIpaciente, String DNIDoctor, String fecha, Paciente.PrioridadPaciente prioridad) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Le pedir� a Paciente la operaci�n READ con el DNI especificado y est� devolver� los datos de este paciente, (crearemos el paciente) y se le actualiza los datos de direcci�n o tel�fono (el que no sea null ya que uno de los dos ser� null siempre).
	 */
	public String obtenerMedioComunicacion(String DNIpaciente) {
		throw new UnsupportedOperationException();
	}
}