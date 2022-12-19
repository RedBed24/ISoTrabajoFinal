package dominio;

public abstract class Persona {
	protected final String dni;

	public Persona(final String DNI) {
		this.dni= DNI;
	}

	public boolean CREATE() throws Exception {
		throw new UnsupportedOperationException();
	}

	public static Persona READ(String DNI) throws Exception {
		throw new UnsupportedOperationException();
	}

	public String getDNI() {
		return dni;
	}
}
	