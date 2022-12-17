import java.text.DateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

import persistencia.Agente;

public class Main implements persistencia.BDConstantes {	

	private final static Scanner TECLADO= new Scanner(System.in);

	public static void main(String[] args) {
		try {
			menú();
		} catch (Exception e) {
			System.err.println("Ha ocurrido un error inesperado: "+ e);
		}
	}
	
	private static void menú() {
		while (true) {
			try {

				Agente a= Agente.getAgente();
				// las columnas deberían ser constantes que contengan cada clase
				String columns2[]= {
						"DNIdoctor",
						"DNIpaciente",
				};
				String values2[]= {
						"05735354M",
						"00000000A",
				};
					
				System.out.println("Elige una opción: 1 Fechas, 2 insert, 3 update, 4 delete, 5 select");
				switch (TECLADO.nextInt()) {
				case 0: return;
				case 1:
					DateFormat r= DateFormat.getDateTimeInstance();
					Date antiguaDate= r.parse("14 dic 2022 19:23:44");
					
					// para saber si estamos en una cita, debemos comprobar si la fecha actual está entre el inicio y el final de una cita
					if (antiguaDate.compareTo(new Date())== -1 && antiguaDate.compareTo(new Date())== 1)
						System.out.println("Entre medias, por tanto, antiguaDate se refiere a una cita");
					break;

				case 2:
					// antes de hacer un insert, sería necesario comprobar que no exista ya uno
					String columns[]= {
							"date",
							columns2[0],
							columns2[1],
					};

					String values[]= {
							new Date().toString(),
							values2[0],
							values2[1],
					};
					System.out.println(a.insert(NOMBRETABLACITAS, columns, values)); 
					break;

				case 3:
					String columnaAEditar[]= { "diagnostico", };
					String valorAEditar[]= { "Este es el diagnóstico de un paciente", };
					System.out.println(a.update(NOMBRETABLACITAS, columnaAEditar, valorAEditar, columns2, values2)); 
					break;
					
				case 4:
					System.out.println(a.delete(NOMBRETABLACITAS, columns2, values2));
					break;
					
				case 5:
					Vector<Object> vec= a.select(NOMBRETABLACITAS, columns2, values2).elementAt(0);
					System.out.println((String)vec.elementAt(0)+" "+(String)vec.elementAt(1)+" "+(String)vec.elementAt(2)+" "+(String)vec.elementAt(3));
					
					break;

				default:
					System.err.println("El número introducido no está dentro del rango esperado.");
				}
			} catch (InputMismatchException e) {
				System.err.println("Error, se esperaba un número.");
				TECLADO.next();
			} catch (Exception e) {
				// realmente este catch no debería de estar, es un place holder
				e.printStackTrace();
			}
		}
	}

}
