package dominio;

public class ControlAutenticacion {

	public static Trabajador autenticar(final String login, final String password) throws Exception {
		return Trabajador.READ(login, password);
	}
}