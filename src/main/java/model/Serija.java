package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import status.Status;


@XmlRootElement
@Entity
public class Serija {
	@Id
	private String LOT;
	
	@OneToMany(mappedBy = "serija", cascade = {CascadeType.PERSIST, CascadeType.ALL, CascadeType.MERGE}, orphanRemoval = true)
	private List<ProizvodSerije> listaProizvodaSerije;
	
	@OneToMany(mappedBy = "serija", cascade = {CascadeType.PERSIST, CascadeType.ALL, CascadeType.MERGE}, orphanRemoval = true)
	private List<ArtiklSerije> listaArtikalaSerije;
	
	@OneToMany(mappedBy = "serija", cascade = {CascadeType.PERSIST, CascadeType.ALL, CascadeType.MERGE}, orphanRemoval = true)
	private List<CisternaSerije> listaCisterniSerije;
	
	@Transient
	private CisternaSerije cs;
	
	public Serija() {
		listaCisterniSerije = new ArrayList<CisternaSerije>();
	}
	
//	@Transient
//	private Status status;
	
	
//	public void setStatus(Status status) {
//		this.status = status;
//	}
//	
//	public Status getStatus() {
//		return status;
//	}
	
	public void kreirajProizvodeSerije(List<PlaniraniProizvodSerije> listaPlaniranihProizvoda) {
		listaProizvodaSerije = new ArrayList();
		for(PlaniraniProizvodSerije plp : listaPlaniranihProizvoda) {
			ProizvodSerije ps = new ProizvodSerije();
			ps.setKolicina(plp.getKolicina());
			ps.setSp(plp.getSp());
			ps.setN(plp.getN());
			dodajUKolekciju(ps);
		}
	}
	
	public void kreirajArtikleSerije(List<ArtiklZaSeriju> listaPlaniranihArtikala) {
		listaArtikalaSerije = new ArrayList();
		for(ArtiklZaSeriju azs : listaPlaniranihArtikala) {
			ArtiklSerije as = new ArtiklSerije();
			as.setArtikl(azs.getArtikl());
			as.setKolicina(azs.getKolicina());
			dodajUKolekciju(as);
		}
	}
	
	public void dodajUKolekciju(ArtiklSerije as) {
		listaArtikalaSerije.add(as);
		as.setSerija(this);
		
	}
	

	public void dodajUKolekciju(ProizvodSerije ps) {
		listaProizvodaSerije.add(ps);
		ps.setSerija(this);
	}
	
	
	public void dodajUKolekciju(CisternaSerije cs) {
		for(CisternaSerije cisterna: listaCisterniSerije) {
			if(cisterna.getCisterna().getIDCisterne() == cs.getCisterna().getIDCisterne()) {
				//cisterna.setStatus(Status.IZMENJENO);
				cisterna.setKolicina(cisterna.getKolicina()+cs.getKolicina());
				return;
			}
		}
		//cs.setStatus(Status.NOVO);
		listaCisterniSerije.add(cs);
	}

	public List<ProizvodSerije> vratiListuProizvoda() {
//		List proizvodi = new ArrayList<ProizvodSerije>();
//		for(ProizvodSerije cs: listaProizvodaSerije)
//			if(cs.getStatus()!=Status.OBRISANO)
//				proizvodi.add(cs);
//		return proizvodi;
		return listaProizvodaSerije;
	}

	public List<ArtiklSerije> vratiListuArtikalaSerije() {
//		List artikli = new ArrayList<ArtiklSerije>();
//		for(ArtiklSerije cs: listaArtikalaSerije)
//			if(cs.getStatus()!=Status.OBRISANO)
//				artikli.add(cs);
//		return artikli;
		return listaArtikalaSerije;
		
	}

	public void unosKolicineProizvodaSerije(int iDArtikla, int iDNalepnice, int kolicina) {
		for(ProizvodSerije ps: listaProizvodaSerije) {
			if(ps.getSp().getIDArtikla() == iDArtikla && ps.getN().getIDNalepnice() == iDNalepnice)
				ps.setKolicina(kolicina);
//			ps.setStatus(Status.IZMENJENO);
		}
		
	}

