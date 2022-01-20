package es.redmetro.dam2.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import es.redmetro.dam2.dao.IBaseGeneralDao;
import es.redmetro.dam2.excepciones.RedMetroException;
import es.redmetro.dam2.utilidades.UtilidadHibernate;
import es.redmetro.dam2.vo.Color;

public class ColorHibernateDao implements IBaseGeneralDao <Color> {

	private Session sesion;
	@Override
	public void crear(Color entidad) throws RedMetroException {
		
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
	public void borrar(Color entidad) throws RedMetroException {
		
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
	public void actualizar(Color entidad) throws RedMetroException {
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
	public Color getEntirdadPorID(int idEntidad) throws RedMetroException {
		Color color = null;
		try {
			sesion = UtilidadHibernate.getSession();
			
			color = sesion.get(Color.class, idEntidad);
			
		}catch(HibernateException e){
			throw new RedMetroException(RedMetroException.ERROR_CONSULTA, e);
		}finally {
            if (sesion != null) {
                sesion.close();
            }
		}
		
		return color;
	}

	@Override
	public List<Color> getListaEntidades() throws RedMetroException {
		
		List<Color> color = null;
		try {
			sesion = UtilidadHibernate.getSession();
			
			color = sesion.createNativeQuery("SELECT * FROM T_COLOR", Color.class ).list();
			
		}catch(HibernateException e){
			throw new RedMetroException(RedMetroException.ERROR_CONSULTA, e);
		}finally {
            if (sesion != null) {
                sesion.close();
            }
		}
		
		return color;
	}

}
