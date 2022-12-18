import java.text.DateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import dominio.Doctor;
import dominio.Trabajador;

public class Main implements persistencia.BDConstantes {	

	private final static Scanner TECLADO= new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			try {
				// instancia del trabajador obtenida por el login
				final Trabajador trabajador= Trabajador.READ("samuel", "espejo");

				if (trabajador instanceof Doctor) menúDoctor((Doctor)trabajador);
				else menúAdministrativo(trabajador);

			} catch (NullPointerException e) {
				System.err.println(e.getMessage());
			} catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.err.println("Ha ocurrido un error inesperado: "+ e);
			}
		}
	}

	private static void menúAdministrativo(Trabajador trabajador) {
		throw new UnsupportedOperationException("El menú del adiminstativo todavía no está preparado.");
	}

	private static void menúDoctor(final Doctor doctor) throws Exception {
		while (true) {
			try {
				//System.out.println("La hora actual es: "+ DateFormat.getDateTimeInstance().format(new Date().toString()));
				System.out.println("0 salir. 1 Diagnosticar paciente 2 Cancelar Diagnóstico");
				switch (Integer.parseInt(TECLADO.nextLine())) {
				case 0: return;
				case 1: {
					System.out.println("Introduce el DNI del paciente: ");
					final String DNIpaciente= TECLADO.nextLine();
					System.out.println("Introduce el diagnóstico: ");
					System.out.println(doctor.diagnosticar(DNIpaciente, TECLADO.nextLine()));
					break;
				}
				case 2: {
					System.out.println("Introduce el DNI del paciente: ");
					final String DNIpaciente= TECLADO.next();
					System.out.println(doctor.cancelarDiagnostico(DNIpaciente));
					break;
				}
				default:
					System.err.println("El número introducido no está dentro del rango esperado.");
				}
			} catch (NumberFormatException e) {
				System.err.println("Error, se esperaba un número.");
				TECLADO.next();
			}
		}
	}

}
