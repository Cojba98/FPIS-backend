package kontroler;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbbroker.DBBroker;
import model.Artikl;
import model.ArtiklZaSeriju;
import model.Nalepnica;
import model.PlanProizvodnjeSerije;
import model.PlaniraniProizvodSerije;
import model.Sastojak;
import model.SopstveniProizvod;
import status.Status;

public class KontrolerPlanaSerije {

	DBBroker dbb;
	PlanProizvodnjeSerije pps;
	int idPlana;
	SopstveniProizvod sp;
	Nalepnica n;
	
	private static KontrolerPlanaSerije instance;
	
	public static KontrolerPlanaSerije vratiInstancu(){
		if(instance == null) {
			instance = new KontrolerPlanaSerije();
		}
		return instance;
	}
	
	
	
	private KontrolerPlanaSerije() {
		dbb = new DBBroker();
	}
	
//	public void kreirajNoviPlan() {
//		pps = new PlanProizvodnjeSerije(vratiNoviIDPlana());
//		pps.setStatus(Status.NOVO);
//	}
	
	public int vratiNoviIDPlana() {
		int noviID =  dbb.vratiNoviIDPlana();
		pps = new PlanProizvodnjeSerije(noviID);
		return pps.getIDPlanaSerije();
	}
	
	public List<SopstveniProizvod> vratiListuProizvoda() {
		
		return dbb.vratiListuProizvoda();
	}
	
	public List<Nalepnica> vratiListuNalepnica() {
		
		return dbb.vratiListuNalepnica();
	}
	
	public List<PlanProizvodnjeSerije> vratiPlanoveProizvodnjeSerije() {
		return dbb.vratiListuPlanovaProizvodnjeSerije();
	}
	
	public void kreirajNoviPlaniraniProizvod() {
		pps.kreirajNoviPlaniraniProizvodSerije();
	}
	
	public void postaviProizvod(int IDArtikla) {
		sp = dbb.pronadjiProizvod(IDArtikla);
		pps.postaviProizvod(sp);
	}
	
	public void postaviNalepnicu(int IDNalepnice) {
		n = dbb.pronadjiNalepnicu(IDNalepnice);
		pps.postaviNalepnicu(n);
	}
	
	public void postaviKolicinu(int kolicina) {
		pps.postaviKolicinu(kolicina);
	}
	
	public void dodajPlaniraniProizvodSerije() {
		pps.dodajPlaniraniProizvodSerije();
	}
	
	public void ukloniPlaniraniProizvodSerije(int IDArtikla, int IDNalepnice) {
		pps.ukloniPlaniraniProizvodSerije(IDArtikla, IDNalepnice);
	}
	
	public void pripremiProizvodZaIzmenu(int IDProizvoda, int IDNalepnice) {
		pps.pripremiProizvodZaIzmenu(IDProizvoda, IDNalepnice);
	}
	
	public List<PlaniraniProizvodSerije> vratiListuPlaniranihProizvoda() {
		return pps.vratiListuPlaniranihProizvoda();
	}
	
	public List<ArtiklZaSeriju> vratiListuArtikalaZaSeriju() {
		List<PlaniraniProizvodSerije> listaPlaniranihProizvoda = pps.getPlaniraniProizvodiSerije();
		List<Sastojak> sastojci = dbb.vratiListuSastavnica();
		pps.inicijalizujArtikleZaSeriju();
		for(PlaniraniProizvodSerije plp : listaPlaniranihProizvoda) {
			for(Sastojak s : sastojci) {
				if(s.getSopstveniProizvod().getIDArtikla() == plp.getSp().getIDArtikla()) {
				Artikl artikl = dbb.pronadjiArtikl(s.getArtikl().getIDArtikla());
				pps.dodajArtiklZaSeriju(artikl, s.getKolicina()*plp.getKolicina());
				}
			}
		}
		
		
		return pps.vratiListuArtikalaZaSeriju();
	}
	
	public int izracunajPotrebnuKolicinuMleka() {
		int ukupno = 0;
		for(PlaniraniProizvodSerije pls : pps.getPlaniraniProizvodiSerije()) {
			ukupno += (pls.getSp().getKolicinaMleka()*pls.getKolicina());
		}
		return ukupno / 1000;
	}
	
	public String zapamtiPlan() {
		pps.pripremiSeZaCuvanje();
		dbb.pokreniDBTransakciju();
		//pps.pripremiSeZaCuvanje();
		boolean ret = dbb.zapamtiPlan(pps);
		pps = null;
		if(ret) {
			dbb.potvrdiDBTransakciju();
		return "true";
		}
		else {
			dbb.ponistiDBTransakciju();
			return "false";
		}
	}



//	public void postaviPlanProizvodnjeSerije(int idPlana) {
//		pps = dbb.pronadjiPlanProizvodnjeSerije(idPlana);
//		//pps.setStatus(Status.NEIZMENJENO);
//	}

	public static void kreirajNoviKontroler() {
		instance = new KontrolerPlanaSerije();
	}



	public void izborPlana(int id) {
		pps = dbb.pronadjiPlanProizvodnjeSerije(id);

	}



	public void unosKolicineProizvodaSerije(int iDArtikla, int iDNalepnice, int kolicina) {
		pps.unosKolicineProizvodaSerije(iDArtikla, iDNalepnice, kolicina);
		
	}



//	public void sacuvajIzmene() {
//		pps.sacuvajIzmene();
//		
//	}
}
