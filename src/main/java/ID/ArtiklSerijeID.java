package ID;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ArtiklSerijeID implements Serializable {

	
	private String LOT;
	private int IDArtikla;
	
	public ArtiklSerijeID() {
	// TODO Auto-generated constructor stub
	}
	
	public ArtiklSerijeID(String LOT, int IDArtikla) {
		super();
		this.LOT = LOT;
		this.IDArtikla = IDArtikla;
	}

	public String getLot() {
		return LOT;
	}

	public void setLot(String lot) {
		this.LOT = lot;
	}

	public int getIDArtikla() {
		return IDArtikla;
	}

	public void setIDArtikla(int iDArtikla) {
		IDArtikla = iDArtikla;
	}


	
	
}