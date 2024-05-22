import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.Scanner;

public class Main {

    static Connection conn = null;

    public static void main(String[] args) {
        try {
            establecerConexion();
            Scanner sc = new Scanner(System.in);
            int choice;

            do {
                System.out.println("Pulsa 0 para crear tablas");
                System.out.println("Pulsa 1 para insertar un registro");
                System.out.println("Pulsa 2 para borrar un paciente por ID");
                System.out.println("Pulsa 3 para borrar todos los registros");
                System.out.println("Pulsa 4 para actualizar operaciones de un paciente");
                System.out.println("Pulsa 5 para consultar un paciente por ID");
                System.out.println("Pulsa 6 para consultar pacientes por apellido");
                System.out.println("Pulsa 7 para eliminar tablas");
                System.out.println("Pulsa 8 para salir");

                choice = sc.nextInt();

                switch (choice) {
                    case 0:
                        creacionTablas();
                        break;
                    case 1:
                        insertarRegistro();
                        break;
                    case 2:
                        borrarPacientes();
                        break;
                    case 3:
                        borrarRegistros();
                        break;
                    case 4:
                        actualizarOperaciones();
                        break;
                    case 5:
                        consultarPaciente();
                        break;
                    case 6:
                        consultarPorApellido();
                        break;
                    case 7:
                        eliminarTablas();
                        break;
                    case 8:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida, por favor intenta de nuevo.");
                }
            } while (choice != 8);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void consultarPorApellido() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dame el apellido del paciente a consultar:");
        String apellidos = sc.next();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM paciente WHERE apellidos = ?");
        ps.setString(1, apellidos);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println("El id del paciente es " + rs.getInt("id") +
                    ", el nombre es " + rs.getString("nombre") +
                    ", el apellido es " + rs.getString("apellidos") +
                    ", y el número de operaciones es " + rs.getInt("n_operaciones"));
        }
    }

    private static void consultarPaciente() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dame el id del paciente a consultar");
        int id = sc.nextInt();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM paciente WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println("El id del paciente es " + rs.getInt(1) +
                    ", el nombre es " + rs.getString(2) +
                    ", el apellido es " + rs.getString(3) +
                    ", y el número de operaciones es " + rs.getInt(4));
        }
    }

    private static void actualizarOperaciones() throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Dame el id que tenga mal las operaciones");
        int id = sc.nextInt();
        System.out.println("¿Cuantas operaciones tiene en realidad?");
        int n_operaciones = sc.nextInt();
        PreparedStatement ps = conn.prepareStatement("UPDATE paciente SET n_operaciones = ? WHERE id = ?");
        ps.setInt(1, n_operaciones);
        ps.setInt(2, id);
        ps.execute();
        System.out.println("Operaciones modificadas");
    }

    private static void borrarRegistros() throws SQLException {
        Statement statement = conn.createStatement();
        int rowsAffected = statement.executeUpdate("DELETE FROM paciente");

        if (rowsAffected > 0) {
            System.out.println("Todos los registros han sido borrados correctamente de la tabla paciente");
        } else {
            System.out.println("La tabla paciente ya estaba vacía");
        }
    }

    private static void establecerConexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/programa_java";
        String user = "root";
        String passwd = "admin";

        conn = DriverManager.getConnection(url, user, passwd);
        System.out.println("Establecida la conexion");
        System.out.println(conn.getCatalog());
    }

    private static void creacionTablas() throws SQLException {
        String query1 = "CREATE TABLE IF NOT EXISTS paciente (" +
                "id INT PRIMARY KEY," +
                "nombre VARCHAR(30) NOT NULL," +
                "apellidos VARCHAR(100) NOT NULL," +
                "n_operaciones INT)";

        String query2 = "CREATE TABLE IF NOT EXISTS habitaciones (" +
                "numero_habitacion INT PRIMARY KEY," +
                "nombre VARCHAR(30) NOT NULL," +
                "numero_planta INT," +
                "Area VARCHAR(30))";

        Statement st = conn.createStatement();
        st.execute(query1);
        System.out.println("Tabla paciente creada correctamente");
        st.execute(query2);
        System.out.println("Tabla habitaciones creada correctamente");
    }

    private static void eliminarTablas() throws SQLException {
        Statement st = conn.createStatement();
        String query = "DROP TABLE IF EXISTS paciente";
        st.executeUpdate(query);
        System.out.println("Tabla paciente eliminada correctamente");

        String query2 = "DROP TABLE IF EXISTS habitaciones";
        st.executeUpdate(query2);
        System.out.println("Tabla habitaciones eliminada correctamente");
    }

    private static void insertarRegistro() throws SQLException {
        Scanner sc = new Scanner(System.in);

        // INSERTAR REGISTRO EN LA TABLA paciente
        PreparedStatement ps = conn.prepareStatement("INSERT INTO paciente VALUES (?, ?, ?, ?)");
        System.out.println("Dame tu id");
        int id = sc.nextInt();
        System.out.println("Dame tu nombre");
        String nombre = sc.next();
        System.out.println("Dame tu apellido");
        String apellidos = sc.next();
        System.out.println("¿Cuantas veces te has operado?");
        int operacion = sc.nextInt();
        ps.setInt(1, id);
        ps.setString(2, nombre);
        ps.setString(3, apellidos);
        ps.setInt(4, operacion);
        ps.execute();
        System.out.println("Registro insertado correctamente en la tabla paciente");

        // INSERTAR REGISTROS PREDEFINIDOS
        PreparedStatement ps2 = conn.prepareStatement("INSERT INTO paciente VALUES (?, ?, ?, ?)");
        ps2.setInt(1, 1);
        ps2.setString(2, "Ivan");
        ps2.setString(3, "Rastrollo");
        ps2.setInt(4, 1);
        ps2.execute();
        System.out.println("Segundo registro insertado correctamente en la tabla paciente");

        PreparedStatement ps3 = conn.prepareStatement("INSERT INTO paciente VALUES (?, ?, ?, ?)");
        ps3.setInt(1, 2);
        ps3.setString(2, "Anouar");
        ps3.setString(3, "Tahiri");
        ps3.setInt(4, 5);
        ps3.execute();
        System.out.println("Tercer registro insertado correctamente en la tabla paciente");
    }

    private static void borrarPacientes() throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Dame el id del paciente que quieres borrar:");
        int id = sc.nextInt();

        PreparedStatement ps = conn.prepareStatement("DELETE FROM paciente WHERE id = ?");
        ps.setInt(1, id);
        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Registro borrado correctamente en la tabla paciente");
        } else {
            System.out.println("No se encontró el id");
        }
    }
}
