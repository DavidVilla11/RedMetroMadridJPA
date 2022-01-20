package es.redmetro.dam2.vo;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="T_ESTACION")
public class Estacion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="cod_estacion")
	private int codigoEstacion;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="direccion")
	private String direccion;
	
	@OneToMany(mappedBy = "codigoLinea")
	private Set<Linea> lineaEstacion = new HashSet<Linea>();

	
	public int getCodigoEstacion() {
		return codigoEstacion;
	}
	public void setCodigoEstacion(int codigoEstacion) {
		this.codigoEstacion = codigoEstacion;
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
	public Set<Linea> getLineaEstacion() {
		return lineaEstacion;
	}
	public void setLineaEstacion(Set<Linea> lineaEstacion) {
		this.lineaEstacion = lineaEstacion;
	}



}
