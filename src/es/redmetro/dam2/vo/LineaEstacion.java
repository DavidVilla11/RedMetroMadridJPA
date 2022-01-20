package es.redmetro.dam2.vo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="T_LINEAESTACION")
public class LineaEstacion implements Serializable{
	
	private static final long serialVersionUID = 1;
	
	@EmbeddedId
	private LineaEstacionId id = new LineaEstacionId();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cod_linea", insertable = false, updatable = false, 
				foreignKey = @ForeignKey(name = "FK_LINEA_ESTACION"))
	private Linea linea;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cod_estacion", insertable = false, updatable = false, 
				foreignKey = @ForeignKey(name = "FK_ESTACION_LINEA"))
	private Estacion estacion;
	
	@Column(name="ordenM")
	private int ordenM;
	
	public Linea getLinea() {
		return linea;
	}
	public void setLinea(Linea linea) {
		this.linea = linea;
	}
	public Estacion getEstacion() {
		return estacion;
	}
	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}
	public int getOrdenM() {
		return ordenM;
	}
	public void setOrdenM(int ordenM) {
		this.ordenM = ordenM;
	}
	
	@Embeddable
	public static class LineaEstacionId implements Serializable {		
		
		@Column(name = "cod_linea")
		private int codigoLinea;

		@Column(name = "cod_estacion")
		private int codigoEstacion;
		
		public LineaEstacionId() {
			
		}
		
		public LineaEstacionId(int codigoLinea, int codigoEstacion) {
			this.codigoLinea = codigoLinea;
			this.codigoEstacion = codigoEstacion;
			}

		@Override
		public int hashCode() {
			return Objects.hash(codigoEstacion, codigoLinea);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			LineaEstacionId other = (LineaEstacionId) obj;
			return codigoEstacion == other.codigoEstacion && codigoLinea == other.codigoLinea;
		}
		
		
		
	}

	
}
