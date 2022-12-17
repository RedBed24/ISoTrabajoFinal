package persistencia;

public interface BDConstantes {
	// Driver para conectar con bases de datos MySQL
	String DRIVER ="com.mysql.cj.jdbc.Driver";
	
	// Informaci�n varia sobre la DB 
	String DBNAME ="iso";
	String DBUSER ="root";
	String DBPASS ="root";
	
	// Información sobre las tablas
	// posiblemente esto deba de estar dentro de cada clase, por ejemplo, si queremos hacer que todo vaya mediante constantes
	String NOMBRETABLACITAS= "citas";
	
	// Identificador ODBC de la base de datos
	String URL = "jdbc:mysql://localhost:3306/"+DBNAME+"?user="+DBUSER+"&password="+DBPASS;

}
