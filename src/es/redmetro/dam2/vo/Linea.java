package es.redmetro.dam2.vo;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="T_LINEA")
public class Linea {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="cod_linea")
	private int codigoLinea;
	
	@Column(name="nombre_corto")
	private String nombreCorto;
	
	@Column(name="nombre_largo")
	private String nombreLargo;
	
	@OneToMany(mappedBy = "codigoEstacion")
	private Set<Estacion> estaciones;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cod_color", foreignKey = @ForeignKey(name = "FK_COLORLINEA"))
	private Color color;
	
	@Column(name="kilometros")
	private float kilometros;
	
	
	public int getCodigoLinea() {
		return codigoLinea;
	}
	public void setCodigoLinea(int codigoLinea) {
		this.codigoLinea = codigoLinea;
	}
	public String getNombreCorto() {
		return nombreCorto;
	}
	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}
	public String getNombreLargo() {
		return nombreLargo;
	}
	public void setNombreLargo(String nombreLargo) {
		this.nombreLargo = nombreLargo;
	}

	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public float getKilometros() {
		return kilometros;
	}
	public void setKilometros(float kilometros) {
		this.kilometros = kilometros;
	}
	public Set<Estacion> getEstaciones() {
		return estaciones;
	}
	public void setEstaciones(Set<Estacion> estaciones) {
		this.estaciones = estaciones;
	}
}
