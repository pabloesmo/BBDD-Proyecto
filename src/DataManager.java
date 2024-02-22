package panaderias;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataManager {
	
	//FUNCIONA BIEN
	public static ArrayList<Empleado> getEmpleadosFromDB(DBConnection conn, boolean sync) {
	    conn.connect();
	    ArrayList<Empleado> empleados = new ArrayList<>();
	    //si la tabla no existe se devuelve null
	    if(!conn.tableExists("empleado")) {
	    	return null;
	    }
	    try {
	    	//cojo los empleados de la base d datos
	        ResultSet rs = conn.query("SELECT * FROM empleado");
	        boolean isEmpty = true;
	        while (rs.next()) {
	            isEmpty = false;
	            //se crea con sync a false
	            Empleado e = new Empleado(rs.getInt("id_empleado"), conn, false);
	            e.setN_ss(rs.getString("n_ss"));
	            e.setNombre(rs.getString("nombre"));
	            e.setApellido1(rs.getString("apellido1"));
	            e.setApellido2(rs.getString("apellido2"));
	            //se pone sync a true tras crearlo
	            e.setSync(true);
	            //lo meto en el arraylist
	            empleados.add(e);
	        }
	        //cierro resultset (ya acabo la query)
	        rs.close();
	        if (isEmpty) {
	            // La tabla está vacía, retornar ArrayList vacío
	            return empleados;
	        }
	    } catch (SQLException e) {
	    	e.getMessage();
	        return null;
	    }
	    return empleados;
	}
	
	//FUNCIONA BIEN
	/*Con PK repetida se pone a NULL*/
	
	//FUNCIONA BIEN
	public static ArrayList<Empleado> getEmpleadosFromCSV(String filename, DBConnection conn, boolean sync) {
	    conn.connect();
	    ArrayList<Empleado> empleados = new ArrayList<>();
	    try {
	        File archivo = new File(filename);
	        Scanner scan = new Scanner(archivo);
	        
	        if (scan.hasNextLine()) {
	            scan.nextLine();
	        }
	        while (scan.hasNext()) {
	            String linea = scan.nextLine();
	            String[] campos = linea.split(";"); //SEPARADOR
	            //si esta vacio le pongo un 0 (variable null)
	            int numero = (campos.length >= 1 && !campos[0].isEmpty()) ? Integer.parseInt(campos[0]) : 0;
	            //si esta vacio le pongo (variable null)
	            String n_ss = (campos.length >= 2 && !campos[1].isEmpty()) ? campos[1] : null;
	            //si esta vacio le pongo (variable null)
	            String nombre = (campos.length >= 3 && !campos[2].isEmpty()) ? campos[2] : null;
	            //si esta vacio le pongo (variable null)
	            String apellido1 = (campos.length >= 4 && !campos[3].isEmpty()) ? campos[3] : null;
	            //si esta vacio le pongo (variable null)
	            String apellido2 = (campos.length >= 5 && !campos[4].isEmpty()) ? campos[4] : null;

	            Empleado e = new Empleado(numero, n_ss, nombre, apellido1, apellido2, conn, sync);
	            if (sync) {
	                // aquí si no se puede insertar por duplicidad de PK se pone a NULL
	                try {
	                	//es insertEntry el que se encarga de que si son null introducirlos como variables centinela en la Bd.
	                    e.insertEntry();
	                } catch (Exception ex) {
	                    System.err.println("Error al insertar el empleado: " + ex.getMessage());
	                    e = null;  //el empleado a null en caso de error
	                }
	            }
	            //meto el empleado construido en esa iteracion en el array.
	            empleados.add(e);
	        }
	        scan.close();
	    } catch (FileNotFoundException e) {
	        e.getMessage();
	        return null;
	    } catch (Exception e) {
	        e.getMessage();
	        return null;
	    }
	    return empleados;
	}

	
	
	//FUNCIONA BIEN
	//FUNCIONA BIEN
	
	
	//FUNCIONA BIEN
	public static ArrayList<Local> getLocalesFromDB(DBConnection conn, boolean sync) {
		conn.connect();
		if(!conn.tableExists("local")) {
	    	return null;
	    }
		ArrayList<Local> locales = new ArrayList<>();
		//si la tabla no existe se devuelve null
		try {
			//cojo los empleados de la base d datos
			ResultSet rs = conn.query("SELECT * FROM local");
			boolean isEmpty = true;
			while(rs.next()) {
				//se crea con sync a false
				Local l = new Local(rs.getInt("id_local"), conn, false);
				l.setTiene_cafeteria(rs.getBoolean("tiene_cafeteria"));
				l.setDireccion(rs.getString("direccion"));
				l.setDescripcion(rs.getString("descripcion"));
				//se pone sync a true tras crearlo
				l.setSync(true);
				//lo meto en el arraylist
				locales.add(l);
			}
			//cierro resultset (ya acabo la query)
			rs.close();
			  if (isEmpty) {
		            // La tabla está vacía, retornar ArrayList vacío
		            return locales;
			  }
		} catch (SQLException e) {
			e.getMessage();
			return null;
		}
		return locales;
	}
	
	
	
	
	//FUNCIONA BIEN
	public static ArrayList<Local> getLocalesFromCSV(String filename, DBConnection conn, boolean sync) {
		conn.connect();
		ArrayList<Local> locales = new ArrayList<>();
		try {
			File archivo = new File(filename);
			Scanner scan = new Scanner(archivo);
			
			if (scan.hasNextLine()) {
				scan.nextLine();
			}
			while (scan.hasNext()) {
				String linea = scan.nextLine();
				String[] campos = linea.split(";");
				//si esta vacio le pongo un 0 (variable null)
				int id_local = (campos.length >= 1 && !campos[0].isEmpty()) ? Integer.parseInt(campos[0]) : 0;
				//si esta vacio le pongo a false (variable null)
				boolean tiene_cafeteria = (campos.length >= 2 && !campos[1].isEmpty()) ? (Integer.parseInt(campos[1]) == 1) : false;
				//si esta vacio le pongo variable null
				String direccion = (campos.length >= 3 && !campos[2].isEmpty()) ? campos[2] : null;
				//si esta vacio le pongo (variable null)
				String descripcion = (campos.length >= 4 && !campos[3].isEmpty()) ? campos[3] : null;

	            Local l = new Local(id_local, tiene_cafeteria, direccion, descripcion, conn, sync);
	            if (sync) {
	            	// aquí si no se puede insertar por duplicidad de PK se pone a NULL
	                try {
	                	//es insertEntry el que se encarga de que si son null introducirlos como variables centinela en la Bd.
	                    l.insertEntry();
	                } catch (Exception ex) {
	                    System.err.println("Error al insertar el local: " + ex.getMessage());
	                    l = null; //el empleado a null en caso de error
	                }
	            }
	          //meto el local construido en esa iteracion en el array.
	            locales.add(l);
	        }
	        scan.close();
	    } catch (FileNotFoundException e) {
	        e.getMessage();
	        return null;
	    } catch (Exception e) {
	        e.getMessage();
	        return null;
	    }
	    return locales;
	}
}