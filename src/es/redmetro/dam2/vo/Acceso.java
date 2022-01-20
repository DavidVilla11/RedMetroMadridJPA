package es.redmetro.dam2.vo;

import javax.persistence.*;

@Entity
@Table(name="T_ACCESO")
public class Acceso {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cod_acceso")
	private int codigoAcceso;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="acceso_discapacidad")
	private int tieneAccesoDiscapacidad;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Estacion estacion;
	
	
	public Acceso(int codigoAcceso, String nombre, int tieneAccesoDiscapacidad, Estacion estacion) {
		super();
		this.codigoAcceso = codigoAcceso;
		this.nombre = nombre;
		this.tieneAccesoDiscapacidad = tieneAccesoDiscapacidad;
		this.estacion = estacion;
	}
	
	
	
	
	public Acceso() {
		super();
		// TODO Auto-generated constructor stub
	}




	public int getCodigoAcceso() {
		return codigoAcceso;
	}
	public void setCodigoAcceso(int codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int isTieneAccesoDiscapacidad() {
		return tieneAccesoDiscapacidad;
	}
	public void setTieneAccesoDiscapacidad(int tieneAccesoDiscapacidad) {
		this.tieneAccesoDiscapacidad = tieneAccesoDiscapacidad;
	}
	public Estacion getEstacion() {
		return estacion;
	}
	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}
}
