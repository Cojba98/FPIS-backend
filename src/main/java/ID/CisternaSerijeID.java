package ID;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class CisternaSerijeID implements Serializable {


	private String LOT;
	private int IDCisterne;
	

	public String getLot() {
		return LOT;
	}

	public void setLot(String lot) {
		this.LOT = lot;
	}

	public String getLOT() {
		return LOT;
	}

	public void setLOT(String lOT) {
		LOT = lOT;
	}

	public int getIDCisterne() {
		return IDCisterne;
	}

	public void setIDCisterne(int iDCisterne) {
		IDCisterne = iDCisterne;
	}




	
}
