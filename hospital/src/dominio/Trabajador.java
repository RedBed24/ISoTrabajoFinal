package dominio;

public class Trabajador extends Persona{
	protected String login;
	protected String password;
	protected String email;

	public Trabajador READ(String login, String password) {
		throw new UnsupportedOperationException();
	}
}