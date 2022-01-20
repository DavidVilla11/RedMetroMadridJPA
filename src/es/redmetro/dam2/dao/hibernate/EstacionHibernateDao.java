package es.redmetro.dam2.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import es.redmetro.dam2.dao.IBaseGeneralDao;
import es.redmetro.dam2.excepciones.RedMetroException;
import es.redmetro.dam2.utilidades.UtilidadHibernate;
import es.redmetro.dam2.vo.Estacion;

public class EstacionHibernateDao implements IBaseGeneralDao <Estacion> {

	private Session sesion;
	@Override
	public void crear(Estacion entidad) throws RedMetroException {

		Transaction transaccion = null;
		try {
			sesion = UtilidadHibernate.getSession();
			transaccion = sesion.beginTransaction();
			
			sesion.save(entidad);
			transaccion.commit();
			
		}catch(HibernateException e){
			if(transaccion != null) transaccion.rollback();
			throw new RedMetroException(RedMetroException.ERROR_ALTA, e);
		}finally {
            if (sesion != null) {
                sesion.close();
            }
		}
		
	}

	@Override
	public void borrar(Estacion entidad) throws RedMetroException {
		Transaction transaccion = null;
		try {
			sesion = UtilidadHibernate.getSession();
			transaccion = sesion.beginTransaction();
			
			sesion.delete(entidad);
			transaccion.commit();
			
		}catch(HibernateException e){
			if(transaccion != null) transaccion.rollback();
			throw new RedMetroException(RedMetroException.ERROR_BORRAR, e);
		}finally {
            if (sesion != null) {
                sesion.close();
            }
		}
		
	}

	@Override
	public void actualizar(Estacion entidad) throws RedMetroException {
		Transaction transaccion = null;
		try {
			sesion = UtilidadHibernate.getSession();
			transaccion = sesion.beginTransaction();
			
			sesion.update(entidad);
			transaccion.commit();
			
		}catch(HibernateException e){
			if(transaccion != null) transaccion.rollback();
			throw new RedMetroException(RedMetroException.ERROR_MODIFICAR, e);
		}finally {
            if (sesion != null) {
                sesion.close();
            }
		}
		
	}

	@Override
	public Estacion getEntirdadPorID(int idEntidad) throws RedMetroException {
		Estacion estacion = null;
		try {
			sesion = UtilidadHibernate.getSession();
			
			estacion = sesion.get(Estacion.class, idEntidad);
			
		}catch(HibernateException e){
			throw new RedMetroException(RedMetroException.ERROR_CONSULTA, e);
		}finally {
            if (sesion != null) {
                sesion.close();
            }
		}
		
		return estacion;
	}

	@Override
	public List<Estacion> getListaEntidades() throws RedMetroException {
		List<Estacion> estacion = null;
		try {
			sesion = UtilidadHibernate.getSession();
			
			estacion = sesion.createNativeQuery("SELECT * FROM T_ESTACION", Estacion.class ).list();
			
		}catch(HibernateException e){
			throw new RedMetroException(RedMetroException.ERROR_CONSULTA, e);
		}finally {
            if (sesion != null) {
                sesion.close();
            }
		}
		
		return estacion;
	}
}


