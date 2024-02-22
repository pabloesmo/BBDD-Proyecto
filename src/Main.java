package panaderias;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Main {

	public static Scanner scanner;
	
	public static void main (String[] args) {
		DBConnection dbc = new DBConnection("localhost:3306", 3306, "panaderia_user", "panaderia_pass", "panaderias");
		//System.out.println("Conectando...\n");
		//dbc.connect();
		//////////////////////////////////////////////////////////////////////////////////
		java.sql.Date sqlInitialDate = java.sql.Date.valueOf("1900-01-01");
		java.sql.Date sqlFinalDate = java.sql.Date.valueOf("2023-05-27");	

		java.sql.Date sqlNewFinalDate = java.sql.Date.valueOf("1492-10-12");
		
		//Empleado e1 = new Empleado(1, null, "Pedro", "Quevedo", null, dbc, true);
		//Local l = new Local(3, false, "Perfumeria", "Goyard", dbc, true);		
		//Trabaja t = new Trabaja(1, 1, sqlInitialDate, sqlNewFinalDate, dbc, true);
		
		//Empleado e2 = new Empleado(2, "19192", "Cruz", "Cafune", "Cibi", dbc, true);
		//Local l1 = new Local(4, true, "jdwidj", "jdd", dbc, true);
		//Trabaja t1 = new Trabaja(1, 2, sqlInitialDate, sqlNewFinalDate, dbc, true);
		

		//Empleado e = new Empleado(3, "922 928", "Inserto", "en el", "mismo id", dbc, true);
		
		//System.out.println("Al inicio la sincronizacion es = " + e.DBSync);
		//boolean insertado = e.insertEntry();
		//System.out.println(insertado);
		//System.out.println("El id del empleado ahora es: " + e.getId_empleado() + "(el valor centinela)");
		//System.out.println("La sincronizacion es ahora = " + e.DBSync);
		
		//boolean insertado = e.insertEntry();
		//System.out.println(insertado);
		
		//boolean actualizado = t.updateEntry();
		//System.out.println(actualizado);
	
		//boolean eliminado = l.deleteEntry();
		//System.out.println(eliminado);
		
		
		//GET FROM CSV PARA EMPLEADO
		/////////////////////////////////////////
		/*ArrayList<Empleado> empleados = DataManager.getEmpleadosFromCSV("/Users/Pablo/Desktop/empleados.csv", dbc, true);
		for (int i=0 ; i<empleados.size(); i++) {
			System.out.println(empleados.get(i).toString());
		}*/
		
		
		//GET FROM CSV PARA LOCAL
		/////////////////////////////////////////
		/*ArrayList<Local> locales = DataManager.getLocalesFromCSV("/Users/Pablo/Desktop/locales.csv", dbc, true);
		for (int i=0 ; i<locales.size(); i++) {
			System.out.println(locales.get(i).toString());
		}*/
		
		
		
		//GET FROM DB PARA EMPLEADO
		/////////////////////////////////////////
		/*System.out.println("EMPLEADOS");
		ArrayList<Empleado> empleados = DataManager.getEmpleadosFromDB(dbc,false);	
		for (int i=0 ; i<empleados.size(); i++) {
			System.out.println(empleados.get(i).toString());
		}*/
			
		
		//GET FROM DB PARA LOCAL
		/////////////////////////////////////////
		/*System.out.println("LOCALES");
		ArrayList<Local> locales = DataManager.getLocalesFromDB(dbc, true);
		for (int i=0 ; i<locales.size(); i++) {
			System.out.println(locales.get(i).toString());
		}*/	
		
		
		
		
		//DESTROY PARA EMPLEADO
		/////////////////////////////////////////
		/*Empleado e = new Empleado(4, dbc, true);
		//aqui salen los atributos guay
		e.getEntryChanges();
		System.out.println(e.toString());
		/////////////////////////////////////////
		e.destroy();
		e.getEntryChanges();
		//aqui deberia salir todo en null
		System.out.println(e.toString());*/
		
		
		
		//DESTROY PARA LOCAL
		/////////////////////////////////////////
		/*Local l = new Local(4, dbc, true);
		// aqui salen los atributos guay
		l.getEntryChanges();
		System.out.println(l.toString());
		/////////////////////////////////////////
		l.destroy();
		l.getEntryChanges();
		// aqui deberia salir todo en null
		System.out.println(l.toString());*/
		
		
		
		//DESTROY PARA TRABAJA
		/////////////////////////////////////////
		/*Trabaja t = new Trabaja(1, 2, sqlInitialDate, dbc, true);
		// aqui salen los atributos guay
		t.getEntryChanges();
		System.out.println(t.toString());
		/////////////////////////////////////////
		t.destroy();
		t.getEntryChanges();
		// aqui deberia salir todo en null
		System.out.println(t.toString());*/
		
		
		
		
		//GET ENTRY CHANGES DE EMPLEADO
		////////////////////////////////////////
		//aqui devuelvo Luis Miguel...
		/*Empleado e2 = new Empleado(3, dbc, true);
		e2.getEntryChanges();
		System.out.println("AQUI DOY EL ACTUAL : \n");
		System.out.println(e2.toString());
		////////////////////////////////////////
		
		//aqui cambio a Luis miguel por Cruz Cafune
		Empleado e1 = new Empleado(3, "923 928", "Cruz", "Cafune", "Cibi", dbc, true);
		e1.updateEntry();
		System.out.println("\nAQUI LE CAMBIO Y ACTUALIZO: \n");
		/////////////////////////////////////////
		
		//aqui me devuelve los valores de cruzzi
		System.out.println("\nAQUI DOY EL NUEVO: \n");
		e1.getEntryChanges();
		System.out.println(e1.toString());*/
		
		
		
		//GET ENTRY CHANGES DE LOCAL
		////////////////////////////////////////
		/*Local l = new Local(1, dbc, true);
		l.getEntryChanges();
		System.out.println("AQUI DOY EL ACTUAL: \n");
		System.out.println(l.toString());
		////////////////////////////////////////
		
		//aqui cambio a la zapateria por un estanco
		Local l2 = new Local(1, false, "Estanco", null, dbc, true);
		l2.updateEntry();
		System.out.println("\nAQUI LE CAMBIO Y ACTUALIZO: \n");
		/////////////////////////////////////////
		
		//aqui me devuelve los valores de cruzzi
		System.out.println("\nAQUI DOY EL NUEVO: \n");
		l2.getEntryChanges();
		System.out.println(l2.toString());*/
		
		
		
		
		//GET ENTRY CHANGES DE TRABAJA
		////////////////////////////////////////
		/*Trabaja t = new Trabaja(1, 1, sqlInitialDate, dbc, true);
		t.getEntryChanges();
		System.out.println("AQUI DOY LA RELACION INICIAL: \n");
		System.out.println(t.toString());
		////////////////////////////////////////

		// aqui cambio a la zapateria por un estanco
		Trabaja t2 = new Trabaja(1, 1, sqlInitialDate, sqlNewFinalDate, dbc, true);
		t2.updateEntry();
		System.out.println("\nAQUI LE CAMBIO Y ACTUALIZO: \n");
		/////////////////////////////////////////

		// aqui me devuelve los valores de cruzzi
		System.out.println("\nAQUI DOY EL NUEVO: \n");
		t2.getEntryChanges();
		System.out.println(t2.toString());*/
		
		
		
		
		
		/*ArrayList<Empleado> arr = DataManager.getEmpleadosFromDB(dbc, true);
		for (int i=0; i < arr.size(); i++) {
			System.out.println(arr.toString());
		}*/
		
		 
		
		/*Empleado e1 = new Empleado(10, null, null, null, null, dbc, true);
		boolean actualizado = e1.updateEntry();
		System.out.println(actualizado);*/
		
		
		// INSERT TABLE 
		 /*Empleado cmd = new Empleado(1,"10","Pablo","Esgueva","Moro",dbc,false);
		 cmd.insertEntry();*/
		

		// UPDATE SIMPLE
		/*
		 * String sql = "UPDATE empleado SET nombre = 'Pablo' WHERE id_empleado = 1";
		 * int rs = dbc.update(sql); System.out.println("Numero de filas afectadas = " +
		 * rs);
		 */

		// UPDATE SIMPLE
		/*
		 * String sql =
		 * "UPDATE empleado SET nombre = ?, n_ss = ? WHERE id_empleado = ?";
		 * ArrayList<Object> parametros = new ArrayList<>(); parametros.add("Maria");
		 * parametros.add("30"); parametros.add(2);
		 * 
		 * 
		 * //UPDATE CON ARRAYLIST int filasAfectadas = dbc.update(sql, parametros);
		 * if(filasAfectadas == -1) {
		 * System.out.println("Se produjo un error al actualizar"); } else {
		 * System.out.println("Se actualizaron " + filasAfectadas +
		 * " fila/s correctamente"); }
		 */

		// QUERY SIMPLE
		/*
		 * String sql = "SELECT * FROM empleado"; ResultSet res = dbc.query(sql);
		 * 
		 * if(res == null) { System.out.println("Hubo un problema en la consulta"); }
		 * else { try { while (res.next()) { int id = res.getInt("id_empleado"); String
		 * nombre = res.getString("nombre"); String apellido1 =
		 * res.getString("apellido1"); String apellido2 = res.getString("apellido2");
		 * String numSs = res.getString("n_ss");
		 * 
		 * System.out.println("<Id>: " + id + " <Nombre>: " + nombre + " <Apellido1>: "
		 * + apellido1 + " <Apellido2>: " + apellido2 + " <num_ss>: " + numSs); } }
		 * catch(SQLException e) {System.out.println("Error al procesar el resultado: "
		 * + e.getMessage());} }
		 */

		// QUERY CON ARRAYLIST
		/*
		 * String sql = "SELECT * FROM empleado WHERE id_empleado = ? AND n_ss = ?";
		 * ArrayList<Object> params = new ArrayList<>(); params.add(1);
		 * params.add("10"); ResultSet res = dbc.query(sql,params); while (res.next()) {
		 * int id = res.getInt("id_empleado"); String nombre = res.getString("nombre");
		 * String apellido1 = res.getString("apellido1"); String apellido2 =
		 * res.getString("apellido2"); String numSs = res.getString("n_ss");
		 * System.out.println("<Id>: " + id + " <Nombre>: " + nombre + " <Apellido1>: "
		 * + apellido1 + " <Apellido2>: " + apellido2 + " <num_ss>: " + numSs); }
		 * res.close();
		 */

		// CREATE TABLE
		
		 /*Empleado e1 = new Empleado(0, dbc, false); 
		 boolean res = e1.createTable();
		 System.out.println((res == false)? "Ya existe" : "Creada");*/
		 

		 /*Local l1 = new Local(0, dbc, false); 
		 boolean res1 = l1.createTable();
		 System.out.println((res1 == false)? "Creada" : "Ya existe");
		 

		 Trabaja t1 = new Trabaja(0, 0, null, dbc, false); 
		 boolean res2 =
		 t1.createTable(); 
		 System.out.println((res2 == false)? "Creada" : "Ya existe");*/
		 

		// INSERT ENTRY
		
		 /*Empleado e1 = new Empleado(3, "20", "Kanye", "West", "Rapper", dbc, true);
		 boolean res = e1.insertEntry();
		 System.out.println("El resultado de insertar un empleado es = " + res);*/
		

		/*
		 * Local l1 = new Local(1, true, "Alcalá 20", "Descripcion 6", dbc, true);
		 * boolean res = l1.insertEntry();
		 * System.out.println("El resultado de insertar un local es = " + res);
		 */

		
		 /*java.sql.Date sqlInitialDate = java.sql.Date.valueOf("2032-05-11");
		 java.sql.Date sqlEndDate = java.sql.Date.valueOf("2009-09-05");
		  
		 Trabaja t1 = new Trabaja(1, 1, sqlInitialDate, sqlEndDate, dbc, true);
		 boolean res = t1.insertEntry();
		 System.out.println("El resultado de insertar un trabaja es = " + res);*/
		 

		// UPDATE ENTRY
		/*
		 * Empleado e1 = new Empleado(2, "15", "Drake", "Graham", "Rapper", dbc, true);
		 * boolean res = e1.updateEntry();
		 * System.out.println("El resultado de actualizar un empleado es = " + res);
		 */

		/*
		 * Local l1 = new Local(6, true, "Av America 21", "Esta la he metido yo", dbc,
		 * true); boolean res = l1.updateEntry();
		 * System.out.println("El resultado de actualizar un local es = " + res);
		 */

		/*
		 * java.sql.Date fI = java.sql.Date.valueOf("2022-06-01"); java.sql.Date fF =
		 * java.sql.Date.valueOf("2006-03-15"); Trabaja t1 = new Trabaja(1, 1, fI, fF,
		 * dbc, false); boolean actualizado = t1.updateEntry();
		 * System.out.println(actualizado == true? "\nActualizado!" :
		 * "\nNo se pudo actualizar");
		 */

		// DELETE ENTRY
		/*
		 * Empleado e1 = new Empleado(3, dbc, true); boolean res = e1.deleteEntry();
		 * System.out.println("El resultado de eliminar un empleado es = " + res);
		 */

		/*
		 * Local l1 = new Local(7, dbc, true); boolean res = l1.deleteEntry();
		 * System.out.println("El resultado de eliminar un local es = " + res);
		 */

		/*
		 * Trabaja t1 = new Trabaja(2,2, java.sql.Date.valueOf("2032-05-11"), dbc,
		 * true); boolean res = t1.deleteEntry();
		 * System.out.println("El resultado de eliminar un local es = " + res);
		 */

		
		 /*ArrayList<Empleado> empleados = DataManager.getEmpleadosFromCSV("/Users/Pablo/Desktop/empleados.csv", dbc, true);
		 for(Empleado e : empleados) {
			 System.out.println(e);
		 }
		 

		ArrayList<Local> locales = DataManager.getLocalesFromCSV("/Users/Pablo/Desktop/locales.csv", dbc, true);
		for(Local l : locales) {
			System.out.println(l);
		}*/
		 
	
		
		/*ArrayList<Empleado> empleados = DataManager.getEmpleadosFromDB(dbc, false);
		 for (Empleado e : empleados) { 
			 System.out.println(e);
		}
		
		 System.out.println("\n");
		 
		ArrayList<Local> locales = DataManager.getLocalesFromDB(dbc, false);
		for (Local l : locales) { 
			System.out.println(l);
		}*/
	
		/*Empleado e = new Empleado(4, dbc, true);
		e.setNombre("Cristina");
		System.out.println(e.getNombre());*/

		
		 /*ArrayList<Local> locales = DataManager.getLocalesFromDB(dbc, true); 
		 
		 for(Local l : locales) { 
			 System.out.println(l); 
		 }*/
		

		// GET_ENTRY_CHANGES

		/*Empleado e1 = new Empleado(3, dbc, true);
		e1.getEntryChanges();
		System.out.println("Nombre:" + e1.getNombre() + "\nNum_Ss:" + e1.getN_ss() + "\nApellido1:" + e1.getApellido1()
				+ "\nApellido2:" + e1.getApellido2());
		*/
		 

		/*
		 * Local l1 = new Local(3, dbc, true); l1.getEntryChanges();
		 * System.out.println("Tiene_Cafeteria:" + l1.getTiene_cafeteria() +
		 * "\nDireccion:" + l1.getDireccion() + "\nDescripcion:" + l1.getDescripcion());
		 */

		/*
		 * java.sql.Date sqlInitialDate = java.sql.Date.valueOf("2022-06-01"); Trabaja
		 * t1 = new Trabaja(1, 1,sqlInitialDate , dbc, true); t1.getEntryChanges();
		 * System.out.println("Fecha_inicio:" + t1.getFecha_inicio() + "\nFecha_fin:" +
		 * t1.getFecha_fin());*
		 * 
		 * 
		 * 
		 * /*ArrayList<Object> arr = new ArrayList<>(); arr.add(fI); arr.add(fF);
		 * for(int i=0; i<arr.size(); i++) {
		 * System.out.println(arr.get(i).getClass().getName()); }
		 */

		/*
		 * Trabaja t1 = new Trabaja(2, 2, fI, fF, dbc, false); boolean insertado =
		 * t1.insertEntry();
		 * System.out.println("El resultado de insertar una entry es = " + insertado);
		 */

		/*
		 * String sql =
		 * "UPDATE trabaja SET fecha_fin=? WHERE id_empleado=? AND id_local=? AND fecha_inicio=?"
		 * ; ArrayList<Object> params = new ArrayList<>();
		 * params.add(java.sql.Date.valueOf("2006-03-15")); params.add(1);
		 * params.add(1); params.add(java.sql.Date.valueOf("2002-01-19")); int filas =
		 * dbc.update(sql, params); System.out.println(filas);
		 */

		// PRUEBAS DESTROY()
		/////////////////////////////////////////////////////////////////////////////////

		
		/*Empleado e1 = new Empleado(0, "6666-6666", "Metido por", "mi mismo", "esto", dbc, true);
		boolean res = e1.insertEntry();
		System.out.println(res);*/
		 

		
		/*Empleado e = new Empleado(7, dbc, true);
		System.out.println("ANTES: " + e.DBSync);
		e.destroy();
		System.out.println(e.getId_empleado());
		System.out.println(e.getNombre());
		System.out.println(e.getApellido1());
		System.out.println(e.getApellido2());
		System.out.println("DESPUES: " + e.DBSync);*/		 

		/////////////////////////////////////////////////////////////////////////////////

		
		/*Local l1 = new Local(0, true, "metida por", "mi y solo yo", dbc, true);
		boolean res = l1.insertEntry();
		System.out.println(res);*/
		 

		
		/*Local l = new Local(6, dbc, true);
		System.out.println("ANTES: " + l.DBSync);
		l.destroy();
		System.out.println(l.getId_local());
		System.out.println(l.getTiene_cafeteria());
		System.out.println(l.getDireccion());
		System.out.println(l.getDescripcion());
		System.out.println("DESPUES: " + l.DBSync);*/
		 

		///////////////////////////////////////////////////////////////////////////////////

		
		/*Trabaja t1 = new Trabaja(2, 2, sqlInitialDate, sqlFinalDate, dbc, true);
		boolean res = t1.insertEntry();
		System.out.println(res);*/		 

		
		/*Trabaja t = new Trabaja(2, 2, sqlInitialDate, dbc, true);
		System.out.println("ANTES: " + t.DBSync);
		t.destroy();
		System.out.println(t.getId_empleado());
		System.out.println(t.getId_local());
		System.out.println(t.getFecha_inicio());
		System.out.println(t.getFecha_fin());
		System.out.println("DESPUES: " + t.DBSync);*/
		 
		
		
		//////////////////////////////////////////////////////////////////////////////////
		dbc.close();
		//System.out.println("\n¡Fin de la conexion!");
	}
}
