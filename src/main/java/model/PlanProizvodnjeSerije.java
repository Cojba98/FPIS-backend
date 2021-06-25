package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

import status.Status;
@XmlRootElement
@Entity
public class PlanProizvodnjeSerije {
@Id
	private int IDPlanaSerije;


	@OneToMany(mappedBy = "plan", cascade = {CascadeType.PERSIST, CascadeType.ALL, CascadeType.MERGE}, orphanRemoval = true)
	private List<PlaniraniProizvodSerije> planiraniProizvodiSerije = new ArrayList<PlaniraniProizvodSerije>();
	
	@OneToMany(mappedBy = "plan", cascade = {CascadeType.PERSIST, CascadeType.ALL, CascadeType.MERGE}, orphanRemoval = true)
	private List<ArtiklZaSeriju> artikliZaSeriju = new ArrayList<ArtiklZaSeriju>();
	
	@Transient
	PlaniraniProizvodSerije plp;
	
	@Transient
	Status status;
	
	@Transient
	private List<ArtiklZaSeriju> pomocni;
	
	public PlanProizvodnjeSerije() {
		
	}
	
//	public void setStatus(Status status) {
//		this.status = status;
//	}
//	
//	public Status getStatus() {
//		return status;
//	}
	
	public PlanProizvodnjeSerije(int iDPlanaSerije) {
		IDPlanaSerije = iDPlanaSerije;
	}

	public void setArtikliZaSeriju(List<ArtiklZaSeriju> artikliZaSeriju2) {
		this.artikliZaSeriju = artikliZaSeriju2;
	}
	
	public List<ArtiklZaSeriju> getArtikliZaSeriju() {
		return artikliZaSeriju;
	}
	
	public List<PlaniraniProizvodSerije> getPlaniraniProizvodiSerije() {
		return planiraniProizvodiSerije;
	}
	
	public void setPlaniraniProizvodiSerije(List<PlaniraniProizvodSerije> list) {
		this.planiraniProizvodiSerije = list;
	}
	
	public int getIDPlanaSerije() {
		return IDPlanaSerije;
	}

	public void setIDPlanaSerije(int iDPlanaSerije) {
		IDPlanaSerije = iDPlanaSerije;
	}
	
	public void kreirajNoviPlaniraniProizvodSerije() {
		plp = new PlaniraniProizvodSerije();
	//	plp.setStatus(Status.NOVO);
	}
	
	public void postaviProizvod(SopstveniProizvod sp) {
		plp.setSp(sp);
	}
	
	public void postaviNalepnicu(Nalepnica n) {
		plp.setN(n);
	}
	
	public void postaviKolicinu(int kolicina) {
		plp.setKolicina(kolicina);
	}
	
	public void dodajPlaniraniProizvodSerije() {
		System.out.println("Dodavanje proizvoda..");
		if(planiraniProizvodiSerije.contains(plp)) {
			PlaniraniProizvodSerije stariPlaniraniProizvod = planiraniProizvodiSerije.get(planiraniProizvodiSerije.indexOf(plp));
			stariPlaniraniProizvod.setStatus(Status.IZMENJENO);
			stariPlaniraniProizvod.setKolicina(stariPlaniraniProizvod.getKolicina() + plp.getKolicina());
		}else {
			plp.setPlan(this);
			//plp.setStatus(Status.NOVO);
		planiraniProizvodiSerije.add(plp);
		}

		//plp = null;
//		izracunajArtikleZaSeriju();
		
//		for(Sastojak s: plp.getSp().getSastojci()) {
//			ArtiklZaSeriju novi = new ArtiklZaSeriju();
//			novi.setArtikl(s.getArtikl());
//			novi.setKolicina(s.getKolicina()*plp.getKolicina());
//			novi.setPlan(this);
//			if(!artikliZaSeriju.contains(novi)) {
//				artikliZaSeriju.add(novi);
//			}else {
//			for(ArtiklZaSeriju azs: artikliZaSeriju) {
//				if(azs.getArtikl().getIDArtikla() == novi.getArtikl().getIDArtikla()) {
//					azs.setKolicina(azs.getKolicina() + novi.getKolicina());
//				}
//			}
//		}
//	}
	}
	
