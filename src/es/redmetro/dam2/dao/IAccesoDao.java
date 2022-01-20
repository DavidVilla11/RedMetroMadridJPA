package es.redmetro.dam2.dao;

import java.util.List;

import es.redmetro.dam2.vo.Acceso;

public interface IAccesoDao {
	public List<Acceso> consultarAccesosEstacion(int codigoEstacion);
}
