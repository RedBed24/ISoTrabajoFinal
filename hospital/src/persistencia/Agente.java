package persistencia;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Agente  implements BDConstantes {
	
	// Instancia del agente
	protected static Agente mInstancia= null;
	
	// Conexi�n con la base de datos
	protected static Connection mBD;

	// Constructor
	private Agente() throws Exception {
		conectar();
	}

	// Implementaci�n del patr�n Singleton --> http://es.wikipedia.org/wiki/Singleton 
	
	public static Agente getAgente() throws Exception {
		if (mInstancia== null) {
			mInstancia= new Agente();
		}
		return mInstancia;
	}

	// Conexi�n a la base de datos
	
	private void conectar() throws Exception {
		Class.forName(DRIVER);
		mBD = DriverManager.getConnection(URL, DBUSER, DBPASS);
	}

	// Desconectar de la base de datos
	
	public void desconectar() throws Exception {
		mBD.close();
	}

	// Inserci�n en la base de datos
	
	/**
	 * Inserta (añadir una nueva entrada) a una tabla en las columnas dadas con los valores dados. Deben ser la misma cantidad y el mismo orden
	 * Debe existir al menos una instancia de cada vector (al menos un elemento)
	 * 
	 * @param tablename --> Nombre de la tabla donde se insertará
	 * @param columns --> Nombres de las columnas donde se insertarán los valores
	 * @param values --> Valores a insertar en las columnas
	 * 
	 * @throws IllegalArgumentException --> Cuando hay un número diferente de columnas y valores a insertar
	 * @throws IndexOutOfBoundsException --> Cuando no se ha especificado al menos 1 elemnto en cada vector
	 */
	public int insert(final String tablename, final String columns[], final String values[]) throws SQLException, Exception {
		if (columns.length != values.length) throw new IllegalArgumentException("No se han especificado el mismo número de columnas y valores.");
		conectar();
		String SQL= "insert into `"+DBNAME+"`.`"+tablename+"` (";

		for (int i= 0; i< columns.length-1; i++)
			SQL+= "`"+columns[i]+"`, ";

		SQL+= "`"+columns[columns.length-1]+"`) values (";

		for (int i= 0; i< values.length-1; i++)
			SQL+= "'"+values[i]+"', ";

		PreparedStatement stmt = mBD.prepareStatement(SQL+"'"+values[values.length-1]+"');");
		int res = stmt.executeUpdate();
		stmt.close();
		desconectar();
		return res;
	}

	// Eliminaci�n de la base de datos
	
	public int delete(final String tablename, final String columnasIdentificativas[], final String valoresIdentificativos[]) throws SQLException, Exception {
		if (columnasIdentificativas.length!= valoresIdentificativos.length) throw new IllegalArgumentException("No se han especificado el mismo número de columnas y valores.");
		conectar();

		String SQL= "delete from `"+DBNAME+"`.`"+tablename+"` where ";

		for (int i= 0; i< columnasIdentificativas.length-1; i++)
			SQL+= "(`"+columnasIdentificativas[i]+"`='"+valoresIdentificativos[i]+"') and ";

		PreparedStatement stmt = mBD.prepareStatement(SQL+ "(`"+columnasIdentificativas[columnasIdentificativas.length-1]+"`='"+valoresIdentificativos[columnasIdentificativas.length-1]+"');");
		int res = stmt.executeUpdate();
		stmt.close();
		desconectar();
		return res;
	}

	/**
	 * Actualiza (cambia los valores de las colmnasAEditar por los valoresAeditar) una entrada de una tabla especificada por las columnas identificativas con los valores identificativos dados
	 * Debe existir al menos una instancia de cada vector (al menos un elemento)
	 * 
	 * @param tablename --> Nombre de la tabla donde se actualizará
	 * @param columnsAEditar --> Nombres de las columnas donde se actualizarán los valores
	 * @param valuesAEditar --> Nuevos valores a actualizar en las columnas
	 * @param columnasIdentificativas --> columnas que identifican una entrada
	 * @param valoresIdentificativos --> valores de las columsnas identificativas que identifican una entrada
	 * 
	 * @throws IllegalArgumentException --> Cuando hay un número diferente de columnas y valores a insertar
	 * @throws IndexOutOfBoundsException --> Cuando no se ha especificado al menos 1 elemnto en cada vector
	 */
	public int update(final String tablename, final String columnsAEditar[], final String valuesAEditar[], final String columnasIdentificativas[], final String valoresIdentificativos[]) throws SQLException, Exception {
		if (columnsAEditar.length!= valuesAEditar.length || columnasIdentificativas.length!= valoresIdentificativos.length) throw new IllegalArgumentException("No se han especificado el mismo número de columnas y valores.");
		conectar();
		// inicio de la sentencia SQL
		String SQL= "update `"+DBNAME+"`.`"+tablename+"` set ";
		
		// se añaden las columnas a editar con los respectivos valores a editar
		for (int i= 0; i< columnsAEditar.length-1; i++)
			SQL+= "`"+columnsAEditar[i]+"`='"+valuesAEditar[i]+"', ";

		// se añade la última columna a editar y el principio de las condiciones
		SQL+= "`"+columnsAEditar[columnsAEditar.length-1]+"`='"+valuesAEditar[columnsAEditar.length-1]+"' where ";

		// se añaden las condiciones con AND ya que sólo se va a editar una entrada por llamada (a no ser que se usen wildcards)
		for (int i= 0; i< columnasIdentificativas.length-1; i++)
			SQL+= "(`"+columnasIdentificativas[i]+"`='"+valoresIdentificativos[i]+"') and ";

		// se añade la última condición
		PreparedStatement stmt = mBD.prepareStatement(SQL+ "(`"+columnasIdentificativas[columnasIdentificativas.length-1]+"`='"+valoresIdentificativos[columnasIdentificativas.length-1]+"');");
		int res = stmt.executeUpdate();
		stmt.close();
		desconectar();
		return res;
	}

	/*
	 * B�squeda o selecci�n de informaci�n en la base de datos.
	 * El m�todo select devuelve un vector de vectores donde cada uno representa 
	 * los registros que se recuperan de la base de datos.
	 */
	
	public Vector<Vector<Object>> select(final String tablename, final String columnasIdentificativas[], final String valoresIdentificativos[]) throws SQLException, Exception {
		if (columnasIdentificativas.length!= valoresIdentificativos.length) throw new IllegalArgumentException("No se han especificado el mismo número de columnas y valores.");
		Vector<Vector<Object>> vectorAdevolver = new Vector<Vector<Object>>();
		conectar();
		String SQL= "select * from `"+DBNAME+"`.`"+tablename+"` where ";

		for (int i= 0; i< columnasIdentificativas.length-1; i++)
			SQL+= "(`"+columnasIdentificativas[i]+"`='"+valoresIdentificativos[i]+"') and ";

		SQL+= "(`"+columnasIdentificativas[columnasIdentificativas.length-1]+"`='"+valoresIdentificativos[columnasIdentificativas.length-1]+"');";

		Statement stmt = mBD.createStatement();
		ResultSet res = stmt.executeQuery(SQL);
		ResultSetMetaData rsmd = res.getMetaData();
		int numCol = rsmd.getColumnCount();
		while (res.next()) {
			Vector<Object> v = new Vector<Object>();
			for (int i = 1; i <= numCol; i++) {
				v.add(res.getObject(i));
			}
			vectorAdevolver.add(v);
		}
		stmt.close();
		desconectar();
		return vectorAdevolver;
	}
}
