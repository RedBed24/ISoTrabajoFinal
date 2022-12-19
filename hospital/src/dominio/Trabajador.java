package dominio;

import java.util.Vector;

import persistencia.Agente;

public class Trabajador extends Persona{
	protected String login;
	protected String password;
	
	public Trabajador(final String DNI) {
		super(DNI);
	}

	public static Trabajador READ(final String login, final String password) throws Exception {
		final Agente a= Agente.getAgente();

		final String columns[]= {
				"login",
				"password",
		};
		final String values[]= {
				login,
				password,
		};
		
		// obtenemos los posiblesTrabajadores
		Vector<Vector<Object>> posiblesTrabajadores= a.select("trabajadores", columns, values);
		
		if (posiblesTrabajadores.size()!= 1) throw new NullPointerException("No se ha encontrado el trabajador con login: \""+login+"\", o no concuerda la contraseña.");
		
		//                                    la columna -v es la que tiene el tipo de trabajador
		switch (((String) posiblesTrabajadores.get(0).get(3)).toLowerCase()) {
		case "doctor": 
			return new Doctor((String) posiblesTrabajadores.get(0).get(0));
		case "administrativo":
			return new Trabajador((String) posiblesTrabajadores.get(0).get(0));
		default:
			throw new IllegalArgumentException("No se encuentra el tipo de trabajador: " + (String)posiblesTrabajadores.get(0).get(3)+", contacte con el adminsitrador de la base de datos.");
		}
	}
}