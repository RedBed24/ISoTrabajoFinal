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
	
	public Persona(final String DNI) {
		this.dni= DNI;
	}

	public boolean CREATE() {
		throw new UnsupportedOperationException();
	}

	public static Persona READ(String DNI) throws Exception {
		final Agente a= Agente.getAgente();
		@SuppressWarnings("unused")
		Vector<Vector<Object>> info= a.select("persona", null, null);
		return null;
	}

	public boolean UPDATE() {
		throw new UnsupportedOperationException();
	}

	public boolean DELETE() {
		throw new UnsupportedOperationException();
	}

	public String getDNI() {
		return dni;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + "]";
	}
	
}
	