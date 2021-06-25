package ID;

import java.io.Serializable;

public class ArtiklZaSerijuID implements Serializable {

	private int IDPlanaSerije;
	private int IDArtikla;
	
	public ArtiklZaSerijuID() {
		// TODO Auto-generated constructor stub
	}
	
	

	public ArtiklZaSerijuID(int iDPlanaSerije, int iDArtikla) {
		super();
		IDPlanaSerije = iDPlanaSerije;
		IDArtikla = iDArtikla;
	}



	public int getIDPlanaSerije() {
		return IDPlanaSerije;
	}

	public void setIDPlanaSerije(int iDPlanaSerije) {
		IDPlanaSerije = iDPlanaSerije;
	}

	public int getIDArtikla() {
		return IDArtikla;
	}

	public void setIDArtikla(int iDArtikla) {
		IDArtikla = iDArtikla;
	}
	
	
	
}
