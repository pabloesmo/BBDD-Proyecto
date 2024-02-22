package panaderias;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;


public class Local extends DBTable {
	
	private int id_local;
	private boolean tiene_cafeteria;
	private String direccion;
	private String descripcion;
	
	public Local(int id_local, DBConnection conn, boolean DBSync) {
		super(conn, DBSync);
		this.id_local = id_local;
		conn.connect();
		if (DBSync) {
			insertEntry();
		}
	}
	
	public Local(int id_local, boolean tiene_cafeteria, String direccion, String descripcion, DBConnection conn, boolean DBSync) {
		super(conn, DBSync);
		this.id_local = id_local;
		this.tiene_cafeteria = tiene_cafeteria;
		this.direccion = direccion;
		this.descripcion = descripcion;
		conn.connect();
		if (DBSync) {
			insertEntry();
		}
	}
	
	// FUNCIONA
	// Devuelve el id_local del Local
	public int getId_local() {
		if (DBSync) {
			getEntryChanges();
		}
		return id_local;
	}

	// FUNCIONA
	// Devuelve el valor de tiene_cafeteria del Local
	public boolean getTiene_cafeteria() {
		if (DBSync) {
			getEntryChanges();
		}
		return tiene_cafeteria;
	}

	// FUNCIONA
	// Establece el valor de tiene_cafeteria del Local
	public void setTiene_cafeteria(boolean tiene_cafeteria) {
		if (DBSync) {
			getEntryChanges();
			updateEntry();
		}
		this.tiene_cafeteria = tiene_cafeteria;
	}

	// FUNCIONA
	// Devuelve la dirección del Local
	public String getDireccion() {
		if (DBSync) {
			getEntryChanges();
		}
		return direccion;
	}

	// FUNCIONA
	// Establece la dirección del Local
	public void setDireccion(String direccion) {
		if (DBSync) {
			getEntryChanges();
			updateEntry();
		}
		this.direccion = direccion;
	}

	// FUNCIONA
	// Devuelve la descripción del Local
	public String getDescripcion() {
		if (DBSync) {
			getEntryChanges();
		}
		return descripcion;
	}

	// FUNCIONA
	// Establece la descripción del Local
	public void setDescripcion(String descripcion) {
		if (DBSync) {
			getEntryChanges();
			updateEntry();
		}
		this.descripcion = descripcion;
	}
	
	//FUNCIONA BIEN
	// Elimina el empleado de la base de datos
	public void destroy() {
		conn.connect();//me conecto a la db
		String sql = "DELETE FROM local WHERE id_local=?"; // se borra la entrada correspondiente en la tabla "local"
		ArrayList<Object> params = new ArrayList<>();
		params.add(id_local);
		conn.update(sql, params);//se actualizan los valores de los atributos a los valores nulos
		id_local = DBConnection.NULL_SENTINEL_INT;
		tiene_cafeteria = false;
		direccion = DBConnection.NULL_SENTINEL_VARCHAR;
		descripcion = DBConnection.NULL_SENTINEL_VARCHAR;
		DBSync = false;
	}
	
	@Override
	/*public String toString() {
	    return "Local{" +
	            "id_local=" + id_local +
	            ", tiene_cafeteria=" + tiene_cafeteria +
	            ", direccion='" + direccion + '\'' +
	            ", descripcion='" + descripcion + '\'' +
	            '}';
	}*/

	
	
	//FUNCIONA BIEN
	boolean createTable() {
		conn.connect(); //me conecto a la db
		boolean creada = false;
		//si ya existe no hago nada
		if(conn.tableExists("local")) {
			return false;
		}
		String sql = "CREATE TABLE local ("
				+ "  id_local INT UNSIGNED,"
				+ "  tiene_cafeteria INT,"
				+ "  direccion VARCHAR(100),"
				+ "  descripcion VARCHAR(100),"
				+ "  PRIMARY KEY (id_local));";
		int ok = conn.update(sql);
		if(ok == 0) {
			creada = true;
		}
		return creada;
	}
	
	//FUNCIONA BIEN
	boolean insertEntry() {
		conn.connect();
		boolean insertado = false;
		if (DBSync) {
			//si no exite la tabla la creo antes de insertar nada
			if (!conn.tableExists("local")) {
				createTable();
			}
			String sql = "INSERT INTO local(id_local,tiene_cafeteria, direccion, descripcion) VALUES (?,?,?,?)";
			ArrayList<Object> params = new ArrayList<>();
			params.add(id_local);
			//si son null los parametros en el constructor los pongo a valor centinela
			params.add(tiene_cafeteria);
			params.add(direccion != null ? direccion : DBConnection.NULL_SENTINEL_VARCHAR);
			params.add(descripcion != null ? descripcion : DBConnection.NULL_SENTINEL_VARCHAR);
			int filas = conn.update(sql, params);
			insertado = filas == 1;
			//no se ha podido insertar el elemento
			if (filas != 1) {
				this.id_local = 0;
				DBSync = false;
				insertado = false;
			}
		}
		return insertado;
	}
	
	//FUNCIONA BIEN
	boolean updateEntry() {
		conn.connect(); //me conecto a la db
		boolean actualizado = false;
		if (DBSync) {
			String sql = "UPDATE local SET tiene_cafeteria=?, direccion =?, descripcion =? WHERE id_local=?";
			ArrayList<Object> params = new ArrayList<>();
			//si son null los parametros en el constructor los pongo a valor centinela
			params.add(tiene_cafeteria);
			params.add(direccion != null ? direccion : DBConnection.NULL_SENTINEL_VARCHAR);
			params.add(descripcion != null ? descripcion : DBConnection.NULL_SENTINEL_VARCHAR);
			params.add(id_local);
			int filas = conn.update(sql, params);
			if (filas > 0)
				actualizado = true;
		}
		return actualizado;
	}
	

	//FUNCIONA BIEN
	boolean deleteEntry() {
		conn.connect(); //me conecto a la db
		boolean eliminado = false;
		if (DBSync) {
			String sql = "DELETE FROM local WHERE id_local = ?"; //borra el local con id =?
			ArrayList<Object> params = new ArrayList<>();
			params.add(id_local);
			int filas = conn.update(sql, params);
			if (filas == 1)
				eliminado = true;
		}
		return eliminado;
	}
	
	//FUNCIONA BIEN
	void getEntryChanges() {
		conn.connect(); //me conecto a la db
		if (DBSync) {
			String sql = "SELECT * FROM local WHERE id_local = ?";
			ArrayList<Object> params = new ArrayList<>();
			params.add(id_local);

			try {
				ResultSet rs = conn.query(sql, params);

				if (rs.next()) {
					//obtengo los valores y los meto en los atributos del objeto
					tiene_cafeteria = rs.getBoolean("tiene_cafeteria");
					direccion = rs.getString("direccion");
					descripcion = rs.getString("descripcion");
				}
			} catch (SQLException e) {
				e.getMessage();
			}
		}
	}
}
