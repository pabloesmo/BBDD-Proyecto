package panaderias;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Trabaja extends DBTable {
	
	private int id_empleado;
	private int id_local;
	private java.sql.Date fecha_inicio;
	private java.sql.Date fecha_fin;

	
	public Trabaja(int id_empleado, int id_local, java.sql.Date fecha_inicio, DBConnection conn, boolean DBSync) {
		super(conn, DBSync);
		this.id_empleado = id_empleado;
		this.id_local = id_local;
		this.fecha_inicio = fecha_inicio;
		conn.connect();
		if (DBSync) {
			insertEntry();
		}
	}
	
	public Trabaja(int id_empleado, int id_local, java.sql.Date fecha_inicio, java.sql.Date fecha_fin, DBConnection conn, boolean DBSync) {
		super(conn, DBSync);
		this.id_empleado = id_empleado;
		this.id_local = id_local;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		conn.connect();
		if (DBSync) {
			insertEntry();
		}
	}

	// FUNCIONA
	// Función que devuelve el id_empleado
	public int getId_empleado() {
		if (DBSync) {
			getEntryChanges();
		}
		return id_empleado;
	}

	// FUNCIONA
	// Función que devuelve la fecha_inicio
	public int getId_local() {
		if (DBSync) {
			getEntryChanges();
		}
		return id_local;
	}

	// FUNCIONA
	// Función que devuelve la fecha_inicio
	public java.sql.Date getFecha_inicio() {
		if (DBSync) { // verifica la sincronización con la base de datos y
			getEntryChanges(); //// llama a getEntryChanges() si es necesario.
		}
		return fecha_inicio;
	}

	// FUNCIONA
	// Función que devuelve la fecha_fin
	public java.sql.Date getFecha_fin() {
		if (DBSync) {
			getEntryChanges();
		}
		return fecha_fin;
	}

	// FUNCIONA
	// Función que establece la fecha_fin
	public void setFecha_fin(java.sql.Date fecha_fin) {
		if (DBSync) { // Si la sincronización está habilitada,
			getEntryChanges();// se llama a getEntryChanges() para obtener los cambios de la entrada
			updateEntry();// y luego se llama a updateEntry() para actualizar la entrada en la base de
							// datos.
		}
		this.fecha_fin = fecha_fin;
	}

	@Override
	/*
	 * public String toString() { StringBuilder sb = new StringBuilder();
	 * sb.append("Trabaja ["); sb.append("id_empleado=").append(id_empleado);
	 * sb.append(", id_local=").append(id_local);
	 * sb.append(", fecha_inicio=").append(fecha_inicio);
	 * sb.append(", fecha_fin=").append(fecha_fin); sb.append("]"); return
	 * sb.toString(); }
	 */

	
	// FUNCIONA BIEN
	// Método para eliminar una entrada de la base de datos
	public void destroy() {
		conn.connect();//me conecto a la db
		String sql = "DELETE FROM trabaja WHERE id_empleado=? AND id_local=? AND fecha_inicio=?"; //se borra la entrada correspondiente en la tabla "trabaja"
		ArrayList<Object> params = new ArrayList<>();
		params.add(id_empleado);
		params.add(id_local);
		params.add(fecha_inicio);
		conn.update(sql, params);//se actualizan los valores de los atributos a los valores nulos
		id_empleado = DBConnection.NULL_SENTINEL_INT;
		id_local = DBConnection.NULL_SENTINEL_INT;
		fecha_inicio = DBConnection.NULL_SENTINEL_DATE;
		fecha_fin = DBConnection.NULL_SENTINEL_DATE;
		DBSync = false;
	}
	
	//FUNCIONA BIEN
	boolean createTable() {
		conn.connect();//me conecto a la db
		boolean creada = false;
		//si ya existe no hago nada
		if(conn.tableExists("trabaja")) {
			return false;
		}
		String sql = "CREATE TABLE trabaja ("
				+ "  id_empleado INT UNSIGNED NOT NULL,"
				+ "  id_local INT UNSIGNED NOT NULL,"
				+ "  fecha_inicio DATE NOT NULL,"
				+ "  fecha_fin DATE,"
				+ "  PRIMARY KEY (fecha_inicio, id_empleado, id_local),"
				+ "  FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),"
				+ "  FOREIGN KEY (id_local) REFERENCES local(id_local)"
				+ ");";
		int ok = conn.update(sql);
		if(ok == 0) {
			creada = true;
		}
		return creada;
	}
	
	
	//FUNCIONA BIEN
	boolean insertEntry() {
		conn.connect();//me conecto a la db
		boolean insertado = false;
		if (DBSync) {
			//si no exite la tabla la creo antes de insertar nada
			if (!conn.tableExists("trabaja")) {
				createTable();
			}
			String sql = "INSERT INTO trabaja (id_empleado, id_local, fecha_inicio, fecha_fin) VALUES (?,?,?,?)";
			ArrayList<Object> params = new ArrayList<>();
			params.add(id_empleado);
			params.add(id_local);
			//si son null los parametros en el constructor los pongo a valor centinela
			params.add(fecha_inicio != null ? fecha_inicio : DBConnection.NULL_SENTINEL_DATE);
			params.add(fecha_fin != null ? fecha_fin : DBConnection.NULL_SENTINEL_DATE);
			int filas = conn.update(sql, params);
			insertado = filas == 1;
			// no se ha podido insertar el elemento
			if(filas != 1) {
				this.id_empleado = 0;
				this.id_local = 0;
				this.fecha_inicio = DBConnection.NULL_SENTINEL_DATE;
				DBSync = false;
				insertado = false;
			}
		}
		return insertado;
	}
	
	
	//FUNCIONA BIEN
	boolean updateEntry() {
		conn.connect();//me conecto a la db
		boolean actualizado = false;
		if (DBSync) {
			String sql = "UPDATE trabaja SET fecha_fin=? WHERE id_empleado=? AND id_local=? AND fecha_inicio=?"; 
			ArrayList<Object> params = new ArrayList<>();
			//si son null los parametros en el constructor los pongo a valor centinela
			params.add(fecha_fin != null ? fecha_fin : DBConnection.NULL_SENTINEL_DATE);
			params.add(id_empleado);
			params.add(id_local);
			params.add(fecha_inicio);
			int filas = conn.update(sql, params);
			if (filas > 0)
				actualizado = true;
		}
		return actualizado;
	}
	
	//FUNCIONA BIEN
	boolean deleteEntry() {
		conn.connect();//me conecto a la db
		boolean eliminado = false;
		if (DBSync) {
			String sql = "DELETE FROM trabaja WHERE id_empleado=? AND id_local=? AND fecha_inicio=?"; //borra el empleado con id =?
			ArrayList<Object> params = new ArrayList<>();
			params.add(id_empleado);
			params.add(id_local);
			params.add(fecha_inicio);
			int filas = conn.update(sql, params);
			if (filas == 1)
				eliminado = true;
		}
		return eliminado;
	}
	
	//FUNCIONA BIEN
	void getEntryChanges() {
		conn.connect();//me conecto a la db
		if (DBSync) {
			String sql = "SELECT * FROM trabaja WHERE id_empleado=? AND id_local=? AND fecha_inicio=?";
			ArrayList<Object> params = new ArrayList<>();
			params.add(id_empleado);
			params.add(id_local);
			params.add(fecha_inicio);

			try {
				ResultSet rs = conn.query(sql, params);

				if (rs.next()) {
					//obtengo los valores y los meto en los atributos del objeto
					fecha_fin = rs.getDate("fecha_fin");
				}
			} catch (SQLException e) {
				e.getMessage();
			}
		}
	}
}
