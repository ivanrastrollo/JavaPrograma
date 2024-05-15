package sesion1505;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EliminarTablas {
    public static void main(String[] args) throws SQLException {
        // Establecimiento de la conexión
        String url = "jdbc:mysql://localhost:3306/programa_java";
        String user = "root";
        String passwd = "admin";

        Connection conn = DriverManager.getConnection(url, user, passwd);
        System.out.println("Establecida la conexión");

        // Eliminar la tabla "paciente"
        String queryPaciente = "DROP TABLE IF EXISTS paciente";
        Statement st = conn.createStatement();
        st.execute(queryPaciente);
        System.out.println("Tabla 'paciente' eliminada correctamente");

        // Eliminar la tabla "habitaciones"
        String queryHabitaciones = "DROP TABLE IF EXISTS habitaciones";
        Statement st1 = conn.createStatement();
        st1.execute(queryHabitaciones);
        System.out.println("Tabla 'habitaciones' eliminada correctamente");

    }
}
