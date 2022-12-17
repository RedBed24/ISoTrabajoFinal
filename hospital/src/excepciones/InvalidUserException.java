package excepciones;

/**
 * @name InvalidUserException
 * 
 * @description Excepción que indica que no se ha creado el usuario correctamente. Por ejemplo,
 * 				cuando se introduce un login o un password de menos de 4 caracteres.
 * 
 */

public class InvalidUserException extends Exception {

	public InvalidUserException() {
	}

	public InvalidUserException(String message) {
		super(message);
	}

	public InvalidUserException(Throwable cause) {
		super(cause);
	}

	public InvalidUserException(String message, Throwable cause) {
		super(message, cause);
	}

}
