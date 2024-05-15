package sesion1505;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearTablas {
    public static void crearTablas(Connection conn) throws SQLException {
        Statement st = conn.createStatement();

        // Crear la tabla "paciente"
        String queryPaciente = "CREATE TABLE IF NOT EXISTS paciente (" +
                "id INT PRIMARY KEY," +
                "nombre VARCHAR(30) NOT NULL," +
                "apellidos VARCHAR(100) NOT NULL," +
                "n_operaciones INT)";
        st.execute(queryPaciente);
        System.out.println("Tabla 'paciente' creada correctamente");

        // Crear la tabla "habitaciones"
        String queryHabitaciones = "CREATE TABLE IF NOT EXISTS habitaciones (" +
                "numero_habitacion INT PRIMARY KEY," +
                "nombre VARCHAR(30) NOT NULL," +
                "numero_planta INT," +
                "Area VARCHAR(30))";
        st.execute(queryHabitaciones);
        System.out.println("Tabla 'habitaciones' creada correctamente");

    }
}
