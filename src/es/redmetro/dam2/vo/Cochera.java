package es.redmetro.dam2.vo;

import javax.persistence.*;

@Entity
@Table(name="T_COCHERA")
public class Cochera {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cod_cochera")
	private int codigoCochera;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="deposito")
	private int deposito;
	
	public int getCodigoCochera() {
		return codigoCochera;
	}
	public void setCodigoCochera(int codigoCochera) {
		this.codigoCochera = codigoCochera;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getDeposito() {
		return deposito;
	}
	public void setDeposito(int deposito) {
		this.deposito = deposito;
	}
}