	public void pripremiProizvodZaIzmenu(int IDProizvoda, int IDNalepnice) {
		for(PlaniraniProizvodSerije planirani : planiraniProizvodiSerije) {
			System.out.println(" proiz: " + planirani.getSp().getIDArtikla() + " nale: " + planirani.getN().getIDNalepnice());
			if(planirani.getSp().getIDArtikla() == IDProizvoda && planirani.getN().getIDNalepnice()==IDNalepnice) {
				System.out.println("Proizvod pripremljen za izmenu. IDProizvoda: " + IDProizvoda + " IDNalepnice: " + IDNalepnice);
				plp = planirani;
				return;
//				System.out.println("Usao da izmeni");
//				planirani.setKolicina(kolicina);
//			planirani.setStatus(Status.IZMENJENO);
			}
		}
		
	}
	
	public void ukloniPlaniraniProizvodSerije(int IDProizvoda, int IDNalepnice) {
		for(PlaniraniProizvodSerije plp : planiraniProizvodiSerije) {
			if(plp.getSp().getIDArtikla()==IDProizvoda && plp.getN().getIDNalepnice()==IDNalepnice) {
				planiraniProizvodiSerije.remove(plp);

				break;
			}
		}
		
	//	izracunajArtikleZaSeriju();
	}
	
	public List<PlaniraniProizvodSerije> vratiListuPlaniranihProizvoda() {
		return planiraniProizvodiSerije;
	}
	
//	public void izracunajArtikleZaSeriju() {
//		List<ArtiklZaSeriju> noviArtikli = new ArrayList<ArtiklZaSeriju>();
//		for(PlaniraniProizvodSerije planirani : planiraniProizvodiSerije) {
//			for(Sastojak s : planirani.getSp().getSastojci()) {
//				System.out.println("Duzina sastojaka: " + planirani.getSp().getSastojci().size());
//				ArtiklZaSeriju novi = new ArtiklZaSeriju();
//				novi.setArtikl(s.getArtikl());
//				novi.setKolicina(s.getKolicina() * planirani.getKolicina());
//				novi.setPlan(this);
//				if(!noviArtikli.contains(novi)) {
//					noviArtikli.add(novi);
//				}else {
//					for(ArtiklZaSeriju azs : noviArtikli) {
//						if(azs.equals(noviArtikli)) {
//							azs.setKolicina(azs.getKolicina() + novi.getKolicina());
//						}
//					}
//				}
//			}
//		}
		
		
	//	pomocni = noviArtikli;
//		
//		System.out.println("Duzina artikala za seriju: " + artikliZaSeriju.size());
		
//		List<ArtiklZaSeriju> pomocni = new ArrayList<ArtiklZaSeriju>(artikliZaSeriju);
//		
//		for(ArtiklZaSeriju stariArtikl: pomocni) {
//			if(stariArtikl.getPlan() != null && stariArtikl.getPlan().getArtikliZaSeriju()!=null) {
//				stariArtikl.getPlan().getArtikliZaSeriju().remove(stariArtikl);
//			}
//		}
//		pomocni = new ArrayList<ArtiklZaSeriju>(artikliZaSeriju);
//		for(ArtiklZaSeriju stariArtikl: pomocni) {
//			artikliZaSeriju.remove(stariArtikl);
//		}
//		
//		for(ArtiklZaSeriju noviArtikl : noviArtikli) {
//			noviArtikl.setPlan(this);
//			artikliZaSeriju.add(noviArtikl);
//		}
		
		
//		ArrayList<ArtiklZaSeriju> sviArtikli = new ArrayList<ArtiklZaSeriju>();
//		for(PlaniraniProizvodSerije plp : planiraniProizvodiSerije) {
//			if(plp.getStatus()!=Status.OBRISANO) {
//			for(Sastojak s : plp.getSp().getSastojci()) {
//				ArtiklZaSeriju artiklZaSeriju = new ArtiklZaSeriju();
//				artiklZaSeriju.setArtikl(s.getArtikl());
//				if(sviArtikli.contains(artiklZaSeriju)) {
//					ArtiklZaSeriju postojeciArtikl = sviArtikli.get(sviArtikli.indexOf(artiklZaSeriju));
//					postojeciArtikl.setKolicina(postojeciArtikl.getKolicina() + s.getKolicina()*plp.getKolicina());
//				}else {
//					artiklZaSeriju.setKolicina(s.getKolicina()*plp.getKolicina());
//					artiklZaSeriju.setPlan(this);
//					sviArtikli.add(artiklZaSeriju);
//					
//				}	
//			}
//		}
//		}
//		
//		List<ArtiklZaSeriju> stariArtikli = new ArrayList<ArtiklZaSeriju>(artikliZaSeriju);
//		
//		for(ArtiklZaSeriju stariArtikl: stariArtikli) {
//			if(stariArtikl.getPlan()!=null && stariArtikl.getPlan().getArtikliZaSeriju()!=null) {
//			stariArtikl.getPlan().getArtikliZaSeriju().remove(stariArtikl);
//		}
//		}
//		
//		for(ArtiklZaSeriju noviArtikl : sviArtikli) {
//			noviArtikl.setPlan(this);
//			artikliZaSeriju.add(noviArtikl);
//		}
		
//		for(ArtiklZaSeriju azs : sviArtikli) {
//			System.out.println("Novi artikl: " + azs.getArtikl().getIDArtikla() + " " + " kolicina: " + azs.getKolicina());
//		if(artikliZaSeriju.contains(azs)) {
//			
//			ArtiklZaSeriju stariAzs = artikliZaSeriju.get(artikliZaSeriju.indexOf(azs));
//			stariAzs.setStatus(Status.NEIZMENJENO);
//			if(stariAzs.getKolicina() != azs.getKolicina()) {
//				stariAzs.setKolicina(azs.getKolicina());
//				stariAzs.setStatus(Status.IZMENJENO);
//			}
//			
//		}else {
//			azs.setStatus(Status.NOVO);
//			artikliZaSeriju.add(azs);
//		}	
//		}
		
		
	//}

