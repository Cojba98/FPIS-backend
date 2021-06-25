package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@Entity
public class Cisterna {
@Id
	private int IDCisterne;
	private Date datum;
	private boolean ispravna;
	private int ukupnoOtkupljenoMleko;
	
	public Cisterna() {
		// TODO Auto-generated constructor stub
	}

	public int getIDCisterne() {
		return IDCisterne;
	}

	public void setIDCisterne(int iDCisterne) {
		IDCisterne = iDCisterne;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public boolean isIspravna() {
		return ispravna;
	}

	public void setIspravna(boolean ispravna) {
		this.ispravna = ispravna;
	}

	public int getUkupnoOtkupljenoMleko() {
		return ukupnoOtkupljenoMleko;
	}

	public void setUkupnoOtkupljenoMleko(int ukupnoOtkupljenoMleko) {
		this.ukupnoOtkupljenoMleko = ukupnoOtkupljenoMleko;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + IDCisterne;
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
		Cisterna other = (Cisterna) obj;
		if (IDCisterne != other.IDCisterne)
			return false;
		return true;
	}
	
	
	
}
