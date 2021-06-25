package model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.ManyToAny;

import ID.SastojakID;

@Entity
public class Sastojak {

	@EmbeddedId
	SastojakID id = new SastojakID();
	
	@ManyToOne
	@MapsId("IDSopstvenogProizvoda")
	private SopstveniProizvod sopstveniProizvod;
	
	@ManyToOne
	@MapsId("IDArtikla")
	private Artikl artikl;
	
	private int kolicina;
	
	
	
	public int getKolicina() {
		return kolicina;
	}
	
	
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public SastojakID getId() {
		return id;
	}

	public void setId(SastojakID id) {
		this.id = id;
	}
	
	@XmlTransient
	public Artikl getArtikl() {
		return artikl;
	}

	public void setArtikl(Artikl artikl2) {
		this.artikl = artikl2;
	}

	@XmlTransient
	public SopstveniProizvod getSopstveniProizvod() {
		return sopstveniProizvod;
	}

	public void setSopstveniProizvod(SopstveniProizvod sopstveniProizvod) {
		this.sopstveniProizvod = sopstveniProizvod;
	}
	
	
}
