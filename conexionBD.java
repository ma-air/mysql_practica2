package mysql_practica2;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class conexionBD {
	String db_ = "";
	String login_ = "root";
	String password_ = "root";
	String url_ = "";

	// ESTABLECEMOS LA CONEXION CON LA BASE DE DATOS
	Connection connection_;

	public void conectar(String nombreBD, String login, String password) {
		try {

			db_ = nombreBD;
			login_ = login;
			password_ = password;
			url_ = "jdbc:mysql://localhost/" + db_;
			// ESTABLECEMOS LA CONEXION CON LA BASE DE DATOS
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection_ = DriverManager.getConnection(url_, login_, password_);

			if (connection_ != null) {
				System.out.println("Conexion establecida con la base de datos: " + db_);
			} else {
				System.err.println("SE HA PRODUCIDO UN ERROR");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void login() {
		Scanner entrada = new Scanner(System.in);
		System.out.print("Introduce nombre de la base de datos: ");
		String bd = entrada.next();
		System.out.print("Introduce login de mysql: ");
		String login = entrada.next();
		System.out.print("Introduce password de mysql: ");
		String password = entrada.next();
		conectar(bd, login, password);
	}

	public void insertarEmpleado() {
		try {
			Scanner entrada = new Scanner(System.in);
			String sql = "INSERT INTO `empleado` (`nif`, `nombre`, `apellidos`, `salario`, `Id_departamento`) VALUES (?, ?, ?, ?, ?);\n"; // PARA
																																			// EVITAR
																																			// EL
																																			// INJECTION
																																			// SE
																																			// PONEN
																																			// INTERROGANTES
			PreparedStatement ps = connection_.prepareStatement(sql);

			System.out.print("Introduce el NIF: ");
			String nif = entrada.next();
			ps.setString(1, nif);

			System.out.print("Introduce el nombre: ");
			String nombre = entrada.next();
			ps.setString(2, nombre);

			System.out.print("Introduce el apellido: ");
			String apellido = entrada.next();
			ps.setString(3, apellido);

			System.out.print("Introduce el salario: ");
			String salario = entrada.next();
			ps.setString(4, salario);

			System.out.print("Introduce el ID de Departamento: ");
			String id = entrada.next();
			ps.setString(5, id);

			actualizarPS(ps);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void modificarEmpleado() {
		try {
			Scanner entrada = new Scanner(System.in);
			System.out.print("Empleado a buscar (NIF): ");
			String dni = entrada.next();
			System.out.print(
					"Modificar NIF(1) , Nombre(2), Apellido(3), Salario(4), Departamento(5). Que desea modifcar: ");
			int opcion = entrada.nextInt();
			if (opcion == 1) {
				String sql1 = "UPDATE `empleado` SET `nif`= ? WHERE `nif`= ?;\n";

				PreparedStatement ps1 = connection_.prepareStatement(sql1);
				System.out.print("Introduce el nuevo NIF: ");
				String nif = entrada.next();
				ps1.setString(1, nif);
				ps1.setString(2, dni);
				actualizarPS(ps1);
			} else if (opcion == 2) {
				String sql2 = "UPDATE `empleado` SET `nombre`= ? WHERE `nif`= ?;\n";
				PreparedStatement ps2 = connection_.prepareStatement(sql2);
				System.out.print("Introduce el nuevo nombre: ");
				String nombre = entrada.next();
				ps2.setString(1, nombre);
				ps2.setString(2, dni);
				actualizarPS(ps2);
			} else if (opcion == 3) {
				String sql3 = "UPDATE `empleado` SET `apellidos`= ? WHERE `nif`= ?;\n";
				PreparedStatement ps3 = connection_.prepareStatement(sql3);
				System.out.print("Introduce el nuevo apellido: ");
				String apellido = entrada.next();
				ps3.setString(3, apellido);
				ps3.setString(2, dni);
				actualizarPS(ps3);
			} else if (opcion == 4) {
				String sql4 = "UPDATE `empleado` SET `salario`= ? WHERE `nif`= ?;\n";
				PreparedStatement ps4 = connection_.prepareStatement(sql4);
				System.out.print("Introduce el nuevo salario: ");
				String salario = entrada.next();
				ps4.setString(4, salario);
				ps4.setString(2, dni);
				actualizarPS(ps4);
			} else if (opcion == 5) {
				String sql5 = "UPDATE `empleado` SET `Id_departamento`= ? WHERE `nif`= ?;\n";
				PreparedStatement ps5 = connection_.prepareStatement(sql5);
				System.out.print("Introduce el nuevo ID de Departamento: ");
				String id = entrada.next();
				ps5.setString(5, id);
				ps5.setString(2, dni);
				actualizarPS(ps5);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void eliminarEmpleado() {
		try {
			Scanner entrada = new Scanner(System.in);
			System.out.print("Empleado a buscar (NIF): ");
			String dni = entrada.next();
			String sql1 = "DELETE `empleado` WHERE `nif`= ?;\n";
			PreparedStatement ps1 = connection_.prepareStatement(sql1);
			ps1.setString(1, dni);
			actualizarPS(ps1);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void eliminarDepartamento() {
		try {
			Scanner entrada = new Scanner(System.in);
			System.out.print("Departamento a buscar (ID): ");
			String id = entrada.next();
			String sql1 = "DELETE `departamento` WHERE `id`= ?;\n";
			PreparedStatement ps1 = connection_.prepareStatement(sql1);
			ps1.setString(1, id);
			actualizarPS(ps1);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void modificarDepartamento() {
		try {
			Scanner entrada = new Scanner(System.in);
			System.out.print("Departamento a buscar (ID): ");
			String id = entrada.next();
			System.out.print("Modificar ID(1) , Nombre(2). Que desea modifcar: ");
			int opcion = entrada.nextInt();
			if (opcion == 1) {
				String sql1 = "UPDATE `departamento` SET `id`= ? WHERE `id`= ?;\n";

				PreparedStatement ps1 = connection_.prepareStatement(sql1);
				System.out.print("Introduce el nuevo ID: ");
				String id_depart = entrada.next();
				ps1.setString(1, id_depart);
				ps1.setString(2, id);
				actualizarPS(ps1);
			} else if (opcion == 2) {
				String sql2 = "UPDATE `departamento` SET `nombre`= ? WHERE `id`= ?;\n";
				PreparedStatement ps2 = connection_.prepareStatement(sql2);
				System.out.print("Introduce el nuevo nombre: ");
				String nombre = entrada.next();
				ps2.setString(1, nombre);
				ps2.setString(2, id);
				actualizarPS(ps2);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void actualizarPS(PreparedStatement ps) throws SQLException {
		int rtdo = ps.executeUpdate();
		if (rtdo > 0) {
			System.out.println("Registro guardado con éxito");
		} else {
			System.out.println("Se produjo algún problema");
		}
	}

	public void insertarDepartamento() {
		try {
			Scanner entrada = new Scanner(System.in);
			String sql = "INSERT INTO `departamento` (`id`, `nombre`) VALUES (?, ?);\n"; // PARA
																							// EVITAR
																							// EL
																							// INJECTION
																							// SE
																							// PONEN
																							// INTERROGANTES
			PreparedStatement ps = connection_.prepareStatement(sql);

			System.out.print("Introduce el ID: ");
			String id = entrada.next();
			ps.setString(1, id);

			System.out.print("Introduce el nombre: ");
			String nombre = entrada.next();
			ps.setString(2, nombre);

			actualizarPS(ps);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void listarEmpleados() {
		try {
			Statement st_ = connection_.createStatement();
			String sql = "Select * from empleado";
			ResultSet resultado = st_.executeQuery(sql);
			String tabla = "";
			while (resultado.next()) {
				String nif = resultado.getString("nif");
				String nombre = resultado.getString("nombre");
				String apellidos = resultado.getString("apellidos");
				String salario = resultado.getString("salario");
				String id_depart = resultado.getString("id_departamento");
				tabla = nif + "   " + nombre + "  " + apellidos + "  " + salario + " " + id_depart;
				System.out.println(tabla);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void listarDepartamentos() {
		try {
			Statement st_ = connection_.createStatement();
			String sql = "Select * from departamento";
			ResultSet resultado = st_.executeQuery(sql);
			String tabla = "";
			while (resultado.next()) {
				String id = resultado.getString("id");
				String nombre = resultado.getString("nombre");
				tabla = id + "   " + nombre;
				System.out.println(tabla);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void listarEmpleadosDepartamento() {
		try {
			Statement st_ = connection_.createStatement();
			Scanner entrada = new Scanner(System.in);
			System.out.print("Busca el empleado por ID de Departamento: ");
			String id = entrada.next();
			String sql = "Select * from empleado where id_departamento ='" + id + "'";
			ResultSet resultado = st_.executeQuery(sql);
			String tabla = "";
			while (resultado.next()) {
				String nif = resultado.getString("nif");
				String nombre = resultado.getString("nombre");
				String apellidos = resultado.getString("apellidos");
				String salario = resultado.getString("salario");
				String id_depart = resultado.getString("id_departamento");
				tabla = nif + "   " + nombre + "  " + apellidos + "  " + salario + " " + id_depart;
				System.out.println(tabla);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void consultarEmpleadoDni() {
		try {
			Statement st_ = connection_.createStatement();
			Scanner entrada = new Scanner(System.in);
			System.out.print("Busca el empleado por DNI: ");
			String dni = entrada.next();
			String sql = "Select * from empleado where nif like '" + dni + "'";
			ResultSet resultado = st_.executeQuery(sql);
			String tabla = "";
			while (resultado.next()) {
				String nif = resultado.getString("nif");
				String nombre = resultado.getString("nombre");
				String apellidos = resultado.getString("apellidos");
				String salario = resultado.getString("salario");
				String id_depart = resultado.getString("id_departamento");
				tabla = nif + "   " + nombre + "  " + apellidos + "  " + salario + " " + id_depart;
				System.out.println(tabla);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void consultarDepartamentoID() {
		try {
			Statement st_ = connection_.createStatement();
			Scanner entrada = new Scanner(System.in);
			System.out.print("Busca el Departamento por ID: ");
			String id = entrada.next();
			String sql = "Select * from departamento where id ='" + id + "'";
			ResultSet resultado = st_.executeQuery(sql);
			String tabla = "";
			while (resultado.next()) {
				String id_dpto = resultado.getString("id");
				String nombre = resultado.getString("nombre");
				tabla = id_dpto + "   " + nombre;
				System.out.println(tabla);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void consultarDepartamentoNombre() {
		try {
			Statement st_ = connection_.createStatement();
			Scanner entrada = new Scanner(System.in);
			System.out.print("Busca el Departamento por Nombre: ");
			String nombreD = entrada.next();
			String sql = "Select * from departamento where nombre like'" + nombreD + "'";
			ResultSet resultado = st_.executeQuery(sql);
			String tabla = "";
			while (resultado.next()) {
				String id_dpto = resultado.getString("id");
				String nombre = resultado.getString("nombre");
				tabla = id_dpto + "   " + nombre;
				System.out.println(tabla);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void consultarSalarioMayor() {
		try {
			Statement st_ = connection_.createStatement();
			Scanner entrada = new Scanner(System.in);
			System.out.print("Busca el empleado por Salario mayor de : ");
			Float salario = entrada.nextFloat();
			String sql = "Select * from empleado where salario>" + salario;
			ResultSet resultado = st_.executeQuery(sql);
			String tabla = "";
			while (resultado.next()) {
				String nif = resultado.getString("nif");
				String nombre = resultado.getString("nombre");
				String apellidos = resultado.getString("apellidos");
				String salario2 = resultado.getString("salario");
				String id_depart = resultado.getString("id_departamento");
				tabla = nif + "   " + nombre + "  " + apellidos + "  " + salario2 + " " + id_depart;
				System.out.println(tabla);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void consultarSalarioInferior() {
		try {
			Statement st_ = connection_.createStatement();
			Scanner entrada = new Scanner(System.in);
			System.out.print("Busca el empleado por Salario menor o igual de : ");
			Float salario = entrada.nextFloat();
			String sql = "Select * from empleado where salario<=" + salario;
			ResultSet resultado = st_.executeQuery(sql);
			String tabla = "";
			while (resultado.next()) {
				String nif = resultado.getString("nif");
				String nombre = resultado.getString("nombre");
				String apellidos = resultado.getString("apellidos");
				String salario2 = resultado.getString("salario");
				String id_depart = resultado.getString("id_departamento");
				tabla = nif + "   " + nombre + "  " + apellidos + "  " + salario2 + " " + id_depart;
				System.out.println(tabla);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void cerrarConexion() {
		try {
			connection_.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
