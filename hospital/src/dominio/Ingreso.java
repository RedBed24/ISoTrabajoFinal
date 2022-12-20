package dominio;

import persistencia.Agente;
import persistencia.BDConstantes;

public class Ingreso implements BDConstantes {
	public Paciente ingresado;
	
	public Ingreso(final Paciente paciente) {
		this.ingresado = paciente;
	}
	
	public boolean CREATE() throws Exception {
		final Agente a = Agente.getAgente();
		final String columns[] = { "DNIingresado" };
		final String values[] = { ingresado.getDNI() };

		return a.insert(NOMBRE_TABLA_INGRESOS, columns, values) == 1;
	}

}