package es.redmetro.dam2.excepciones;

public class RedMetroException extends Exception {

	private static final long serialVersionUID = -6408929048092846124L;
	public final static String ERROR_ALTA = "001";
	public final static String ERROR_CONSULTA = "002";
	public final static String ERROR_MODIFICAR = "003";
	public final static String ERROR_BORRAR = "004";
	private String codigoError;
	
	public RedMetroException (String codigoError, Exception exception) {
		super(exception);
		setCodigoError(codigoError);
		
	}
	public String getCodigoError() {
		return codigoError;
	}
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}
	
	
	
}
