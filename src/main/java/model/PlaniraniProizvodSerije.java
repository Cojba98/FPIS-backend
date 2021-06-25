package model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import ID.PlaniraniProizvodSerijeID;
import status.Status;
@XmlRootElement
@Entity
public class PlaniraniProizvodSerije {

	@EmbeddedId
	PlaniraniProizvodSerijeID id = new PlaniraniProizvodSerijeID();
	
	
	@ManyToOne()
	@MapsId("IDArtikla")
	@JoinColumn(name="IDArtikla")
	private SopstveniProizvod sp;
	
	@ManyToOne()
	@MapsId("IDNalepnice")
	@JoinColumn(name="IDNalepnice")
	private Nalepnica n;
	
	@ManyToOne
	@MapsId("IDPlanaSerije")
	@JoinColumn(name="IDPlanaSerije")
	private PlanProizvodnjeSerije plan;
	
	private int kolicina;
	
	@Transient
	Status status;
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public Nalepnica getN() {
		return n;
	}
	
	public void setN(Nalepnica n) {
		this.n = n;
	}
	
	public SopstveniProizvod getSp() {
		return sp;
	}
	
	public void setSp(SopstveniProizvod sp) {
		this.sp = sp;
	}
	
	
	public int getKolicina() {
		return kolicina;
	}
	
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((n == null) ? 0 : n.hashCode());
		result = prime * result + ((sp == null) ? 0 : sp.hashCode());
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
		PlaniraniProizvodSerije other = (PlaniraniProizvodSerije) obj;
		if (n == null) {
			if (other.n != null)
				return false;
		} else if (!n.equals(other.n))
			return false;
		if (sp == null) {
			if (other.sp != null)
				return false;
		} else if (!sp.equals(other.sp))
			return false;
		return true;
	}

	@XmlTransient
	public PlanProizvodnjeSerije getPlan() {
		return plan;
	}

	public void setPlan(PlanProizvodnjeSerije plan) {
		this.plan = plan;
	}


	
	
	
}
