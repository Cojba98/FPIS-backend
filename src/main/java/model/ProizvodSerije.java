package model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import ID.ProizvodSerijeID;
import status.Status;
@Entity
@XmlRootElement
public class ProizvodSerije {

	@EmbeddedId
	ProizvodSerijeID id = new ProizvodSerijeID();
	
	@ManyToOne()
	@MapsId("LOT")
	@JoinColumn(name="LOT")
	private Serija serija;
	
	@ManyToOne
	@MapsId("IDArtikla")
	@JoinColumn(name="IDSopstvenogProizvoda")
	private SopstveniProizvod sp;
	
	@ManyToOne
	@MapsId("IDNalepnice")
	@JoinColumn(name="IDNalepnice")
	private Nalepnica n;
	
	private int kolicina;
	
	@Transient
	private Status status;
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public SopstveniProizvod getSp() {
		return sp;
	}
	public void setSp(SopstveniProizvod sp) {
		this.sp = sp;
	}
	public Nalepnica getN() {
		return n;
	}
	public void setN(Nalepnica n) {
		this.n = n;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	
	@XmlTransient
	public Serija getSerija() {
		return serija;
	}
	public void setSerija(Serija serija) {
		this.serija = serija;
	}
	
}
