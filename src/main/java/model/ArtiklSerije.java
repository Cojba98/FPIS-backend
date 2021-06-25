package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import ID.ArtiklSerijeID;
import status.Status;

@XmlRootElement
@Entity
public class ArtiklSerije {
	
	
	
	@EmbeddedId
	private ArtiklSerijeID id = new ArtiklSerijeID();
	
	@ManyToOne()
	@MapsId("LOT")
	@JoinColumn(name="LOT")
	private Serija serija;
	
	@ManyToOne
	@MapsId("IDArtikla")
	@JoinColumn(name="IDArtikla")
	private Artikl artikl;
	
	private int kolicina;
	
	@Transient
	private Status status;
	
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Artikl getArtikl() {
		return artikl;
	}
	public void setArtikl(Artikl artikl) {
		this.artikl = artikl;
		
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	public ArtiklSerijeID getId() {
		return id;
	}
	public void setId(ArtiklSerijeID id) {
		this.id = id;
	}
	@XmlTransient
	public Serija getSerija() {
		return serija;
	}
	public void setSerija(Serija serija) {
		this.serija = serija;
	}
	

}
