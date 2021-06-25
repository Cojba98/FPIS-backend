package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.bind.v2.runtime.Name;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@XmlRootElement
public class Artikl {
	@Id
	private int IDArtikla;
	private String nazivArtikla;
	
	public Artikl(int iDArtikla) {
		super();
		IDArtikla = iDArtikla;
	}
	
	@OneToOne(mappedBy = "artikl")
	private Sastojak sastojak;

	public Artikl() {
		// TODO Auto-generated constructor stub
	}

	public String getNazivArtikla() {
		return nazivArtikla;
	}
	
	public void setNazivArtikla(String nazivArtikla) {
		this.nazivArtikla = nazivArtikla;
	}
	
	public int getIDArtikla() {
		return IDArtikla;
	}
	
	public void setIDArtikla(int iDArtikla) {
		IDArtikla = iDArtikla;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + IDArtikla;
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
		Artikl other = (Artikl) obj;
		if (IDArtikla != other.IDArtikla)
			return false;
		return true;
	}
	
	
}