	public void unosKolicineArtiklaSerije(int iDArtikla, int kolicina) {
		for(ArtiklSerije as : listaArtikalaSerije) {
			if(as.getArtikl().getIDArtikla() == iDArtikla)
				as.setKolicina(kolicina);
			as.setStatus(Status.IZMENJENO);
		}
	}

	public List<CisternaSerije> vratiListuCisterniSerije() {
//		List cisterne = new ArrayList<CisternaSerije>();
//		for(CisternaSerije cs: listaCisterniSerije)
//			if(cs.getStatus()!=Status.OBRISANO)
//				cisterne.add(cs);
//		return cisterne;
		return listaCisterniSerije;
	}

	public void dodajNovuCisternu() {
		cs = new CisternaSerije();	
	}

	public void postaviCisternu(Cisterna cis) {
		cs.setCisterna(cis);
	}

	public void postaviLOT(String lOT) {
		LOT = lOT;	
	}
	
	public void setLOT(String lOT) {
		LOT = lOT;
	}
	
	public String getLOT() {
		return LOT;
	}

	public void obrisiCisternuSerije(int idCisterne) {
		
		List<CisternaSerije> zaBrisanje = new ArrayList<CisternaSerije>();
		
		for(CisternaSerije cs : listaCisterniSerije) {
			if(cs.getCisterna().getIDCisterne() == idCisterne)
			{
				zaBrisanje.add(cs);
			}
		}
		
		for(CisternaSerije c : zaBrisanje) {
			c.getSerija().getListaCisterniSerije().remove(c);
			c.getCisterna().setUkupnoOtkupljenoMleko(c.getCisterna().getUkupnoOtkupljenoMleko() + c.getKolicina());
		}
		
	}

	public void ukloniProizvodSerije(int iDArtikla, int iDNalepnice) {
		for(ProizvodSerije ps : listaProizvodaSerije) {
			if(ps.getSp().getIDArtikla() == iDArtikla && ps.getN().getIDNalepnice()==iDNalepnice) {
				listaProizvodaSerije.remove(ps);
				//ps.setStatus(Status.OBRISANO);
				return;
			}
		}
		
	}

	public void postaviKolicinuZaSeriju(int kolicina) {
		if(kolicina <= cs.getCisterna().getUkupnoOtkupljenoMleko()) {
		cs.setKolicina(kolicina);
		cs.getCisterna().setUkupnoOtkupljenoMleko(cs.getCisterna().getUkupnoOtkupljenoMleko() - kolicina);
		}else {
			cs.setKolicina(cs.getCisterna().getUkupnoOtkupljenoMleko());
			cs.getCisterna().setUkupnoOtkupljenoMleko(0);
		}
	}

	public void ubaciCisternuUKolekciju() {
		dodajUKolekciju(cs);
		cs.setSerija(this);
	}
	
	public void setListaArtikalaSerije(List<ArtiklSerije> listaArtikalaSerije) {
		this.listaArtikalaSerije = listaArtikalaSerije;
	}
	
	public void setListaCisterniSerije(List<CisternaSerije> listaCisterniSerije) {
		this.listaCisterniSerije = listaCisterniSerije;
	}
	
	public void setListaProizvodaSerije(List<ProizvodSerije> listaProizvodaSerije) {
		this.listaProizvodaSerije = listaProizvodaSerije;
	}
	
	public List<ArtiklSerije> getListaArtikalaSerije() {
		return listaArtikalaSerije;
	}
	
	public List<CisternaSerije> getListaCisterniSerije() {
		return listaCisterniSerije;
	}
	
	public List<ProizvodSerije> getListaProizvodaSerije() {
		return listaProizvodaSerije;
	}

	public void ukloniArtiklSerije(int iDArtikla) {
	//	System.out.println("Ukloni artikl: " + iDArtikla);
		for(ArtiklSerije as : listaArtikalaSerije) {
			if(as.getArtikl().getIDArtikla() == iDArtikla) {
	//			System.out.println("Izmenjen status na obrisan");
	//			as.setStatus(Status.OBRISANO);
				return;
			}
		}
		
	}
	
	
}
