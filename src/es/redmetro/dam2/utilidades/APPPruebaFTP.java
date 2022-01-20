package es.redmetro.dam2.utilidades;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import es.redmetro.dam2.vo.*;
import es.redmetro.dam2.dao.hibernate.CocheraHibernateDao;
import es.redmetro.dam2.dao.hibernate.EstacionHibernateDao;
import es.redmetro.dam2.dao.hibernate.LineaHibernateDao;
import es.redmetro.dam2.dao.hibernate.TrenHibernateDao;
import es.redmetro.dam2.excepciones.RedMetroException;

public class APPPruebaFTP {
	
	public static void LecturaFilezillaTrenes(List<Tren> trenes) throws RedMetroException{
		//pruebaFTPsClient();
		
		TrenHibernateDao trenAccion = new TrenHibernateDao();
		CocheraHibernateDao cocheraAccion = new CocheraHibernateDao();
		EstacionHibernateDao estacionAccion = new EstacionHibernateDao();
		LineaHibernateDao lineaAccion = new LineaHibernateDao();
			
	    String server = "dam2.actividad.cf";
	    int port = 21;
	    String user = "aadd_dam2";
	    String pass = "aadd_dam2";

//	    FTPClient ftpClient = new FTPClient();
	    FTPSClient ftpClient = new FTPSClient("TLS", false);
	    FTPFile[] ficherosRaiz=null;
	    try {
			ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out))); // outputs all conversation to the console
			
	        ftpClient.connect(server, port);

	        ftpClient.enterLocalPassiveMode();
	        boolean success = ftpClient.login(user, pass);

	        if (!success) {
	            System.out.println("Could not login to the server");
	            return;
	        }
			// Set protection buffer size
			ftpClient.execPBSZ(0);
			// Set data channel protection to private
			ftpClient.execPROT("P");

