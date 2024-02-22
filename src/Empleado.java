package panaderias;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Empleado extends DBTable {
	
	private int id_empleado;
	private String n_ss;
	private String nombre;
	private String apellido1;
	private String apellido2;
	
	public Empleado(int id_empleado, DBConnection conn, boolean DBSync) {
		super(conn, DBSync);
		this.id_empleado = id_empleado;
		conn.connect();
		if (DBSync) {
			insertEntry();
		}
	}
	
	public Empleado(int id_empleado, String n_ss, String nombre, String apellido1, String apellido2, DBConnection conn, boolean DBSync) {
		super(conn, DBSync);	
		this.id_empleado = id_empleado;
		this.n_ss = n_ss;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		conn.connect();
		if (DBSync) {
			insertEntry();
		}
	}
	
	// FUNCIONA
	// Devuelve el ID del empleado.
	public int getId_empleado() {
		if (DBSync) {// Si la sincronización con la base de datos está activada,
			getEntryChanges();// se obtienen los cambios de la entrada antes de devolver el ID.
		}
		return id_empleado;
	}

	// FUNCIONA
	// Devuelve el número de seguridad social del empleado.
	public String getN_ss() {
		if (DBSync) {// Si la sincronización con la base de datos está activada,
			getEntryChanges();// se obtienen los cambios de la entrada antes de devolver el número de
								// seguridad social.
		}
		return n_ss;
	}

	// FUNCIONA
	// Devuelve el nombre del empleado.
	public String getNombre() {
		if (DBSync) {// Si la sincronización con la base de datos está activada,
			getEntryChanges();// se obtienen los cambios de la entrada antes de devolver el nombre.
		}
		return nombre;
	}

	// FUNCIONA
	// Devuelve el primer apellido del empleado.
	public String getApellido1() {
		if (DBSync) {// Si la sincronización con la base de datos está activada,
			getEntryChanges();// se obtienen los cambios de la entrada antes de devolver el primer apellido.
		}
		return apellido1;
	}

	// FUNCIONA
	// Misma funcion que getApellido1 pero con apellido2
	public String getApellido2() {
		if (DBSync) {
			getEntryChanges();
		}
		return apellido2;
	}

	// FUNCIONA
	// Establece el número de seguridad social del empleado.
	public void setN_ss(String n_ss) {
		if (DBSync) {// Si la sincronización con la base de datos está activada,
			getEntryChanges();// se obtienen los cambios de la entrada
			updateEntry();// se actualiza la entrada
		}
		this.n_ss = n_ss;
	}

	// FUNCIONA
	// Establece el nombre del empleado.
	public void setNombre(String nombre) {
		if (DBSync) {// Si la sincronización con la base de datos está activada,
			getEntryChanges();// se obtienen los cambios de la entrada
			updateEntry();// se actualiza la entrada.
		}
		this.nombre = nombre;
	}

	// FUNCIONA
	// Establece el primer apellido del empleado
	public void setApellido1(String apellido1) {
		if (DBSync) {// Si la sincronización con la base de datos está activada
			getEntryChanges();// se obtienen los cambios de la entrada
			updateEntry();// se actualiza la entrada
		}
		this.apellido1 = apellido1;
	}

	// FUNCIONA
	// Igual que setApellido1 pero con apellido2
	public void setApellido2(String apellido2) {
		if (DBSync) {
			getEntryChanges();
			updateEntry();
		}
		this.apellido2 = apellido2;
	}

	// FUNCIONA BIEN
	// Elimina el empleado de la base de datos
	public void destroy() {
		conn.connect();//me conecto a la db
		String sql = "DELETE FROM empleado WHERE id_empleado=?";//se borra la entrada correspondiente en la tabla "empleado"
		ArrayList<Object> params = new ArrayList<>();
		params.add(id_empleado);
		conn.update(sql, params);//se actualizan los valores de los atributos a los valores nulos
		id_empleado = DBConnection.NULL_SENTINEL_INT;
		n_ss = DBConnection.NULL_SENTINEL_VARCHAR;
		nombre = DBConnection.NULL_SENTINEL_VARCHAR;
		apellido1 = DBConnection.NULL_SENTINEL_VARCHAR;
		apellido2 = DBConnection.NULL_SENTINEL_VARCHAR;
		DBSync = false;
	}		
	
	@Override
	/*public String toString() {
	    return "Empleado{" +
	            "id_empleado=" + id_empleado +
	            ", n_ss='" + n_ss + '\'' +
	            ", nombre='" + nombre + '\'' +
	            ", apellido1='" + apellido1 + '\'' +
	            ", apellido2='" + apellido2 + '\'' +
	            '}';
	}*/

	
	//FUNCIONA BIEN
	boolean createTable() {
		conn.connect(); //me conecto a la db
		boolean creada = false;
		//si ya existe no hago nada
		if(conn.tableExists("empleado")) {
			return false;
		}
		String sql = "CREATE TABLE empleado ("
				+ "  id_empleado INT UNSIGNED,"
				+ "  nombre VARCHAR(100),"
				+ "  apellido1 VARCHAR(100),"
				+ "  apellido2 VARCHAR(100),"
				+ "  n_ss VARCHAR(100),"
				+ "  PRIMARY KEY (id_empleado));";
		int ok = conn.update(sql);
		if(ok == 0) {
			creada = true;
		}
		return creada;
	}
	
	
	//FUNCIONA BIEN
	public boolean insertEntry() {
		conn.connect(); //me conecto a la db
		boolean insertado = false;
		if (DBSync) {
			//si no exite la tabla la creo antes de insertar nada
			if (!conn.tableExists("empleado")) {
				createTable();
			}
			String sql = "INSERT INTO empleado(id_empleado, nombre, apellido1, apellido2, n_ss) VALUES (?,?,?,?,?)";
			ArrayList<Object> params = new ArrayList<>();
			params.add(id_empleado);
			//si son null los parametros en el constructor los pongo a valor centinela
			params.add(nombre != null ? nombre : DBConnection.NULL_SENTINEL_VARCHAR);
			params.add(apellido1 != null ? apellido1 : DBConnection.NULL_SENTINEL_VARCHAR);
			params.add(apellido2 != null ? apellido2 : DBConnection.NULL_SENTINEL_VARCHAR);
			params.add(n_ss != null ? n_ss : DBConnection.NULL_SENTINEL_VARCHAR);
			int filas = conn.update(sql, params);
			insertado = filas == 1;
			//no se ha podido insertar el elemento
			if(filas != 1) {
				this.id_empleado = 0;
				DBSync = false;
				insertado = false;
			}
		}
		return insertado;
	}

	
	// FUNCIONA BIEN	
	public boolean updateEntry() {
	    conn.connect(); //me conecto a la db
	    boolean actualizado = false;
	    if (DBSync) {
	        String sql = "UPDATE empleado SET nombre = ?, apellido1 = ?, apellido2 = ?, n_ss = ? WHERE id_empleado = ?";
	        ArrayList<Object> params = new ArrayList<>();
	        //si son null los parametros en el constructor los pongo a valor centinela
	        params.add(nombre != null ? nombre : DBConnection.NULL_SENTINEL_VARCHAR);
	        params.add(apellido1 != null ? apellido1 : DBConnection.NULL_SENTINEL_VARCHAR);
	        params.add(apellido2 != null ? apellido2 : DBConnection.NULL_SENTINEL_VARCHAR);
	        params.add(n_ss != null ? n_ss : DBConnection.NULL_SENTINEL_VARCHAR);
	        params.add(id_empleado);
	        int filas = conn.update(sql, params);
	        if (filas > 0) {
	            actualizado = true;
	        }
	    }
	    return actualizado;
	}

	
	//FUNCIONA BIEN
	boolean deleteEntry() {
		conn.connect(); //me conecto
		boolean eliminado = false;
		if (DBSync) {
			String sql = "DELETE FROM empleado WHERE id_empleado = ?"; //borra el empleado con id =?
			ArrayList<Object> params = new ArrayList<>();
			params.add(id_empleado);
			int filas = conn.update(sql, params);
			if (filas == 1)
				eliminado = true;
		}
		return eliminado;
	}
	
	//FUNCIONA BIEN
	void getEntryChanges() {
		conn.connect();//me conecto
		if (DBSync) {
			String sql = "SELECT * FROM empleado WHERE id_empleado = ?";
			ArrayList<Object> params = new ArrayList<>();
			params.add(id_empleado);
			try {
				ResultSet rs = conn.query(sql, params);

				if (rs.next()) {
					//obtengo los valores y los meto en los atributos del objeto
					n_ss = rs.getString("n_ss");
					nombre = rs.getString("nombre");
					apellido1 = rs.getString("apellido1");
					apellido2 = rs.getString("apellido2");
				}
			} catch (SQLException e) {
				e.getMessage();
			}
		}
	}
}
