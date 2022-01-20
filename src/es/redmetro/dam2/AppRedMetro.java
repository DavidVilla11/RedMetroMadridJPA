package es.redmetro.dam2;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.redmetro.dam2.dao.IBaseGeneralDao;
import es.redmetro.dam2.dao.ICocheraDao;
import es.redmetro.dam2.dao.hibernate.AccesoHibernateDao;
import es.redmetro.dam2.dao.hibernate.CocheraHibernateDao;
import es.redmetro.dam2.dao.hibernate.ColorHibernateDao;
import es.redmetro.dam2.dao.hibernate.EstacionHibernateDao;
import es.redmetro.dam2.dao.hibernate.LineaEstacionHibernateDao;
import es.redmetro.dam2.dao.hibernate.LineaHibernateDao;
import es.redmetro.dam2.dao.hibernate.TrenHibernateDao;
import es.redmetro.dam2.dao.jdbc.CocheraJdbcDao;
import es.redmetro.dam2.excepciones.RedMetroException;
import es.redmetro.dam2.procesos.ProcesoFicheros;
import es.redmetro.dam2.utilidades.APPPruebaFTP;
import es.redmetro.dam2.utilidades.UtilidadHibernate;
import es.redmetro.dam2.vo.Acceso;
import es.redmetro.dam2.vo.Cochera;
import es.redmetro.dam2.vo.Color;
import es.redmetro.dam2.vo.Estacion;
import es.redmetro.dam2.vo.Linea;
import es.redmetro.dam2.vo.LineaEstacion;
import es.redmetro.dam2.vo.Tren;

public class AppRedMetro {
	public static void main(String[] args) throws RedMetroException{
		
		UtilidadHibernate.getSession();
		
		/*List<Tren> Trenes = new ArrayList<Tren>();
		List<Acceso> Accesos = new ArrayList<Acceso>();
		List<Estacion> Estaciones = new ArrayList<Estacion>();
		
		TrenHibernateDao tren = new TrenHibernateDao();
		AccesoHibernateDao acceso = new AccesoHibernateDao();
		EstacionHibernateDao estacion = new EstacionHibernateDao();
		
		
		APPPruebaFTP.LecturaFilezillaAcceso(Accesos);
		APPPruebaFTP.LecturaFilezillaEstaciones(Estaciones);
		APPPruebaFTP.LecturaFilezillaTrenes(Trenes);
		
		for(Tren item : Trenes) {
			tren.crear(item);
		}
		
		for(Acceso item : Accesos) {
			acceso.crear(item);
		}
		
		for(Estacion item : Estaciones) {
			estacion.crear(item);
		}*/
	}
	

}