	public List<ArtiklZaSeriju> vratiListuArtikalaZaSeriju() {
		if(pomocni == null)
			pomocni = new ArrayList<ArtiklZaSeriju>(artikliZaSeriju);
		return pomocni;
//		return artikliZaSeriju;
	}

	public void pripremiSeZaCuvanje() {
		
		List<ArtiklZaSeriju> zaBrisanje = new ArrayList<ArtiklZaSeriju>();
		
		for(ArtiklZaSeriju azs : artikliZaSeriju) {
			if(!pomocni.contains(azs)) {
				zaBrisanje.add(azs);
			}
		}
		
		for(ArtiklZaSeriju azs : zaBrisanje) {
			artikliZaSeriju.remove(azs);
		}
		
		for(ArtiklZaSeriju azs : pomocni) {
			if(artikliZaSeriju.contains(azs)) {
				artikliZaSeriju.get(artikliZaSeriju.indexOf(azs)).setKolicina(azs.getKolicina());
			}else {
				artikliZaSeriju.add(azs);
			}
		}
		
	}

	public void unosKolicineProizvodaSerije(int iDArtikla, int iDNalepnice, int kolicina) {
		for(PlaniraniProizvodSerije planirani: planiraniProizvodiSerije) {
			if(planirani.getSp().getIDArtikla() == iDArtikla && planirani.getN().getIDNalepnice() == iDNalepnice) {
				planirani.setKolicina(kolicina);
			}
		}
	//	izracunajArtikleZaSeriju();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + IDPlanaSerije;
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
		PlanProizvodnjeSerije other = (PlanProizvodnjeSerije) obj;
		if (IDPlanaSerije != other.IDPlanaSerije)
			return false;
		return true;
	}

	public void inicijalizujArtikleZaSeriju() {
		pomocni = new ArrayList<ArtiklZaSeriju>();		
	}
//	
	public void dodajArtiklZaSeriju(Artikl artikl, int kolicina) {
		ArtiklZaSeriju azs = new ArtiklZaSeriju();
		azs.setArtikl(artikl);
		azs.setKolicina(kolicina);
		azs.setPlan(this);
		if(pomocni.contains(azs)) {
			pomocni.get(pomocni.indexOf(azs)).setKolicina(pomocni.get(pomocni.indexOf(azs)).getKolicina() + azs.getKolicina());
		}else {
			pomocni.add(azs);
		}
		
	}


}
