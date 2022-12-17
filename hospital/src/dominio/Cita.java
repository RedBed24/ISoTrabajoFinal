package dominio;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Vector;

import persistencia.Agente;

public class Cita {
	/* En la base de datos se guardará:
	 * fechaYhoraInicial; fechaYHoraFinal; diagnostico; DNIpaciente; DNIdoctor
	 */
	private Date fechaYHoraInicial;
	private Date fechaYHoraFinal;
	private String diagnostico;
	public Paciente pacienteCitado;
	public Doctor doctor;

	public Cita(final Date fechaYHoraInicial, final Date fechaYHoraFinal, final String DNIpaciente, final Doctor doctor) throws Exception {
		super();
		this.fechaYHoraInicial = fechaYHoraInicial;
		this.fechaYHoraFinal = fechaYHoraFinal;
		this.pacienteCitado= (Paciente) Paciente.READ(DNIpaciente);
		this.doctor= doctor;
	}

	public void setDiagnostico(final String diagnostico) {
		this.diagnostico= diagnostico;
	}

	public boolean CREATE() {
		throw new UnsupportedOperationException();
	}

	public static Cita READ(final String DNIpaciente, final Doctor doctor) throws Exception {
		final Agente a= Agente.getAgente();

		final String columns[]= {
				"DNIpaciente", // TODO: Usar constantes, pero dónde ponemos las constantes ???
				"DNIdoctor",
		};
		final String values[]= {
				DNIpaciente,
				doctor.getDNI(),
		};
		// para poder pasar String con fechas a Date
		final DateFormat d= DateFormat.getDateInstance();
		
		// leemos todas las citas asociadas a este doctor y este paciente
		Vector<Vector<Object>> posiblesCitas= a.select("citas", columns, values);
		
		// obtenemos la fechaYHoraActual para poder saber qué cita de todas las posibles es la que se está llevando a cabo
		final Date horaActual= new Date();

		Date horaInicial;
		Date horaFinal;

		for (int i= 0; i< posiblesCitas.size(); i++) {
			// ambas se parsean a una instancia Date
			// la hora inicial se guarda en la primera columna
			horaInicial= d.parse((String) posiblesCitas.get(i).get(0));
			// la hora final se guarda en la segunda columna
			horaFinal= d.parse((String) posiblesCitas.get(i).get(1));
			
			// se compara con la actual, si esta está entre medias
			if (horaActual.compareTo(horaInicial)== 1 && horaActual.compareTo(horaFinal)== -1)
				// la cita i es la cita actual
				return new Cita(
						horaInicial,
						horaFinal,
						// DNI paciente
						(String) posiblesCitas.get(i).get(3),
						doctor
				);
		}


		throw new NullPointerException("No se ha encontrado la cita buscada.");
	}

	public boolean UPDATE() throws Exception {
		final Agente a= Agente.getAgente();
		
		final String columnasACambiar[]= { "diagnostico", };
		final String valoresACambiar[] = {  diagnostico , };

		final String columnsIdentificativas[]= {
				// recordemos que un paciente y un doctor pueden estar asignados a varias citas
				"fechaYHoraInicial",
				"DNIpaciente",
				"DNIdoctor",
		};
		final String identificacion[]= {
				fechaYHoraFinal.toString(),
				pacienteCitado.getDNI(),
				doctor.getDNI(),
		};
		
		return a.update("citas", columnasACambiar, valoresACambiar, columnsIdentificativas, identificacion)== 1 ? true : false;
	}

	public boolean DELETE() throws SQLException, Exception {
		final Agente a= Agente.getAgente();

		final String columnsIdentificativas[]= {
				// recordemos que un paciente y un doctor pueden estar asignados a varias citas
				"fechaYHoraInicial",
				"DNIpaciente",
				"DNIdoctor",
		};
		final String identificacion[]= {
				fechaYHoraFinal.toString(),
				pacienteCitado.getDNI(),
				doctor.getDNI(),
		};
		
		return a.delete("citas", columnsIdentificativas, identificacion)== 1 ? true : false;
	}

	@Override
	public String toString() {
		return "Cita [fechaYHoraInicial=" + fechaYHoraInicial + ", fechaYHoraFinal=" + fechaYHoraFinal
				+ ", diagnostico=" + diagnostico + ", pacienteCitado=" + pacienteCitado + ", doctor=" + doctor + "]";
	}

}
