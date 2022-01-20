package es.redmetro.dam2.utilidades;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GestorConfiguracion {
	private static Properties propiedades=null;
	public static String getInfoConfiguracion(String clave) {
		String valor = null;
		// Se carga la informaci�n del fichero de configuraci�n (una vez)
		if(propiedades ==null) {
			InputStream inputStream=null;
			propiedades = new Properties();
			try {
				inputStream = new FileInputStream("config/red-metro.properties");
				propiedades.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Se devuelve un valor.
		valor=propiedades.getProperty(clave);
		
		return valor;
	}

}
