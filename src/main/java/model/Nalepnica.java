package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Nalepnica {
@Id
	private int IDNalepnice;
	private String opis;
	
	public int getIDNalepnice() {
		return IDNalepnice;
	}
	
	public void setIDNalepnice(int iDNalepnice) {
		IDNalepnice = iDNalepnice;
	}
	
	public String getOpis() {
		return opis;
	}
	
	public void setOpis(String opis) {
		this.opis = opis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + IDNalepnice;
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
		Nalepnica other = (Nalepnica) obj;
		if (IDNalepnice != other.IDNalepnice)
			return false;
		return true;
	}
	
	
	
}
