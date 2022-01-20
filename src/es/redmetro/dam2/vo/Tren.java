package es.redmetro.dam2.vo;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="T_TREN")
public class Tren {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="cod_tren")
	private int codigoTren;
	
	@Column(name="modelo")
	private String modelo;
	
	@Column(name="año_incorporacion")
	private Date anyoIncorporacion;
	
	@Column(name="empresa_constructora")
	private String empresaConstructora;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Linea linea;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Cochera cochera;
	
	
	public Tren(int codigoTren, String modelo, Date anyoIncorporacion, String empresaConstructora, Linea linea,
			Cochera cochera) {
		super();
		this.codigoTren = codigoTren;
		this.modelo = modelo;
		this.anyoIncorporacion = anyoIncorporacion;
		this.empresaConstructora = empresaConstructora;
		this.linea = linea;
		this.cochera = cochera;
	}
	public Tren() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEmpresaConstructora() {
		return empresaConstructora;
	}
	public void setEmpresaConstructora(String empresaConstructora) {
		this.empresaConstructora = empresaConstructora;
	}
	public int getCodigoTren() {
		return codigoTren;
	}
	public void setCodigoTren(int codigoTren) {
		this.codigoTren = codigoTren;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Date getAnyoIncorporacion() {
		return anyoIncorporacion;
	}
	public void setAnyoIncorporacion(Date anyoIncorporacion) {
		this.anyoIncorporacion = anyoIncorporacion;
	}
	public Linea getLinea() {
		return linea;
	}
	public void setLinea(Linea linea) {
		this.linea = linea;
	}
	public Cochera getCochera() {
		return cochera;
	}
	public void setCochera(Cochera cochera) {
		this.cochera = cochera;
	}
}
