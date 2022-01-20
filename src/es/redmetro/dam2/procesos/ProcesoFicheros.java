package es.redmetro.dam2.procesos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import es.redmetro.dam2.dao.ICocheraDao;
import es.redmetro.dam2.dao.jdbc.CocheraJdbcDao;
import es.redmetro.dam2.excepciones.RedMetroException;
import es.redmetro.dam2.utilidades.GestorConfiguracion;
import es.redmetro.dam2.vo.Cochera;

public class ProcesoFicheros {
	public void procesarFicheroCocherasCSV() throws RedMetroException {
	
		// Consultar fichero, procesar informaci�n y cargar la lista de usuarios
		File fichero= new File(GestorConfiguracion.getInfoConfiguracion("ruta.cocheras.csv"));
		
		FileReader fr;
		BufferedReader br= null;
		String sLinea=null;
		boolean primerVez=true;
		try {
			fr = new FileReader(fichero, StandardCharsets.UTF_8);
			br= new BufferedReader(fr);
			while((sLinea=br.readLine())!=null){
				if(primerVez) {
					primerVez=false;
				}else{
					Cochera cochera=new Cochera();
					// Informaci�n de una l�nea
					String[] partesLinea=sLinea.split(";");
					String sOperacion = partesLinea[0];
					int operacion = Integer.parseInt(sOperacion);
					
					String sCodigoCochera = partesLinea[1];
					int codigoCochera=-1;
					if(sCodigoCochera!=null && !"".equals(sCodigoCochera) ) {
						codigoCochera = Integer.parseInt(sCodigoCochera);
					}
					
					String sEsDeposito = partesLinea[2];
					int esDeposito= Integer.parseInt(sEsDeposito);
					String nombre= partesLinea[3];
					String direccion= partesLinea[4];
					// Se carga la informaci�n de una cochera
					cochera.setCodigoCochera(codigoCochera);
					cochera.setDeposito(esDeposito);
					cochera.setNombre(nombre);
					cochera.setDireccion(direccion);;

					ICocheraDao operacionCochera = new CocheraJdbcDao();
					switch (operacion) {
						case 0: {
							System.out.println("No se hace nada.");
							break;
						}
						case 1: {
							System.out.println("Crear Cochera.");
							operacionCochera.crearCochera(cochera);
							break;
						}
						case 2: {
							System.out.println("Modificar Cochera.");
							operacionCochera.modificarCochera(cochera);
							break;							
						}
						case 3: {
							System.out.println("Borrar Cochera.");
							operacionCochera.borrarCochera(codigoCochera);
							break;
						}
						default:
							throw new IllegalArgumentException("Valor no esperado: " + operacion);
						}
					}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		
		// Una vez finalizado el procesamiento se borra el fichero.
		fichero.delete();
	}
	
	
	
	public void crearXMLCocheras (List<Cochera> listaCocheras) {
		Document documento = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			documento = dBuilder.newDocument();

			
			// Se crear el nodo base
			Element raiz = documento.createElement("red-metro");
			documento.appendChild(raiz);

			// Se crear el nodo raíz
			Element cocheras = documento.createElement("cocheras");
			raiz.appendChild(cocheras);

			// Informaci�n para nodos internos
			Element nodoCochera= null , nodoDatos = null ;
			Text  texto = null;
			for (Cochera cochera: listaCocheras) {

				// Se asigna el nodo/elemento usuario a elemento ra�z 
				nodoCochera = documento.createElement("cochera");
				cocheras.appendChild(nodoCochera);

				// Se carga informaci�n del identificador de Cochera
				nodoCochera.setAttribute("codigo", String.valueOf(cochera.getCodigoCochera()));
				
				// Se carga informaci�n del identificador de Cochera
				nodoCochera.setAttribute("deposito", String.valueOf(cochera.getDeposito()));

				// Se carga informaci�n nombre de cochera
				nodoDatos = documento.createElement("nombre");
				nodoCochera.appendChild(nodoDatos);
				String numDpto= String.valueOf(cochera.getNombre());
				texto = documento.createTextNode(numDpto);
				nodoDatos.appendChild(texto);
	
				// Se carga informaci�n nombre
				nodoDatos = documento.createElement("direccion");
				nodoCochera.appendChild(nodoDatos);
				texto = documento.createTextNode(cochera.getDireccion());
				nodoDatos.appendChild(texto);
			}
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			String rutaOrigen = GestorConfiguracion.getInfoConfiguracion("ruta.cochera.xml");
			Result output = new StreamResult(new File(rutaOrigen));
			Source input = new DOMSource(documento);
			transformer.transform(input, output);
		} catch(Exception e) {
			  e.printStackTrace();
		}		
	}

}