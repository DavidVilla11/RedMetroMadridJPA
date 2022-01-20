package es.redmetro.dam2.dao;

import java.util.List;

import es.redmetro.dam2.excepciones.RedMetroException;
import es.redmetro.dam2.vo.Cochera;

public interface ICocheraDao {
	public  int crearCochera(Cochera cochera) throws RedMetroException;
	public  int modificarCochera(Cochera cochera)throws RedMetroException;
	public  int borrarCochera(int codigoCochera)throws RedMetroException;
	public  List<Cochera> consultarCocherasContieneTextoDireccion(String texto)throws RedMetroException; // Se puede poner otro nombre; conviene que sea significativo
}
