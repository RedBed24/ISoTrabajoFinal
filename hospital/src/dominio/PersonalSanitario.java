package dominio;

/**
 * Cuando un personal sanitario no es una instancia de Doctor, se considera un enfermero
 */
public class PersonalSanitario extends Trabajador {
	
	public PersonalSanitario(final String DNI) {
		super(DNI);
	}

}