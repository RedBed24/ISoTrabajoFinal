package dominio;

import persistencia.Agente;

public class Ingreso {
	public Paciente ingresado;
	
	public Ingreso(final Paciente paciente) {
		this.ingresado= paciente;
	}
	
	public boolean CREATE() throws Exception {
		final Agente a= Agente.getAgente();
		final String columns[]= { "DNIingresado" };
		final String values[]= { ingresado.getDNI() };

		return a.insert("ingresos", columns, values)== 1;
	}

}
