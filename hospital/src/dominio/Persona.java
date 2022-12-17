package dominio;

import java.util.Vector;

import persistencia.Agente;

public abstract class Persona {
	protected String nombre;
	protected String apellidos;
	protected String dni;
	protected int edad;
	protected String localidad;
	protected String provincia;
	protected String telefono;
	protected String direccion;

	public boolean CREATE() {
		throw new UnsupportedOperationException();
	}

	public static Persona READ(String DNI) throws Exception {
		final Agente a= Agente.getAgente();
		Vector<Vector<Object>> info= a.select("persona", null, null);
		return null;
	}

	public boolean UPDATE() {
		throw new UnsupportedOperationException();
	}

	public boolean DELETE() {
		throw new UnsupportedOperationException();
	}
}