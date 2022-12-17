import java.util.InputMismatchException;
import java.util.Scanner;

import dominio.Doctor;
import dominio.Trabajador;

public class Main implements persistencia.BDConstantes {	

	private final static Scanner TECLADO= new Scanner(System.in);

	public static void main(String[] args) {
		try {
			// instancia del trabajador obtenida por el login
			final Trabajador trabajador= Trabajador.READ("pepito", "flores");

			if (trabajador instanceof Doctor) menúDoctor((Doctor)trabajador);
			else menúAdministrativo(trabajador);

		} catch (Exception e) {
			System.err.println("Ha ocurrido un error inesperado: "+ e);
		}
	}

	private static void menúAdministrativo(Trabajador trabajador) {
		
	}

	private static void menúDoctor(final Doctor doctor) throws Exception {
		while (true) {
			try {
				System.out.println("0 salir. 1 Diagnosticar paciente 2 Cancelar Diagnóstico");
				switch (TECLADO.nextInt()) {
				case 0: return;
				case 1: {
					System.out.println("Introduce el DNI del paciente: ");
					final String DNIpaciente= TECLADO.next();
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
			} catch (InputMismatchException e) {
				System.err.println("Error, se esperaba un número.");
				TECLADO.next();
			}
		}
	}

}
