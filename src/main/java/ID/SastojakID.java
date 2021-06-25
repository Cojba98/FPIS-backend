package ID;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Embeddable
public class SastojakID implements Serializable{

	private int IDArtikla;
	
	private int IDSopstvenogProizvoda;
	
	public SastojakID() {
		// TODO Auto-generated constructor stub
	}

	public SastojakID(int iDArtikla, int idSopstvenogProizvoda) {
		super();
		IDArtikla = iDArtikla;
		IDSopstvenogProizvoda = idSopstvenogProizvoda;
	}
	
	public int getIDArtikla() {
		return IDArtikla;
	}
	public void setIDArtikla(int iDArtikla) {
		IDArtikla = iDArtikla;
	}

	public int getIDSopstvenogProizvoda() {
		return IDSopstvenogProizvoda;
	}

	public void setIDSopstvenogProizvoda(int iDSopstvenogProizvoda) {
		IDSopstvenogProizvoda = iDSopstvenogProizvoda;
	}

	
	
	
}
