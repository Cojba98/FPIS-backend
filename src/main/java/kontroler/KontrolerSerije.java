package kontroler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbbroker.DBBroker;
import model.ArtiklSerije;
import model.ArtiklZaSeriju;
import model.Cisterna;
import model.CisternaSerije;
import model.PlanProizvodnjeSerije;
import model.PlaniraniProizvodSerije;
import model.ProizvodSerije;
import model.Serija;
import status.Status;

public class KontrolerSerije {

	DBBroker dbb;
	PlanProizvodnjeSerije pps;
	Serija s;
	private static KontrolerSerije instance;
	private CisternaSerije cs;
	private boolean ret;
	
	public static KontrolerSerije vratiInstancu() {
		if(instance==null) {
			instance = new KontrolerSerije();
		}
		return instance;
	}
	
	public KontrolerSerije() {
		
		dbb = new DBBroker();
		s = new Serija();

	}
	
	public List<PlanProizvodnjeSerije> vratiListuPlanovaProizvodnjeSerije() throws SQLException {
		return dbb.vratiListuPlanovaProizvodnjeSerije();
//		while(rs.next()) {
//			PlanProizvodnjeSerije pps = new PlanProizvodnjeSerije();
//					pps.setIDPlanaSerije(rs.getInt("idplanaserije"));
//			listaPlanova.add(pps);
//		}
//		return listaPlanova;
	}
	
	public PlanProizvodnjeSerije izborPlanaProizvodnjeSerije(int IDPlanaProizvodnjeSerije) {
		pps = dbb.pronadjiPlanProizvodnjeSerije(IDPlanaProizvodnjeSerije );
		s.kreirajProizvodeSerije(pps.vratiListuPlaniranihProizvoda());
		s.kreirajArtikleSerije(pps.vratiListuArtikalaZaSeriju());
	//	s.setStatus(Status.NOVO);
		return pps;
	}
	
	

	public List<ProizvodSerije> vratiListuProizvodaSerije() {
		return s.vratiListuProizvoda();
	}
	
	public List<ArtiklSerije> vratiListuArtikalaSerije(){
		return s.vratiListuArtikalaSerije();
	}

	public List<Cisterna> vratiListuCisterni() throws SQLException {
	return dbb.vratiListuCisterni();
//		List<Cisterna> listaCisterni = new ArrayList();
//		while(rs.next()) {
//			Cisterna cis = new Cisterna();
//			cis.setIDCisterne(rs.getInt("IDCisterne"));
//			cis.setDatum(rs.getDate("Datum"));
//			cis.setIspravna(rs.getBoolean("Ispravna"));
//			cis.setUkupnoOtkupljenoMleko(rs.getInt("ukupnootkupljenomleko"));
//			listaCisterni.add(cis);
//		}
//		return listaCisterni;
	}

	public void unosKolicineProizvodaSerije(int iDArtikla, int iDNalepnice, int kolicina) {
		s.unosKolicineProizvodaSerije(iDArtikla, iDNalepnice, kolicina);
	}
	
	public void ukloniProizvodSerije(int iDArtikla, int iDNalepnice) {
		s.ukloniProizvodSerije(iDArtikla, iDNalepnice);
	}

	public List<PlaniraniProizvodSerije> vratiListuPlaniranihProizvodaSerije() {
		return pps.getPlaniraniProizvodiSerije();
	}

	public List<ArtiklZaSeriju> vratiListuArtikalaZaSeriju() {
		return pps.getArtikliZaSeriju();
	}

	public void unosKolicineArtiklaSerije(int iDArtikla, int kolicina) {
		s.unosKolicineArtiklaSerije(iDArtikla, kolicina);
		
	}
	
	public void ukloniArtiklSerije(int iDArtikla) {
		s.ukloniArtiklSerije(iDArtikla);
	}

	public List<CisternaSerije> vratiListuCisterniSerije() {
		return s.vratiListuCisterniSerije();
	}
	
	public List<CisternaSerije> dodajNovuCisternu() {
		s.dodajNovuCisternu();
		return vratiListuCisterniSerije();
	}

	public void postaviCisternu(int idCisterne) throws SQLException {
		Cisterna cis = dbb.pronadjiCisternu(idCisterne);
		s.postaviCisternu(cis);
		
	}

	public void postaviKolicinuZaCisternu(int kolicina) {
		s.postaviKolicinuZaSeriju(kolicina);
	}

	public boolean sacuvajSeriju(String LOT) {
		s.postaviLOT(LOT);
		dbb.pokreniDBTransakciju();
		ret = dbb.zapamtiSeriju(s);
		if(ret) 
			dbb.potvrdiDBTransakciju();
		else
			dbb.ponistiDBTransakciju();
		return ret;
	}

	public void obrisiCisternuSerije(int idCisterne) {
		s.obrisiCisternuSerije(idCisterne);
		
	}

	
	public void ubaciCisternu() {
		s.ubaciCisternuUKolekciju();

	}

	public static void kreirajNoviKontroler() {
		instance = new KontrolerSerije();
		
	}

	public List<Serija> vratiListuSerija() throws SQLException {
		return dbb.vratiListuSerija();
//		List<Serija> lista = new ArrayList<Serija>();
//		while(rs.next()) {
//			Serija s = new Serija();
//			s.postaviLOT(rs.getString("lot"));
//			lista.add(s);
//		}
//		return lista;
	}

	public Serija postaviSeriju(String lot) {
		s = dbb.pronadjiSeriju(lot);
//		System.out.println("Duzina artikala: " + s.vratiListuArtikalaSerije().size());
//		System.out.println("Duzina proizvoda: " + s.vratiListuProizvoda().size());
//		System.out.println("Duzina cisterni: " + s.vratiListuCisterniSerije().size());
		return s;
	}
	
}
