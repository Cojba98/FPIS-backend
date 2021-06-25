package model;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import ID.CisternaSerijeID;
import status.Status;

@XmlRootElement
@Entity
public class CisternaSerije {

	@EmbeddedId
	private CisternaSerijeID id = new CisternaSerijeID();
	
	@ManyToOne
	@MapsId("LOT")
	@JoinColumn(name="LOT")
	private Serija serija;
	
	@ManyToOne
	@MapsId("IDCisterne")
	@JoinColumn(name="IDCisterne")
	private Cisterna cisterna;
	
	private int kolicina;
	
	@Transient
	private Status status;
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public Cisterna getCisterna() {
		return cisterna;
	}
	public void setCisterna(Cisterna cisterna) {
		this.cisterna = cisterna;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	
	
	public CisternaSerijeID getId() {
		return id;
	}

	public void setId(CisternaSerijeID id) {
		this.id = id;
	}

	@XmlTransient
	public Serija getSerija() {
		return serija;
	}

	public void setSerija(Serija serija) {
		this.serija = serija;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cisterna == null) ? 0 : cisterna.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CisternaSerije other = (CisternaSerije) obj;
		if (cisterna == null) {
			if (other.cisterna != null)
				return false;
		} else if (!cisterna.equals(other.cisterna))
			return false;
		return true;
	}
	
	
	
}
