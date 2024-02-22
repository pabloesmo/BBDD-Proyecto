package panaderias;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

	final static String NULL_SENTINEL_VARCHAR = "NULL";
	final static int NULL_SENTINEL_INT = Integer.MIN_VALUE;
	final static java.sql.Date NULL_SENTINEL_DATE = java.sql.Date.valueOf("1900-01-01");

	private Connection conn = null;
	private String user;
	private String pass;
	private String url;
	private String database;
	private String server;
	private int port;

	public DBConnection(String server, int port, String user, String pass, String database) {
		this.server = server;
		this.port = port;
		this.user = user;
		this.pass = pass;
		this.database = database;
		url = String.format("jdbc:mysql://%s/%s", server, database);
	}

	// FUNCIONA BIEN
	// Establece una conexión con la base de datos utilizando los parámetros de
	// conexión proporcionados en el constructor
	public boolean connect() {
		boolean conectado = false;
		try {
			if (conn == null || conn.isClosed()) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection(url, user, pass);
					conectado = true;
				} catch (SQLException e) {
					System.err.println("Error al abriendo la conexion. El ERROR fue:");
					System.err.println(e.getMessage());
					System.exit(1);
				} catch (ClassNotFoundException e) {
					e.getMessage();
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return conectado;// Devuelve true si la conexión es exitosa, y false en caso contrario
	}

	// FUNCIONA BIEN
	// Cierra la conexión con la base de datos
	public boolean close() {
		boolean cerrado = false;
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				cerrado = true;
			}
			return cerrado;
		} catch (SQLException e) {
			System.err.println("Error al cerrar la conexion. El ERROR fue:");
			System.err.println(e.getMessage());
		}
		return cerrado;// Devuelve true si se cierra la conexion correctamente, y false en caso
						// contrario
	}

	// FUNCIONA BIEN
	// Ejecuta una instrucción SQL de actualización en la base de datos
	public int update(String sql) {// Recibe como parametro la instruccion SQL a ejecutar.
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			int filas = st.executeUpdate();
			return filas;// Retorna el número de filas afectadas por la actualización
		} catch (SQLException e) {
			e.getMessage();
			return -1;// o -1 si ocurre un error.
		}
	}

	// FUNCIONA
	// Ejecuta una instruccion SQL de actualizacion en la base de datos con
	// parametros
	public int update(String sql, ArrayList<Object> a) {// Recibe como parametros la instrucción SQL a ejecutar y una
														// lista de objetos que representan los parametros de la
														// consulta
		try (PreparedStatement st = conn.prepareStatement(sql)) {// Los valores de los parametros se asignan a la
																	// instruccion SQL utilizando PreparedStatement
			for (int i = 0; i < a.size(); i++) {
				Object param = a.get(i);

				if (param == null) {
					st.setNull(i + 1, Types.NULL);
				}
				// depende de que tipo de clase sea: hacemos un casting u otro
				String claseN = param.getClass().getName();
				switch (claseN) {
					// casteo a Integer
					case "java.lang.Integer":
						st.setInt(i + 1, (Integer) param);
						break;
					// casteo a boolean
					case "java.lang.Boolean":
						st.setBoolean(i + 1, (Boolean) param);
						break;
					// casteo a string
					case "java.lang.String":
						st.setString(i + 1, (String) param);
						break;
					// casteo a sql.Date (para Trabajar)
					case "java.sql.Date":
						st.setDate(i + 1, (java.sql.Date) param);
						break;
					// si no es de ninguno de estos tipos no hace nada
					default:
						break;
				}
			}
			int filas = st.executeUpdate();
			return filas;// Retorna el numero de filas afectadas por la actualizacion,
		} catch (SQLException e) {
			e.getMessage();
			return -1;// o -1 si ocurre un error.
		}
	}

	// FUNCIONA BIEN
	// Ejecuta una consulta SQL en la base de datos
	public ResultSet query(String sql) {// Recibe como parametro la instruccion SQL a ejecutar
		ResultSet rs = null;
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
		}
		return rs;// Devuelve un objeto ResultSet que contiene los resultados de la consulta, o
					// null si ocurre un error
	}

	// FUNCIONA
	// Ejecuta una consulta SQL en la base de datos con parametros
	public ResultSet query(String sql, ArrayList<Object> a) {// Recibe como parámetros la instrucción SQL a ejecutar y
																// una lista de objetos que representan los parámetros
																// de la consulta
		ResultSet rs = null;
		try {
			PreparedStatement st = conn.prepareStatement(sql);// Los valores de los parámetros se asignan a la
																// instrucción SQL utilizando PreparedStatement
			for (int i = 0; i < a.size(); i++) {
				Object param = a.get(i);
				if (param == null) {
					st.setNull(i + 1, Types.NULL);
				} else {
					// depende de que tipo de clase sea: hacemos un casting u otro
					String claseN = param.getClass().getName();
					switch (claseN) {
						// casteo a int
						case "java.lang.Integer":
							st.setInt(i + 1, (Integer) param);
							break;
						// casteo a boolean
						case "java.lang.Boolean":
							st.setBoolean(i + 1, (Boolean) param);
							break;
						// casteo a string
						case "java.lang.String":
							st.setString(i + 1, (String) param);
							break;
						// casteo a sql.Date (para trabajar)
						case "java.sql.Date":
							st.setDate(i + 1, (java.sql.Date) param);
							break;
						// si no es de ninguno de estos tipos no hace nada
						default:
							break;
					}
				}
			}
			rs = st.executeQuery();
		} catch (SQLException e) {
			e.getMessage();
		}
		return rs;// Retorna un objeto ResultSet que contiene los resultados de la consulta, o
					// null si ocurre un error
	}

	// FUNCIONA BIEN
	// Verifica si una tabla especificada existe en la base de datos.
	public boolean tableExists(String tableName) {// Recibe como parámetro el nombre de la tabla a verificar.
		boolean existe = false;
		try {
			ResultSet rs1 = conn.getMetaData().getTables(null, null, tableName, null);
			if (rs1.next()) {
				existe = true;
			} else {
				existe = false;
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return existe;// Retorna true si la tabla existe, y false en caso contrario
	}

}
