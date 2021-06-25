package model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import ID.ArtiklZaSerijuID;
import status.Status;
@XmlRootElement
@Entity
public class ArtiklZaSeriju {

	@EmbeddedId
	ArtiklZaSerijuID id = new ArtiklZaSerijuID();
	
	@ManyToOne()
	@MapsId("IDPlanaSerije")
	@JoinColumn(name="IDPlanaSerije")
	private PlanProizvodnjeSerije plan;
	
	@ManyToOne()
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
	
	
	public int getKolicina() {
		return kolicina;
	}
	
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}


	public PlanProizvodnjeSerije getPlan() {
		return null;
	}

	public void setPlan(PlanProizvodnjeSerije plan) {
		this.plan = plan;
	}

	public Artikl getArtikl() {
		return artikl;
	}

	public void setArtikl(Artikl artikl) {
		this.artikl = artikl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artikl == null) ? 0 : artikl.hashCode());
		result = prime * result + ((plan == null) ? 0 : plan.hashCode());
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
		ArtiklZaSeriju other = (ArtiklZaSeriju) obj;
		if (artikl == null) {
			if (other.artikl != null)
				return false;
		} else if (!artikl.equals(other.artikl))
			return false;
		if (plan == null) {
			if (other.plan != null)
				return false;
		} else if (!plan.equals(other.plan))
			return false;
		return true;
	}

	

	

	
	
	
	
}
