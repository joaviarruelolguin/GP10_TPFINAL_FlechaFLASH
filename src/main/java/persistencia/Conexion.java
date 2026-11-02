package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;  // Es el driver que gestiona/solicita la conexión y también el encargado de utilizar el driver JDBC de MariaDB.
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


/*
    Responsabilidad de clase Conexion:
    establecer la conexión entre Java y la base de datos MariaDB mediante una única instancia, o sea, patrón SINGLETON.
    Además, comprobar si la base de datos flechaflash existe y, si no existe, inicializarla mediante un script SQL.
*/

// Patrón SINGLETON: una única instancia existente de Conexion durante toda la ejecución del programa.

/*
    Todos los Data utilizan la misma clase encargada de obtener la conexión, 
    en lugar de que cada Data tenga su propia lógica de conexión.
    De este modo, Data solo se ocupa de acceder a los datos.
 */


public class Conexion {

    // Clase que administra la conexión; guarda la única instancia de Conexion:
    private static Conexion instancia;          // Variable a nivel de clase: pertenece a la clase Conexion. (SINGLETON)

    // Objeto de JDBC que representa la conexión concreta con MariaDB; guarda la conexión JDBC:
    private Connection connection;              // Variable no static: pertenece al objeto Conexion.

    
    // Constructor privado para impedir la creación de Conexion desde afuera.
    // De este modo, ninguna otra clase puede crear DIRECTAMENTE un objeto Conexion.
    private Conexion() {  //                                                                                 (SINGLETON)

        try {
                     
            // 1ro.
            // Se establece una conexión inicial con el servidor MariaDB, sin seleccionar todavía una base de datos.
            // localhost indica que MariaDB está ejecutándose localmente.
            // 3306 es el puerto predeterminado de MariaDB.
            // root es el usuario y "" indica que no hay contraseña.
                  
            Connection conexionInicial = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/", "root", ""
            );

            
            // 2do.
            // Se comprueba si la base de datos flechaflash ya existe.
            if (!existeBaseDeDatos(conexionInicial)) {
                
                // Si no existe, se ejecuta el script SQL para crear la base de datos, sus tablas y los datos iniciales.
                System.out.println("La base de datos no existe. Creándola...");
                ejecutarScript(conexionInicial);
                
            } else {
                
                // Si ya existe, no se vuelve a ejecutar el script para evitar duplicar los datos iniciales.
                System.out.println("La base de datos flechaflash ya existe.");
            }

            // Se cierra la conexión inicial con el servidor MariaDB.
            conexionInicial.close();

                       
            // 3ro.
            // Una vez comprobada o inicializada la base de datos, se establece la conexión JDBC con flechaflash.
            // Esta es la conexión que utilizarán las clases Data para acceder a la base de datos.
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/flechaflash", "root", "");

        } catch (SQLException | IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

    }


    private boolean existeBaseDeDatos(Connection conexion) throws SQLException {
        
        // Consulta a INFORMATION_SCHEMA, que contiene información sobre las bases de datos existentes en MariaDB.
        String sql = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            
            // Se establece el nombre de la base de datos que se desea comprobar.
            ps.setString(1, "flechaflash");
            
             // Se ejecuta la consulta y se obtiene el resultado.
            try (ResultSet rs = ps.executeQuery()) {
                
                // Si existe un resultado, la base de datos existe; de lo contrario, no existe.
                return rs.next();
            }
        }
    }
    
    
    // Método para leer y ejecutar el script SQL para inicializar la base de datos.
    private void ejecutarScript(Connection conexion) throws SQLException, IOException {
        
        // Se obtiene el archivo SQL ubicado en src/main/resources.
        InputStream archivo = getClass().getResourceAsStream("/GP10_flechaflash_base.sql");

        // Comprobar si se encontró el archivo:
        if (archivo == null) {
            throw new IOException("No se encontró el archivo SQL en resources.");
        }

        // Se lee el contenido completo del archivo utilizando UTF-8.
        String contenido;
        try (archivo) {
            contenido = new String(archivo.readAllBytes(), StandardCharsets.UTF_8);
        }

        // Se eliminan los comentarios de bloque y los comentarios de línea del script.
        contenido = contenido.replaceAll("(?s)/\\*.*?\\*/", "");
        contenido = contenido.replaceAll("(?m)^\\s*--.*$", "");

        // Se separa el contenido del script en sentencias SQL utilizando el punto y coma como delimitador.
        String[] sentencias = contenido.split(";");

        // Se crea un objeto Statement para ejecutar las sentencias SQL.
        try (Statement st = conexion.createStatement()) {
            
            // Se recorren todas las sentencias obtenidas del script.
            for (String sentencia : sentencias) {
                
                // Se eliminan los espacios en blanco al principio y al final de cada sentencia.
                sentencia = sentencia.trim();
                
                // Se ejecutan únicamente las sentencias que no están vacías.
                if (!sentencia.isEmpty()) {
                    
                    st.execute(sentencia);
                }
            }
        }
    }

    
    
    // La propia clase es la que controle el único new Conexion()                                   (SINGLETON)
    // El objeto se puede obtener/crear desde afuera, pero su construcción está controlada por la propia clase. 
    public static Conexion getInstancia() {

        if (instancia == null) {

            // 1ro. 
            // Se activa el constructor cuando desde afuera: Conexion.getInstancia()
            // OBJETO SINGLETON:
            instancia = new Conexion();
        }
        return instancia;   // Si ya existe objeto Conexion creado, solo se reutiliza.
    }

    public Connection getConnection() {

        // 4to.
        // Cuando desde afuera se invoca: Conexion.getInstancia().getConnection(),
        // habiéndose cumplido los pasos anteriores.
        // Devuelve la conexión JDBC establecida con la base de datos flechaflash.     
        return connection;
    }

    // Comprobar si el objeto de conexión se obtuvo o no:
    public boolean isConectado() {

        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    /*
    getInstancia()
        ↓
    new Conexion()
        ↓
    constructor
        ↓
    DriverManager.getConnection(...)
        ↓
    Conexión inicial con el servidor MariaDB
        ↓
    existeBaseDeDatos()
        ↓
    ¿Existe flechaflash?
        ↓
    NO → ejecutarScript()
             ↓
          Inicializa la base de datos
        ↓
    SÍ → Continúa sin ejecutar el script
        ↓
    DriverManager.getConnection(...)
        ↓
    Connection
        ↓
    isConectado()
        ↓
    Verifica si se obtuvo una conexión (connection != null)
        ↓
    getConnection()
        ↓
    Devuelve el objeto Connection para que lo utilicen las clases Data
     */
}
