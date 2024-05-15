import sesion1505.GestionTablas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        //ESTABLECIMIENTO DE LA CONEXION
        String url = "jdbc:mysql://localhost:3306/programa_java";
        String user = "root";
        String passwd = "admin";

        Connection conn = DriverManager.getConnection(url, user, passwd);
        System.out.println("Establecida la conexion");
        System.out.println(conn.getCatalog());

        // Llamar al método para crear tablas
        GestionTablas.crearTablasPaciente(conn);
        GestionTablas.crearTablasHabitaciones(conn);

        // Llamar al método para borrar tablas
        GestionTablas.borrarTablaPaciente(conn);
        GestionTablas.borrarTablaHabitaciones(conn);
    }
        }
