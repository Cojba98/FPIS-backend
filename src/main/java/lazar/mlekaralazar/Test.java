package lazar.mlekaralazar;




import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import ID.ProizvodSerijeID;
import dbbroker.DBBroker;
import model.Artikl;
import model.ArtiklSerije;
import model.ArtiklZaSeriju;
import model.Cisterna;
import model.CisternaSerije;
import model.Nalepnica;
import model.PlanProizvodnjeSerije;
import model.PlaniraniProizvodSerije;
import model.ProizvodSerije;
import model.Sastojak;
import model.Serija;
import model.SopstveniProizvod;
import status.Status;

public class Test {

	public static void main(String[] args) {
		
		System.out.println("Radi...");
		
//		SopstveniProizvod sp = new  SopstveniProizvod();
//		sp.setIDArtikla(1);
//		sp.setKolicinaMleka(1000);
//		sp.setNazivArtikla("Blacki sir");
//		sp.setOpis("Odlican sir");
//		
//		SopstveniProizvod sp2 = new  SopstveniProizvod();
//		sp2.setIDArtikla(2);
//		sp2.setKolicinaMleka(1000);
//		sp2.setNazivArtikla("Blacki sir");
//		sp2.setOpis("Odlican sir");
//		
//		Serija s = new Serija();
//		s.setLOT("asd");
//		
//		Artikl a1 = new Artikl(11);
//		a1.setNazivArtikla("Sirilo");
//
//		Artikl a2 = new Artikl(12);
//		a2.setNazivArtikla("Reagensi");
//		
//		Sastojak s1 = new Sastojak();
//		s1.setSopstveniProizvod(sp);
//		s1.setArtikl(a1);
//		s1.setKolicina(10);
//		
//		Sastojak s2 = new Sastojak();
//		s2.setSopstveniProizvod(sp);
//		s2.setArtikl(a2);
//		s2.setKolicina(5);
//		
//		Sastojak s3 = new Sastojak();
//		s3.setSopstveniProizvod(sp2);
//		s3.setArtikl(a2);
//		s3.setKolicina(15);
//		
//		sp.getSastojci().add(s1);
//		sp.getSastojci().add(s2);
//		sp2.getSastojci().add(s3);
//		
//		Cisterna c = new Cisterna();
//		c.setDatum(new Date());
//		c.setIDCisterne(3);
//		c.setIspravna(true);
//		c.setUkupnoOtkupljenoMleko(234);
//		
//		CisternaSerije cs = new CisternaSerije();
//		cs.setCisterna(c);
//		cs.setKolicina(340);
//		cs.setSerija(s);
//		
//		ArtiklSerije as = new ArtiklSerije();
//		as.setArtikl(a1);
//		as.setKolicina(10);
//		as.setSerija(s);
//		
//		ArtiklSerije as2 = new ArtiklSerije();
//		as2.setArtikl(a2);
//		as2.setKolicina(13);
//		as2.setSerija(s);
//		
//		Nalepnica n1 = new Nalepnica();
//		n1.setIDNalepnice(1);
//		n1.setOpis("KPlus nalepnica");
//		
//		Nalepnica n2 = new Nalepnica();
//		n2.setIDNalepnice(2);
//		n2.setOpis("Lazar nalepnica");
//		
//		ProizvodSerije ps1 = new ProizvodSerije();
//		ps1.setKolicina(100);
//		ps1.setN(n1);
//		ps1.setSerija(s);
//		ps1.setSp(sp);
//		
//		ProizvodSerije ps2 = new ProizvodSerije();
//		ps2.setKolicina(140);
//		ps2.setN(n2);
//		ps2.setSerija(s);
//		ps2.setSp(sp);
//		
//		PlanProizvodnjeSerije plan = new PlanProizvodnjeSerije();
//		plan.setIDPlanaSerije(1);
//		PlaniraniProizvodSerije p1 = new PlaniraniProizvodSerije();
//		p1.setN(n1);
//		p1.setKolicina(100);
//		p1.setPlan(plan);
//		p1.setSp(sp);
//		
//		ArtiklZaSeriju azs = new ArtiklZaSeriju();
//		azs.setArtikl(a1);
//		azs.setKolicina(33);
//		azs.setPlan(plan);
		
		Configuration con = new Configuration().configure().addAnnotatedClass(Artikl.class).addAnnotatedClass(ArtiklSerije.class)
				.addAnnotatedClass(Serija.class).addAnnotatedClass(ProizvodSerije.class).addAnnotatedClass(Nalepnica.class).addAnnotatedClass(CisternaSerije.class)
				.addAnnotatedClass(Cisterna.class).addAnnotatedClass(SopstveniProizvod.class).addAnnotatedClass(Sastojak.class);
		SessionFactory sf =  con.buildSessionFactory();
		
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		
//		session.save(s1);
//		session.save(s2);
//		session.save(s3);
//		
//		Criteria criteria = session.createCriteria(SopstveniProizvod.class);
	//	List<SopstveniProizvod> proizvodi = criteria.list();
		
//		Criteria criteria = session.createCriteria(Sastojak.class);
//		List<Sastojak> proizvodi = criteria.list();
//		
//		for(Sastojak sa : proizvodi) {
//			System.out.println("Sastojak: " + sa.getArtikl().getNazivArtikla() + " " + sa.getSopstveniProizvod().getNazivArtikla());
//
//		}
		
		int noviID = -2;
		List<Integer> idjevi = new ArrayList<Integer>();
		Query upit = session.createQuery("SELECT IDPlanaSerije from PlanProizvodnjeSerije");
		for(Iterator it = upit.iterate(); it.hasNext();) {
			noviID =  (Integer) it.next();
			System.out.println("ID: " + noviID);
			idjevi.add(noviID);
		}
		
		
		
//		for(SopstveniProizvod sop : proizvodi) {
//			for(Sastojak sa : sop.getSastojci()) {
//				System.out.println("Sastojak: " + sa.getArtikl().getNazivArtikla() + " " + sa.getSopstveniProizvod().getNazivArtikla());
//			}
//		}
		
//		System.out.println("Duzina sopstvenih proizvoda: " + proizvodi.size());
		
		tr.commit();
//		session.save(s);
//		session.save(a1);
//		session.save(a2);
//		session.save(c);
//		session.save(n1);
//		session.save(n2);
//		session.save(sp);
//		session.save(s1);
//		session.save(s2);
//		session.save(as);
//		session.save(as2);
//		session.save(cs);
//		session.save(ps1);
//		session.save(ps2);
		
///		session.save(plan);
//		session.save(azs);
//		session.save(p1);
		
//		DBBroker db = new DBBroker();
//		db.zapamtiSeriju(s);
				
		
		//Serija ss = (Serija) session.get(Serija.class, "5");
//		
//		
//		for(ArtiklSerije ass: ss.getListaArtikalaSerije()) {
//			System.out.println(ass.getArtikl().getNazivArtikla());
//		}
	

 
		System.out.println("Gotovo...");
	}
	
}
