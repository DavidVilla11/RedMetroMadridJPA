package es.redmetro.dam2.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.redmetro.dam2.dao.IAccesoDao;
import es.redmetro.dam2.utilidades.GestorConexion;
import es.redmetro.dam2.vo.Acceso;
import es.redmetro.dam2.vo.Estacion;


public class AccesoJdbcDao implements IAccesoDao{

	@Override
	public List<Acceso> consultarAccesosEstacion(int codigoEstacion) {
		List<Acceso> listaAccesos= new ArrayList<Acceso>();
		Connection conexion =null;
		PreparedStatement sentenciaPreparado=null;
		ResultSet resultado =null; 
        try
        {
        	conexion =GestorConexion.getConexion();
            if(conexion !=null) {	               	
            	String sql = "SELECT A.cod_acceso, A.nombre, A.acceso_discapacidad, B.nombre, B.direccion FROM T_ACCESOS A, T_ESTACION B WHERE  A.cod_estacion = B.cod_estacion and cod_estacion= ?";
            	sentenciaPreparado = conexion.prepareCall(sql);
	
            	sentenciaPreparado.setInt(1, codigoEstacion);  
	            
	            resultado= sentenciaPreparado.executeQuery();
				while (resultado.next()){
					Acceso acceso = new Acceso();
					int cod_acceso= resultado.getInt(1);
					acceso.setCodigoAcceso(cod_acceso);
					String nombreAcceso = resultado.getString(2);
					acceso.setNombre(nombreAcceso);
					int acceso_discapacidad = resultado.getInt(3);
					acceso.setTieneAccesoDiscapacidad(acceso_discapacidad);
					
					Estacion estacion = new Estacion();
					estacion.setCodigoEstacion(codigoEstacion);
					String nombreEstacion= resultado.getString(4);
					estacion.setNombre(nombreEstacion);
					String direccionAcceso = resultado.getString(5);
					estacion.setDireccion(direccionAcceso);
					
					acceso.setEstacion(estacion);
					
					listaAccesos.add(acceso);
				}
            }
        }catch(Exception e){
            System.out.println(e);
        }finally {
        	try {
        		if (conexion!=null)
        			conexion.close();
        		if(sentenciaPreparado!=null)
        			sentenciaPreparado.close();
        		if(resultado!=null)
        			resultado.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		
		return listaAccesos;
	}
}
