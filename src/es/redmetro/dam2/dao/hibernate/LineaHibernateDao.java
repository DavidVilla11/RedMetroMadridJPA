package es.redmetro.dam2.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import es.redmetro.dam2.dao.IBaseGeneralDao;
import es.redmetro.dam2.excepciones.RedMetroException;
import es.redmetro.dam2.utilidades.UtilidadHibernate;
import es.redmetro.dam2.vo.Linea;

public class LineaHibernateDao implements IBaseGeneralDao <Linea> {

	private Session sesion;
	@Override
	public void crear(Linea entidad) throws RedMetroException {
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
	public void borrar(Linea entidad) throws RedMetroException {
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
	public void actualizar(Linea entidad) throws RedMetroException {
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
	public Linea getEntirdadPorID(int idEntidad) throws RedMetroException {
		Linea linea = null;
		try {
			sesion = UtilidadHibernate.getSession();
			
			linea = sesion.get(Linea.class, idEntidad);
			
		}catch(HibernateException e){
			throw new RedMetroException(RedMetroException.ERROR_CONSULTA, e);
		}finally {
            if (sesion != null) {
                sesion.close();
            }
		}
		
		return linea;
	}

	@Override
	public List<Linea> getListaEntidades() throws RedMetroException {
		List<Linea> linea = null;
		try {
			sesion = UtilidadHibernate.getSession();
			
			linea = sesion.createNativeQuery("SLECT * FROM T_LINEA", Linea.class ).list();
			
		}catch(HibernateException e){
			throw new RedMetroException(RedMetroException.ERROR_CONSULTA, e);
		}finally {
			sesion.close();
		}
		
		return linea;
	}

}
