package ID;

import java.io.Serializable;

import javax.persistence.Entity;
@Entity
public class PlaniraniProizvodSerijeID implements Serializable {

	private int IDPlanaSerije;
	private int IDArtikla;
	private int IDNalepnice;
	
	public PlaniraniProizvodSerijeID() {
		// TODO Auto-generated constructor stub
	}


	public PlaniraniProizvodSerijeID(int iDPlanaSerije, int iDArtikla, int iDNalepnice) {
		super();
		IDPlanaSerije = iDPlanaSerije;
		IDArtikla = iDArtikla;
		IDNalepnice = iDNalepnice;
	}




	public int getIDArtikla() {
		return IDArtikla;
	}

	public void setIDArtikla(int iDArtikla) {
		IDArtikla = iDArtikla;
	}

	public int getIDNalepnice() {
		return IDNalepnice;
	}

	public void setIDNalepnice(int iDNalepnice) {
		IDNalepnice = iDNalepnice;
	}

	public int getIDPlanaSerije() {
		return IDPlanaSerije;
	}

	public void setIDPlanaSerije(int iDPlanaSerije) {
		IDPlanaSerije = iDPlanaSerije;
	}

	
}