//	        FTPFile[] ftpFiles = ftpClient.listFiles();
	        boolean cambio= ftpClient.changeToParentDirectory();
	        if(cambio)
	        		ficherosRaiz=ftpClient.listFiles();
	        System.out.println(ficherosRaiz);

	     // configuration code for ftpclient port, server etc
	        InputStream in = ftpClient.retrieveFileStream("./dam2_g4/datos.xml");
	        Document documento = readXmlEstacion(in);
			// Se obtiene la lista de nodos relacionado con 'departamento'
			NodeList nListIni = documento.getElementsByTagName("tren");
			System.out.println("Nº de departamentos: " + nListIni.getLength());
			for(int temp = 0; temp < nListIni.getLength(); temp++) {
				Node nNode = nListIni.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				    Element elemento = (Element) nNode;
			    	int cod_tren = Integer.parseInt(elemento.getAttribute("cod_tren"));
			    	
			    	NodeList modeloTren = elemento.getElementsByTagName("modelo");
			    	String modelo = modeloTren.item(0).getTextContent();
			    	
			    	NodeList empresaConstructora = elemento.getElementsByTagName("empresa_constructora");
			    	String empresa = empresaConstructora.item(0).getTextContent();
			    	
			    	NodeList FechaIncor = elemento.getElementsByTagName("fecha_incorporacion");
			    	java.util.Date formatter = null;
			    	
					try {
						formatter = new SimpleDateFormat("dd-MM-yyyy",Locale.GERMAN).parse(FechaIncor.item(0).getTextContent());
					} catch (DOMException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	java.sql.Date fecha = new java.sql.Date(formatter.getTime());
			    	
			    	NodeList contenidoCodCochera = elemento.getElementsByTagName("cod_cochera");
			    	int codCochera = Integer.parseInt(contenidoCodCochera.item(0).getTextContent());

			    	Cochera cochera = cocheraAccion.getEntirdadPorID(codCochera);
			    	if(cochera==null) {
			    		cochera = new Cochera();
			    		cochera.setCodigoCochera(codCochera);
			    		cocheraAccion.crear(cochera);
			    	}
			    	
			    	
			    	NodeList cod_linea = elemento.getElementsByTagName("cod_linea");
			    	int codLinea = Integer.parseInt(cod_linea.item(0).getTextContent());
			    	Linea linea = lineaAccion.getEntirdadPorID(codLinea);

			    	Tren tren = new Tren(cod_tren,modelo,fecha,empresa,linea,cochera);
			    	
			    	trenes.add(tren);

			    	
				}
			}
			        
	    } catch (IOException | ParserConfigurationException | SAXException ex) {
	        System.out.println("Oops! Something wrong happened");
	        ex.printStackTrace();
	    } finally {
	        try {
	            if (ftpClient.isConnected()) {
	                ftpClient.logout();
	                ftpClient.disconnect();
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	}

		 public static Document readXmlEstacion(InputStream is) throws ParserConfigurationException, SAXException, IOException  {
	     DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	     dbf.setValidating(false);
	     dbf.setIgnoringComments(false);
	     dbf.setIgnoringElementContentWhitespace(true);
	     dbf.setNamespaceAware(true);
	     // dbf.setCoalescing(true);
	     // dbf.setExpandEntityReferences(true);

	     DocumentBuilder db = null;
	     db = dbf.newDocumentBuilder();
	     // db.setErrorHandler( new MyErrorHandler());

	     return db.parse(is);
	 }


	public static void LecturaFilezillaEstaciones(List<Estacion> Estaciones){
		//pruebaFTPsClient();
		
		TrenHibernateDao trenAccion = new TrenHibernateDao();
		CocheraHibernateDao cocheraAccion = new CocheraHibernateDao();
		EstacionHibernateDao estacionAccion = new EstacionHibernateDao();
		LineaHibernateDao lineaAccion = new LineaHibernateDao();
		
	    String server = "dam2.actividad.cf";
	    int port = 21;
	    String user = "aadd_dam2";
	    String pass = "aadd_dam2";

//	    FTPClient ftpClient = new FTPClient();
	    FTPSClient ftpClient = new FTPSClient("TLS", false);
	    FTPFile[] ficherosRaiz=null;
	    try {
			ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out))); // outputs all conversation to the console
			
	        ftpClient.connect(server, port);

	        ftpClient.enterLocalPassiveMode();
	        boolean success = ftpClient.login(user, pass);

	        if (!success) {
	            System.out.println("Could not login to the server");
	            return;
	        }
			// Set protection buffer size
			ftpClient.execPBSZ(0);
			// Set data channel protection to private
			ftpClient.execPROT("P");

//	        FTPFile[] ftpFiles = ftpClient.listFiles();
	        boolean cambio= ftpClient.changeToParentDirectory();
	        if(cambio)
	        		ficherosRaiz=ftpClient.listFiles();
	        System.out.println(ficherosRaiz);

	     // configuration code for ftpclient port, server etc
	        InputStream in = ftpClient.retrieveFileStream("./dam2_g4/datos.xml");
	        Document documento = readXmlTren(in);
			// Se obtiene la lista de nodos relacionado con 'departamento'
			NodeList nListIni = documento.getElementsByTagName("estaciones");
			for(int temp = 0; temp < nListIni.getLength(); temp++) {
				Node nNode = nListIni.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element elemento = (Element) nNode;
				    	int cod_estacion= Integer.parseInt(elemento.getAttribute("cod_estacion"));
				    	
				    	NodeList Nombre = elemento.getElementsByTagName("nombre");
				    	String nombre = Nombre.item(0).getTextContent();
				    	
				    	NodeList Direccion = elemento.getElementsByTagName("direccion");
				    	String direccion = Direccion.item(0).getTextContent();
				    	
				    	Estacion estacion = new Estacion();
				    	estacion.setCodigoEstacion(cod_estacion);
				    	estacion.setNombre(nombre);
				    	estacion.setDireccion(direccion);
				    	
				    	Estaciones.add(estacion);
					}
				}			
			        
	    } catch (IOException | ParserConfigurationException | SAXException ex) {
	        System.out.println("Oops! Something wrong happened");
	        ex.printStackTrace();
	    } finally {
	        try {
	            if (ftpClient.isConnected()) {
	                ftpClient.logout();
	                ftpClient.disconnect();
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	}

		 public static Document readXmlTren(InputStream is) throws ParserConfigurationException, SAXException, IOException  {
	     DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	     dbf.setValidating(false);
	     dbf.setIgnoringComments(false);
	     dbf.setIgnoringElementContentWhitespace(true);
	     dbf.setNamespaceAware(true);
	     // dbf.setCoalescing(true);
	     // dbf.setExpandEntityReferences(true);

	     DocumentBuilder db = null;
	     db = dbf.newDocumentBuilder();
	     // db.setErrorHandler( new MyErrorHandler());

	     return db.parse(is);
	 }

	public static void LecturaFilezillaAcceso(List<Acceso> Accesos ) throws RedMetroException{
		//pruebaFTPsClient();
		
		TrenHibernateDao trenAccion = new TrenHibernateDao();
		CocheraHibernateDao cocheraAccion = new CocheraHibernateDao();
		EstacionHibernateDao estacionAccion = new EstacionHibernateDao();
		LineaHibernateDao lineaAccion = new LineaHibernateDao();
		
	    String server = "dam2.actividad.cf";
	    int port = 21;
	    String user = "aadd_dam2";
	    String pass = "aadd_dam2";

//	    FTPClient ftpClient = new FTPClient();
	    FTPSClient ftpClient = new FTPSClient("TLS", false);
	    FTPFile[] ficherosRaiz=null;
	    try {
			ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out))); // outputs all conversation to the console
			
	        ftpClient.connect(server, port);

	        ftpClient.enterLocalPassiveMode();
	        boolean success = ftpClient.login(user, pass);

	        if (!success) {
	            System.out.println("Could not login to the server");
	            return;
	        }
			// Set protection buffer size
			ftpClient.execPBSZ(0);
			// Set data channel protection to private
			ftpClient.execPROT("P");

//	        FTPFile[] ftpFiles = ftpClient.listFiles();
	        boolean cambio= ftpClient.changeToParentDirectory();
	        if(cambio)
	        		ficherosRaiz=ftpClient.listFiles();
	        System.out.println(ficherosRaiz);

	     // configuration code for ftpclient port, server etc
	        InputStream in = ftpClient.retrieveFileStream("./dam2_g4/datos.xml");
	        Document documento = readXmlAcceso(in);
			// Se obtiene la lista de nodos relacionado con 'departamento'
			NodeList nListIni = documento.getElementsByTagName("accesos");
			for(int temp = 0; temp < nListIni.getLength(); temp++) {
				Node nNode = nListIni.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				    Element elemento = (Element) nNode;
				    int cod_acceso= Integer.parseInt(elemento.getAttribute("cod_acceso"));
			    	
			    	NodeList Nombre = elemento.getElementsByTagName("nombre");
			    	String nombre = Nombre.item(0).getTextContent();
			    	
			    	NodeList AccesoDiscapacidad = elemento.getElementsByTagName("acceso_discapacidad");
			    	int accesoDiscapacidad = Integer.parseInt(AccesoDiscapacidad.item(0).getTextContent());
			    	
			    	NodeList cod_acceso_estacion = elemento.getElementsByTagName("cod_accesoestacion");
			    	int cod_acc_est = Integer.parseInt(cod_acceso_estacion.item(0).getTextContent());

			    	Estacion estacion = estacionAccion.getEntirdadPorID(cod_acc_est);
			    	/*if(estacion==null) {
			    		estacion = new Estacion();
			    		estacion.setCodigoEstacion(codEstacion);
			    		operacionEstacion.crear(estacion);
			    	}*/
			    	
			    	Acceso acceso = new Acceso(cod_acceso,nombre,accesoDiscapacidad,estacion);
			    	
			    	Accesos.add(acceso);
				}
			}
			        
	    } catch (IOException | ParserConfigurationException | SAXException ex) {
	        System.out.println("Oops! Something wrong happened");
	        ex.printStackTrace();
	    } finally {
	        try {
	            if (ftpClient.isConnected()) {
	                ftpClient.logout();
	                ftpClient.disconnect();
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	}

		 public static Document readXmlAcceso(InputStream is) throws ParserConfigurationException, SAXException, IOException  {
	     DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	     dbf.setValidating(false);
	     dbf.setIgnoringComments(false);
	     dbf.setIgnoringElementContentWhitespace(true);
	     dbf.setNamespaceAware(true);
	     // dbf.setCoalescing(true);
	     // dbf.setExpandEntityReferences(true);

	     DocumentBuilder db = null;
	     db = dbf.newDocumentBuilder();
	     // db.setErrorHandler( new MyErrorHandler());

	     return db.parse(is);
	 }
	}