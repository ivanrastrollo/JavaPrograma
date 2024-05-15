package sesion1505;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionTablas {

    // Establecimiento de la conexión
    static final String URL = "jdbc:mysql://localhost:3306/programa_java";
    static final String USER = "root";
    static final String PASSWORD = "admin";

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Establecida la conexión");
    }

    // Método para crear la tabla "paciente"
    public static void crearTablasPaciente(Connection conn) throws SQLException {
        // Crear la tabla "paciente"
        String queryPaciente = "CREATE TABLE IF NOT EXISTS paciente (" +
                "id INT PRIMARY KEY," +
                "nombre VARCHAR(30) NOT NULL," +
                "apellidos VARCHAR(100) NOT NULL," +
                "n_operaciones INT)";
        Statement st = conn.createStatement();
        st.execute(queryPaciente);
        System.out.println("Tabla 'paciente' creada correctamente");
    }

    // Método para crear la tabla "habitaciones"
    public static void crearTablasHabitaciones(Connection conn) throws SQLException {
        // Crear la tabla "habitaciones"
        String queryHabitaciones = "CREATE TABLE IF NOT EXISTS habitaciones (" +
                "numero_habitacion INT PRIMARY KEY," +
                "nombre VARCHAR(30) NOT NULL," +
                "numero_planta INT," +
                "Area VARCHAR(30))";
        Statement st = conn.createStatement();
        st.execute(queryHabitaciones);
        System.out.println("Tabla 'habitaciones' creada correctamente");
    }

    // Método para borrar la tabla "paciente"
    public static void borrarTablaPaciente(Connection conn) throws SQLException {
        // Borrar la tabla "paciente"
        String queryPaciente = "DROP TABLE IF EXISTS paciente";
        Statement st = conn.createStatement();
        st.execute(queryPaciente);
        System.out.println("Tabla 'paciente' eliminada correctamente");
    }

    // Método para borrar la tabla "habitaciones"
    public static void borrarTablaHabitaciones(Connection conn) throws SQLException {
        // Borrar la tabla "habitaciones"
        String queryHabitaciones = "DROP TABLE IF EXISTS habitaciones";
        Statement st = conn.createStatement();
        st.execute(queryHabitaciones);
        System.out.println("Tabla 'habitaciones' eliminada correctamente");
    }
}
