package es.redmetro.dam2.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import es.redmetro.dam2.dao.IBaseGeneralDao;
import es.redmetro.dam2.excepciones.RedMetroException;
import es.redmetro.dam2.utilidades.UtilidadHibernate;
import es.redmetro.dam2.vo.Tren;

public class TrenHibernateDao implements IBaseGeneralDao<Tren>{

	private Session sesion;
	@Override
	public void crear(Tren entidad) throws RedMetroException {
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
	public void borrar(Tren entidad) throws RedMetroException {
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
	public void actualizar(Tren entidad) throws RedMetroException {
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
	public Tren getEntirdadPorID(int idEntidad) throws RedMetroException {
		Tren tren = null;
		try {
			sesion = UtilidadHibernate.getSession();
			
			tren = sesion.get(Tren.class, idEntidad);
			
		}catch(HibernateException e){
			throw new RedMetroException(RedMetroException.ERROR_CONSULTA, e);
		}finally {
            if (sesion != null) {
                sesion.close();
            }
		}
		
		return tren;
	}

	@Override
	public List<Tren> getListaEntidades() throws RedMetroException {
		List<Tren> tren = null;
		try {
			sesion = UtilidadHibernate.getSession();
			
			tren = sesion.createNativeQuery("SELECT * FROM T_TREN", Tren.class ).list();
			
		}catch(HibernateException e){
			throw new RedMetroException(RedMetroException.ERROR_CONSULTA, e);
		}finally {
            if (sesion != null) {
                sesion.close();
            }
		}
		
		return tren;
	}

}
