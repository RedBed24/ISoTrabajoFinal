# ISoTrabajoFinal
Código para la organización de un Hospital (teórico).

### hospital/

Es el proyecto principal, por ahora, único. Trae ya las configuraciones para poder ejecutarlo (hospital/.classpath).

### hospital/src/

Carpeta que contiene los diferentes archivos de código fuente (*.java).
Sigue la división en:

### hospital/src/presentacion/

Contiene aquellas clases de interfaz que se comunicarán con los usuarios finales. Es donde se localiza el main (actualmente en hospital/src/Main.java).

### hospital/src/dominio/

Clases que se encargarán de hacer la ejecución propiamente dicha: Aquí es donde está la lógica del problema.

Contiene tanto clases de información, como clases de control e híbridos.

### hospital/src/persistencia/

Clases encargadas de comunicarse con la base de datos. Tiene la información necesaria para poder hacer todo lo relacionado con esto.

### ISOspital.jar

Es el ejecutable que se usará durante la entrega.

### infoDataBase/

Contiene scripts SQL y ficheros .csv con la información necesaria para la base de datos.

### hospital/lib/

Contiene también una librería externa para la conexión con una base de datos local MySQL.
