package es.redmetro.dam2.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.redmetro.dam2.dao.ICocheraDao;
import es.redmetro.dam2.excepciones.RedMetroException;
import es.redmetro.dam2.utilidades.GestorConexion;
import es.redmetro.dam2.vo.Cochera;

public class CocheraJdbcDao implements ICocheraDao{
	@Override
	public int crearCochera(Cochera cochera) throws RedMetroException {
		Connection conexion =null;
		PreparedStatement sentenciaPreparada =null;
		int resultado =0;
        String sql = "INSERT INTO T_COCHERA (nombre, direccion, deposito) VALUES (?, ?, ?)";
        try
        {
            conexion =GestorConexion.getConexion();
            if(conexion !=null) {	        
	            //Se crea una sentencia preparada
	            sentenciaPreparada = conexion.prepareStatement(sql);
	            

	            //Asignaci�n de par�metros
	            sentenciaPreparada.setString(1, cochera.getNombre());
	            sentenciaPreparada.setString(2, cochera.getDireccion());
	            sentenciaPreparada.setInt(3, cochera.getDeposito());
	            
	            // Ejecutar sentencia
	            resultado = sentenciaPreparada.executeUpdate();
				
	            // Si el alta no es correcta
				if (resultado ==1 ){
					System.out.println("SE insertado correctamente la cochera "+ cochera.getNombre()); 
				}else{
					System.out.println("Error al insertar la cochera " + cochera.getNombre());
				}
            }
        }catch(Exception e){
            System.out.println(e);
            throw new RedMetroException(RedMetroException.ERROR_ALTA, e);
        }finally {
        	try {
        		if (conexion!=null)
        			conexion.close();
        		if(sentenciaPreparada!=null)
        			sentenciaPreparada.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return resultado;
	}

	@Override
	public List<Cochera> consultarCocherasContieneTextoDireccion(String texto) throws RedMetroException {
		
		// Ejemplo de carga de informaci�n mediante un procedimiento almacenado.
		List<Cochera> listaCocheras= new ArrayList<Cochera>();
		Connection conexion =null;
		PreparedStatement sentenciaPreparada=null;
		ResultSet resultado =null; 
	    try
	    {
	    	conexion =GestorConexion.getConexion();
	        if(conexion !=null) {	               	
	        	String textoC="%"+ texto + "%"; 
	        	sentenciaPreparada = conexion.prepareStatement("SELECT * FROM T_COCHERA WHERE direccion LIKE ?");
	
	        	sentenciaPreparada.setString(1, textoC);
	            
	            resultado= sentenciaPreparada.executeQuery();
				while (resultado.next()){
					Cochera cochera = new Cochera();
					int codigoCochera= resultado.getInt(1);
					cochera.setCodigoCochera(codigoCochera);
					String nombre = resultado.getString(2);
					cochera.setNombre(nombre);
					String direccion= resultado.getString(3);
					cochera.setDireccion(direccion);
					int esDeposito= resultado.getInt(4);
					cochera.setDeposito(esDeposito);
					
					listaCocheras.add(cochera);
				}
	        }
	    }catch(Exception e){
	        System.out.println(e);
	        throw new RedMetroException(RedMetroException.ERROR_CONSULTA, e);
	    }finally {
	    	try {
	    		if (conexion!=null)
	    			conexion.close();
	    		if(sentenciaPreparada!=null)
	    			sentenciaPreparada.close();
	    		if(resultado!=null)
	    			resultado.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
		return listaCocheras;
	}

	@Override
	public int modificarCochera(Cochera cochera) throws RedMetroException {
		// TODO Auto-generated method stub
		Connection conexion =null;
		PreparedStatement sentenciaPreparada =null;
		int resultado =0;
        String sql = "UPDATE T_COCHERA SET nombre=?, direccion=?, deposito=? WHERE cod_cochera = ?";
        try
        {
            conexion =GestorConexion.getConexion();
            if(conexion !=null) {	                  
	            //Se crea una sentencia preparada
	            sentenciaPreparada = conexion.prepareStatement(sql);
	            
	            //Asignaci�n de par�metros
	            sentenciaPreparada.setString(1, cochera.getNombre());
	            sentenciaPreparada.setString(2, cochera.getDireccion());
	            sentenciaPreparada.setInt(3, cochera.getDeposito());
	            sentenciaPreparada.setInt(4, cochera.getCodigoCochera());
	
	            
	            // Ejecutar sentencia
	            resultado = sentenciaPreparada.executeUpdate();
				
	            // Si el alta no es correcta
				if (resultado ==1 ){
					System.out.println("Se ha modificado correctamente la cochera "+ cochera.getNombre()); 
				}else{
					System.out.println("Error al modificar la cochera " + cochera.getNombre());
				}    
            }
        }catch(Exception e){
            System.out.println(e);
            throw new RedMetroException(RedMetroException.ERROR_MODIFICAR, e);
        }finally {
        	try {
        		if (conexion!=null)
        			conexion.close();
        		if(sentenciaPreparada!=null)
        			sentenciaPreparada.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return resultado;
	}

	@Override
	public int borrarCochera(int codigoCochera) throws RedMetroException {
		Connection conexion =null;
		PreparedStatement sentenciaPreparada =null;
		int resultado =0;
        String sql = "DELETE FROM T_COCHERA WHERE cod_cochera= ?";
        try
        {
            conexion =GestorConexion.getConexion();
            if(conexion !=null) {	                  
	            //Se crea una sentencia preparada
	            sentenciaPreparada = conexion.prepareStatement(sql);
	            
	            //Asignaci�n de par�metros
	            sentenciaPreparada.setInt(1, codigoCochera);
	            
	            // Ejecutar sentencia
	            resultado = sentenciaPreparada.executeUpdate();
				
	            // Si el alta no es correcta
				if (resultado ==1 ){
					System.out.println("Se ha borrado correctamente la cochera "+ codigoCochera); 
				}else{
					System.out.println("Error al borrar la cochera " + codigoCochera);
				}    
            }
        }catch(Exception e){
            System.out.println(e);
            throw new RedMetroException(RedMetroException.ERROR_BORRAR, e);
        }finally {
        	try {
        		if (conexion!=null)
        			conexion.close();
        		if(sentenciaPreparada!=null)
        			sentenciaPreparada.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return resultado;
	}


}
