package ID;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Transient;

import model.Artikl;
import model.Serija;
import model.SopstveniProizvod;
import status.Status;
@Entity
public class ProizvodSerijeID implements Serializable {


	private String LOT;
	private int IDArtikla;
	private int IDNalepnice;
	
	public ProizvodSerijeID() {
		// TODO Auto-generated constructor stub
	}
	

	public ProizvodSerijeID(String lOT, int iDArtikla) {
		super();
		LOT = lOT;
		IDArtikla = iDArtikla;
	}


	public int getIDArtikla() {
		return IDArtikla;
	}

	public void setIDArtikla(int iDArtikla) {
		IDArtikla = iDArtikla;
	}

	public String getLOT() {
		return LOT;
	}

	public void setLOT(String lOT) {
		LOT = lOT;
	}

	public int getIDNalepnice() {
		return IDNalepnice;
	}

	public void setIDNalepnice(int iDNalepnice) {
		IDNalepnice = iDNalepnice;
	}

	
	
}
