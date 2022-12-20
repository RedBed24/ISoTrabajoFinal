package persistencia;

public interface BDConstantes {
	// Driver para conectar con bases de datos MySQL
	String DRIVER ="com.mysql.cj.jdbc.Driver";
	
	// Informaciín varia sobre la DB 
	String DBNAME ="iso";
	String DBUSER ="root";
	String DBPASS ="root";
	
	// Información sobre las tablas
	String NOMBRE_TABLA_CITAS = "citas";
	String NOMBRE_TABLA_TRABAJADORES = "trabajadores";
	String NOMBRE_TABLA_PACIENTES = "pacientes";
	String NOMBRE_TABLA_INGRESOS = "ingresos";
	
	// Identificador ODBC de la base de datos
	String URL = "jdbc:mysql://localhost:3306/"+DBNAME+"?user="+DBUSER+"&password="+DBPASS;

}
