package es.redmetro.dam2.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import es.redmetro.dam2.dao.IBaseGeneralDao;
import es.redmetro.dam2.excepciones.RedMetroException;
import es.redmetro.dam2.utilidades.UtilidadHibernate;
import es.redmetro.dam2.vo.Cochera;

public class CocheraHibernateDao implements IBaseGeneralDao<Cochera> {

	private Session sesion;
	@Override
	public void crear(Cochera entidad) throws RedMetroException {
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
	public void borrar(Cochera entidad) throws RedMetroException {
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
	public void actualizar(Cochera entidad) throws RedMetroException {
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
	public Cochera getEntirdadPorID(int idEntidad) throws RedMetroException {
		Cochera cochera = null;
		try {
			sesion = UtilidadHibernate.getSession();
			
			cochera = sesion.get(Cochera.class, idEntidad);
			
		}catch(HibernateException e){
			throw new RedMetroException(RedMetroException.ERROR_CONSULTA, e);
		}finally {
            if (sesion != null) {
                sesion.close();
            }
		}
		
		return cochera;
	}

	@Override
	public List<Cochera> getListaEntidades() throws RedMetroException {
		List<Cochera> cochera = null;
		try {
			sesion = UtilidadHibernate.getSession();
			
			cochera = sesion.createNativeQuery("SELECT * FROM T_COCHERA", Cochera.class ).list();
			
		}catch(HibernateException e){
			throw new RedMetroException(RedMetroException.ERROR_CONSULTA, e);
		}finally {
            if (sesion != null) {
                sesion.close();
            }
		}
		
		return cochera;
	}

}
