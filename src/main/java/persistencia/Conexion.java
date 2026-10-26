package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;  // Es el driver que gestiona/solicita la conexión y también el encargado de utilizar el driver JDBC de MariaDB.
import java.sql.SQLException;

// Responsabilidad única de clase Conexión: 
// establecer la conexión entre Java y la base de datos MariaDB mediante una única instancia, o sea, patrón SINGLETON.
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
    private Conexion() {                                                                                    // (SINGLETON)

        try {

            //2do.
            // Se establece la conexión con la base de datos llamada sgulp (en este caso).
            // localhost indica que MariaDB está ejecutándose localmente.
            // root es el usuario y "" indica que no hay contraseña.         
            // Se le asigna a la variable la conexión que DriverManager consiga con los datos: URL, usuario y contraseña.
            connection = DriverManager.getConnection("jdbc:mariadb://localhost/", "root", "");

            // DriverManager pide el acceso → driver de MariaDB realiza la comunicación → Connection representa ese acceso en Java.
        } catch (SQLException e) {

            System.out.println("Error de conexión: " + e.getMessage());

        }

    }

    //comentario
    // La propia clase es la que controle el único new Conexion()                                              (SINGLETON)
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

        // 3ro.
        // Cuando desde afuera: Conexion.getInstancia().getConnection(), habiéndose cumplido lo 1ro y 2do.        
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
