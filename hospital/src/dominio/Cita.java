package dominio;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Vector;

import persistencia.Agente;

public class Cita {
	/* En la base de datos se guardará:
	 * fechaYhoraInicial; fechaYhoraFinal; diagnostico; DNIpaciente; DNIdoctor
	 */
	private Date fechaYHoraInicial;
	private Date fechaYHoraFinal;
	private String diagnostico;
	public Paciente pacienteCitado;
	public Doctor doctor;
	
	public Cita(final Date fechaYHoraInicial, final Date fechaYHoraFinal, final Paciente paciente, final Doctor doctor) {
		super();
		this.fechaYHoraInicial = fechaYHoraInicial;
		this.fechaYHoraFinal = fechaYHoraFinal;
		this.pacienteCitado= paciente;
		this.doctor= doctor;
	}

	public Cita(final Date fechaYHoraInicial, final Date fechaYHoraFinal, final String DNIpaciente, final Doctor doctor) throws Exception {
		super();
		this.fechaYHoraInicial = fechaYHoraInicial;
		this.fechaYHoraFinal = fechaYHoraFinal;
		this.pacienteCitado= (Paciente) Paciente.READ(DNIpaciente);
		this.doctor= doctor;
	}

	public Cita(final Date fechaYHoraInicial, final Date fechaYHoraFinal, final String DNIdoctor, final Paciente paciente) throws Exception {
		super();
		this.fechaYHoraInicial = fechaYHoraInicial;
		this.fechaYHoraFinal = fechaYHoraFinal;
		this.pacienteCitado= paciente;
		this.doctor= (Doctor) Doctor.READ(DNIdoctor);
	}

	public void setDiagnostico(final String diagnostico) {
		this.diagnostico= diagnostico;
	}

	public boolean CREATE() throws Exception {
		final Agente a= Agente.getAgente();
		final DateFormat d= DateFormat.getDateTimeInstance();
		
		final String columns[]= {
				"fechaYhoraInicial",
				"fechaYhoraFinal",
				"DNIpaciente",
				"DNIdoctor",
		};
		final String values[]= {
				d.format(fechaYHoraInicial),
				d.format(fechaYHoraFinal),
				pacienteCitado.getDNI(),
				doctor.getDNI(),
		};

		return a.insert("citas", columns, values)== 1;
	}
	
	public static Cita READ(final Paciente paciente, final Date hora) throws Exception {
		final Agente a= Agente.getAgente();

		final String columns[]= {
				"DNIpaciente",
		};
		final String values[]= {
				paciente.getDNI(),
		};
		// para poder pasar String con fechas a Date
		final DateFormat d= DateFormat.getDateTimeInstance();
		
		// leemos todas las citas asociadas a este doctor y este paciente
		Vector<Vector<Object>> posiblesCitas= a.select("citas", columns, values);

		Date horaInicial, horaFinal;

		for (int i= 0; i< posiblesCitas.size(); i++) {
			// ambas se parsean a una instancia Date
			// la hora inicial se guarda en la primera columna
			horaInicial= d.parse((String) posiblesCitas.get(i).get(0));
			// la hora final se guarda en la segunda columna
			horaFinal= d.parse((String) posiblesCitas.get(i).get(1));
			
			// se compara con la actual, si esta está entre medias
			if (hora.after(horaInicial) && hora.before(horaFinal))
				// la cita i es la cita actual
				return new Cita(
						horaInicial,
						horaFinal,
						// DNI paciente
						(String) posiblesCitas.get(i).get(4),
						paciente
				);
		}
		throw new NullPointerException("No se ha encontrado la cita buscada.");
	}

	public static Cita READ(final Doctor doctor, final Date hora) throws Exception {
		final Agente a= Agente.getAgente();

		final String columns[]= {
				"DNIdoctor",
		};
		final String values[]= {
				doctor.getDNI(),
		};
		// para poder pasar String con fechas a Date
		final DateFormat d= DateFormat.getDateTimeInstance();
		
		// leemos todas las citas asociadas a este doctor y este paciente
		Vector<Vector<Object>> posiblesCitas= a.select("citas", columns, values);

		Date horaInicial, horaFinal;

		for (int i= 0; i< posiblesCitas.size(); i++) {
			// ambas se parsean a una instancia Date
			// la hora inicial se guarda en la primera columna
			horaInicial= d.parse((String) posiblesCitas.get(i).get(0));
			// la hora final se guarda en la segunda columna
			horaFinal= d.parse((String) posiblesCitas.get(i).get(1));
			
			// se compara con la actual, si esta está entre medias
			if (hora.after(horaInicial) && hora.before(horaFinal))
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
				DateFormat.getDateTimeInstance().format(fechaYHoraInicial),
				pacienteCitado.getDNI(),
				doctor.getDNI(),
		};
		
		return a.update("citas", columnasACambiar, valoresACambiar, columnsIdentificativas, identificacion)== 1;
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
				DateFormat.getDateTimeInstance().format(fechaYHoraInicial),
				pacienteCitado.getDNI(),
				doctor.getDNI(),
		};
		
		return a.delete("citas", columnsIdentificativas, identificacion)== 1;
	}

	@Override
	public String toString() {
		return "Cita [fechaYHoraInicial=" + fechaYHoraInicial + ", fechaYHoraFinal=" + fechaYHoraFinal
				+ ", diagnostico=" + diagnostico + ", pacienteCitado=" + pacienteCitado + ", doctor=" + doctor + "]";
	}

}
