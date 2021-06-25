package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
@XmlRootElement
@Entity
@PrimaryKeyJoinColumn(name = "IDArtikla")
public class SopstveniProizvod extends Artikl{

	public SopstveniProizvod() {
		// TODO Auto-generated constructor stub
	}
	
	public SopstveniProizvod(int IDArtikla) {
		super(IDArtikla);
	}
	
	private String opis;
	private int ZapreminaUMl;
	private int kolicinaMleka;
	
	@OneToMany(mappedBy = "sopstveniProizvod")
	private List<Sastojak> sastojci = new ArrayList<Sastojak>();
	
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public int getZapreminaUMl() {
		return ZapreminaUMl;
	}
	public void setZapreminaUMl(int zapreminaUMl) {
		ZapreminaUMl = zapreminaUMl;
	}
	public int getKolicinaMleka() {
		return kolicinaMleka;
	}
	
	public void setKolicinaMleka(int kolicinaMleka) {
		this.kolicinaMleka = kolicinaMleka;
	}
	
	public void setSastojci(List<Sastojak> sastojci) {
		this.sastojci = sastojci;
	}
	
	public List<Sastojak> getSastojci() {
		return sastojci;
	}
	
	
	
}
