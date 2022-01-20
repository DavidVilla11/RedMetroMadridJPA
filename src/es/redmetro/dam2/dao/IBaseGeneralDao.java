package es.redmetro.dam2.dao;

import java.util.List;

import es.redmetro.dam2.excepciones.RedMetroException;

public interface IBaseGeneralDao <T> {
	
	public void crear(T entidad) throws RedMetroException;
	public void borrar(T entidad) throws RedMetroException;
	public void actualizar(T entidad) throws RedMetroException;
	public T getEntirdadPorID(int idEntidad) throws RedMetroException;
	public List<T> getListaEntidades() throws RedMetroException;
}
