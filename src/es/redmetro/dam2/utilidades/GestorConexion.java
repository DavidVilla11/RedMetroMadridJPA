package es.redmetro.dam2.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorConexion {
	//Conexi�n con JDBC 	
	public static Connection getConexion() {
		Connection conexion=null;
		try {
			String driver=GestorConfiguracion.getInfoConfiguracion("driver").trim();
		    Class.forName(driver);

		    String sgbd=GestorConfiguracion.getInfoConfiguracion("sgbd").trim();
			String servidor=GestorConfiguracion.getInfoConfiguracion("servidor").trim();
			String puerto=GestorConfiguracion.getInfoConfiguracion("puerto").trim();
			String nombreBBDD=GestorConfiguracion.getInfoConfiguracion("nombreBBDD").trim();
			String usuarioBBDD=GestorConfiguracion.getInfoConfiguracion("usuarioBBDD").trim();
			String clave=GestorConfiguracion.getInfoConfiguracion("clave").trim();
			
			String urlConexionBBDD="jdbc:" + sgbd + "://"+ servidor + ":" + puerto + "/" + nombreBBDD;
			// Acceso a informaci�n de un fichero de configuraci�n: Devuelve Properties
			System.out.println(urlConexionBBDD);
		    
			//Connection cn = DriverManager.getConnection("jdbc:mysql://servidor_bd:puerto/nombre_bd", "usuario", "contrase�a");
			conexion = DriverManager.getConnection(urlConexionBBDD, usuarioBBDD, clave);
		} catch (ClassNotFoundException ex) {
			System.out.println("No se encontro el Driver MySQL para JDBC.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conexion;
	}
}
