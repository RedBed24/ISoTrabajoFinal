import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import dominio.ControlAdministrativo;
import dominio.ControlAutenticacion;
import dominio.Doctor;
import dominio.Paciente;
import dominio.Paciente.PrioridadPaciente;
import dominio.Trabajador;

public class Main {	

	private final static Scanner TECLADO= new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			try {
				// instancia del trabajador obtenida por el login
				System.out.println("Registrate:");
				final Trabajador trabajador= ControlAutenticacion.autenticar(TECLADO.nextLine(), TECLADO.nextLine());

				if (trabajador instanceof Doctor) menúDoctor((Doctor)trabajador);
				else menúAdministrativo();

			} catch (NullPointerException e) {
				System.err.println(e.getMessage());
			} catch (NumberFormatException e) {
				System.err.println("Error, se esperaba un número.");
			} catch (ParseException e) {
				System.err.println("Error, la fecha introducida no tiene el formato esperado.");
			} catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
			} catch (SQLException e) {
				System.err.println("Ha ocurrido un error con la base de datos.");
			} catch (Exception e) {
				System.err.println("Ha ocurrido un error inesperado: "+ e);
			}
		}
	}

	private static void menúAdministrativo() throws ParseException {
		System.out.println("Menú administrativo:");
		System.out.println("Introduce el DNI del paciente:");
		final String DNIpaciente= TECLADO.nextLine();
		System.out.println("Introduce el DNI del doctor:");
		final String DNIdoctor= TECLADO.nextLine();

		final DateFormat d= DateFormat.getDateTimeInstance();
		System.out.println("Fecha de ejemplo: " + d.format(new Date()));
		System.out.println("Introduce la fecha de inicio de la cita:");
		final Date fechaInicio= d.parse(TECLADO.nextLine());
		System.out.println("Introduce la fecha de fin de la cita:");
		final Date fechaFin= d.parse(TECLADO.nextLine());
		
		if (fechaInicio.after(fechaFin)) throw new IllegalArgumentException("Error, la fecha de inicio está tras la fecha de fin.");
		
		System.out.println("Introduce la prioridad del paciente:");
		final Paciente.PrioridadPaciente prioridad;
		switch (TECLADO.nextLine().toLowerCase()) {
		case "vital": prioridad= PrioridadPaciente.VITAL; break;
		case "severe": prioridad= PrioridadPaciente.SEVERE; break;
		case "mild": prioridad= PrioridadPaciente.MILD; break;
		default: throw new IllegalArgumentException("Error, no se ha introducido una de las posibles prioridades.");
		}

		System.out.println(ControlAdministrativo.organizarCitasAutomotico(DNIpaciente, DNIdoctor, fechaInicio, fechaFin, prioridad));
	}

	private static void menúDoctor(final Doctor doctor) throws Exception {
		System.out.println("Menú doctor:");
		System.out.println("0 salir. 1 Diagnosticar paciente 2 Cancelar Diagnóstico");
		switch (Integer.parseInt(TECLADO.nextLine())) {
		case 0: return;
		case 1:
			System.out.println("Introduce el diagnóstico:");
			System.out.println(doctor.diagnosticar(TECLADO.nextLine()));
			break;
		case 2:
			System.out.println(doctor.cancelarDiagnostico());
			break;
		default: System.err.println("El número introducido no está dentro del rango esperado.");
		}
	}

}
