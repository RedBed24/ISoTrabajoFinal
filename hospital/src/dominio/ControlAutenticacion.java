package dominio;

public class ControlAutenticacion {

	/**
	 * READ Trabajador con Login 
	 * Comprobar que trabajador tiene el password correspondiente 
	 * devolverlo
	 */
	public static Trabajador autenticar(final String login, final String password) throws Exception {
		return Trabajador.READ(login, password);
	}
}