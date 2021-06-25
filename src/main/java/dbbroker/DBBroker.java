package dbbroker;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

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

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DBBroker {
	
	private Connection conn;
	private Configuration con;
	private SessionFactory sf;
	private Session session;
	private Transaction tr;
	
	
	public DBBroker() {
		con = new Configuration().configure().addAnnotatedClass(Artikl.class).addAnnotatedClass(ArtiklSerije.class)
				.addAnnotatedClass(Serija.class).addAnnotatedClass(ProizvodSerije.class).addAnnotatedClass(Nalepnica.class).addAnnotatedClass(CisternaSerije.class)
				.addAnnotatedClass(Cisterna.class).addAnnotatedClass(SopstveniProizvod.class).addAnnotatedClass(Sastojak.class);
		
		sf =  con.buildSessionFactory();
		session = sf.openSession();
		tr = session.beginTransaction();
	}
	
	public void pokreniDBTransakciju(){
	//	try {
			//Class.forName("com.mysql.jdbc.Driver");
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mlekaralazar","root","");
			//conn. setAutoCommit(false);
			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		}
	
	public void potvrdiDBTransakciju() {
//		try {
		//conn.commit();
		//conn.close();
//		tr.commit();
		session.close();
//		} catch (SQLException e) {
//		System.err.println("Greska prilikom commit operacije... -> " + e);
//		}
		}
	
		public void ponistiDBTransakciju() {
//		try {
////		conn.rollback();
////		conn.close();
		tr.rollback();
		session.close();
//		} catch (SQLException e) {
//		System.err.println("Greska prilikom rollback operacije... -> " + e);
//		}
		}
		
		public List<SopstveniProizvod> vratiListuProizvoda()  {
//			try {
//				
//				Statement state = conn.createStatement();
//				String sql = "select * from sopstveniproizvod join artikl on sopstveniproizvod.idartikla=artikl.idartikla";
//				return state.executeQuery(sql);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			return null;
			
			Criteria criteria = session.createCriteria(SopstveniProizvod.class);
			List<SopstveniProizvod> proizvodi = criteria.list();
			
			return proizvodi;
			
		}
		
		public List<Nalepnica> vratiListuNalepnica()  {
			
			Criteria criteria = session.createCriteria(Nalepnica.class);
			List<Nalepnica> nalepnice = criteria.list();
			
			return nalepnice;
		}
		
		public int vratiNoviIDPlana() {
			
//			try {
//				
//			Statement state = conn.createStatement();
//			String sql = "select max(IDPlanaSerije) from planproizvodnjeserije";
//			ResultSet rs = state.executeQuery(sql);
//			//conn.close();
//			if(rs.next()) {
//				return (rs.getInt(1) + 1);
//			}else {
//				return 1;
//			}
//			}catch(Exception e) {
//				e.printStackTrace();
//				
//			}
//			return 1;
			int noviID = -2;
			Query upit = session.createQuery("SELECT max(IDPlanaSerije) from PlanProizvodnjeSerije");
			for(Iterator it = upit.iterate(); it.hasNext();) {
				noviID =  (Integer) it.next();
			}
		return (noviID + 1);
		}
		
		public SopstveniProizvod pronadjiProizvod(int IDArtikla) {
			
//			try {
//				
//				Statement state = conn.createStatement();
//				String sql = "select * from sopstveniproizvod join artikl on sopstveniproizvod.idartikla=artikl.idartikla where sopstveniproizvod.idartikla=" + IDArtikla;
//				ResultSet rs = state.executeQuery(sql);
//				SopstveniProizvod sp = new SopstveniProizvod(-1);
//				
//				while(rs.next()) {
//					sp.setIDArtikla(rs.getInt("IDArtikla"));
//					sp.setKolicinaMleka(rs.getInt("kolicinamleka"));
//					sp.setNazivArtikla(rs.getString("nazivartikla"));
//					sp.setOpis(rs.getString("opis"));
//					sp.setZapreminaUMl(rs.getInt("zapreminauml"));
//					
//				}
//				
//				sql = "select * from sastojak join artikl on sastojak.idartiklasastojka=artikl.idartikla where idartiklaproizvoda=" + IDArtikla;
//				rs = state.executeQuery(sql);
//				
//				while(rs.next()) {
//					Artikl a = new Artikl(rs.getInt("artikl.idartikla"));
//					a.setNazivArtikla("artikl.nazivartikla");
//					Sastojak s = new Sastojak();
//					s.setArtikl(a);
//					s.setKolicina(rs.getInt("sastojak.kolicina"));
//					sp.getSastojci().add(s);
//				}
//				conn.close();
//				return sp;
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return null;
			
			SopstveniProizvod sp = (SopstveniProizvod) session.get(SopstveniProizvod.class, IDArtikla);
			
			return sp;
		}
		
		public boolean zapamtiPlan(PlanProizvodnjeSerije pps) {
//			System.out.println("DBB pamti... Status plana: " + pps.getStatus());
//			if(pps.getStatus().equals(Status.NOVO)) {
//			try {
//			String sql = "insert into planproizvodnjeserije values()";
//			PreparedStatement state = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//			state.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
//			ResultSet rs = state.getGeneratedKeys();
//			int generatedKey =0;
//			while(rs.next()) {
//				generatedKey = rs.getInt(1);
//			}
//			for(PlaniraniProizvodSerije plp : pps.getPlaniraniProizvodiSerije()) {
//				
//				sql = "insert into planiraniproizvodserije values(?, ?, ?, ?)";
//				
//				state = conn.prepareStatement(sql);
//				state.setInt(1, plp.getSp().getIDArtikla());
//				state.setInt(2, plp.getN().getIDNalepnice());
//				state.setInt(3, plp.getKolicina());
//				state.setInt(4, generatedKey);
//				System.out.println("Izvrseni upit: " + state.toString());
//				state.executeUpdate();
//			}
//			
//			for(ArtiklZaSeriju azs : pps.getArtikliZaSeriju()) {
//				sql = "insert into artiklzaseriju values(?, ?, ?)";
//				state = conn.prepareStatement(sql);
//				state.setInt(1, azs.getArtikl().getIDArtikla());
//				state.setInt(2, azs.getKolicina());
//				state.setInt(3, generatedKey);
//				System.out.println("Izvrseni upit: " + state.toString());
//				state.executeUpdate();
//			}
//			
//			System.out.println("Uspesno izvrseno");
//			return true;
//			}catch(Exception e) {
//				e.printStackTrace();
//				System.out.println("Neuspesno izvrseno");
//			return false;
//			}
//			}else {
//				
//				try {
//
//					PreparedStatement state;
//					
//					for(PlaniraniProizvodSerije plp : pps.getPlaniraniProizvodiSerije()) {
//						
//						if(plp.getStatus().equals(Status.NOVO)) {
//						String sql = "insert into planiraniproizvodserije values(?, ?, ?, ?)";
//						
//						state = conn.prepareStatement(sql);
//						state.setInt(1, plp.getSp().getIDArtikla());
//						state.setInt(2, plp.getN().getIDNalepnice());
//						state.setInt(3, plp.getKolicina());
//						state.setInt(4, pps.getIDPlanaSerije());
//						System.out.println("Izvrseni upit: " + state.toString());
//						state.executeUpdate();
//						}
//						
//						if(plp.getStatus().equals(Status.IZMENJENO)) {
//							String sql = "update planiraniproizvodserije set kolicina="+plp.getKolicina()+" where IDArtikla="+plp.getSp().getIDArtikla()+
//									" and IDNalepnice="+plp.getN().getIDNalepnice()+" and IdPlanaProizvodnjeSerije="+pps.getIDPlanaSerije();
//							
//							Statement statement = conn.createStatement();
//							System.out.println("Izvrseni upit: " + sql);
//							statement.executeUpdate(sql);
//							}
//						
//						if(plp.getStatus().equals(Status.OBRISANO)) {
//							String sql = "delete from planiraniproizvodserije where IDArtikla="+plp.getSp().getIDArtikla()+
//									" and IDNalepnice="+plp.getN().getIDNalepnice()+" and IdPlanaProizvodnjeSerije="+pps.getIDPlanaSerije();
//							
//							Statement statement = conn.createStatement();
//							System.out.println("Izvrseni upit: " + sql);
//							statement.executeUpdate(sql);
//							}
//					}
//					System.out.println("Duzina artikala: " + pps.getArtikliZaSeriju().size());
//					for(ArtiklZaSeriju azs : pps.getArtikliZaSeriju()) {
//						System.out.println("Usao da upise artikl: " + azs.getStatus());
//						if(azs.getStatus()==Status.NOVO) {
//						String sql = "insert into artiklzaseriju values(?, ?, ?)";
//						state = conn.prepareStatement(sql);
//						state.setInt(1, azs.getArtikl().getIDArtikla());
//						state.setInt(2, azs.getKolicina());
//						state.setInt(3, pps.getIDPlanaSerije());
//						System.out.println("Izvrseni upit: " + state.toString());
//						state.executeUpdate();
//						}
//						if(azs.getStatus()==Status.IZMENJENO) {
//							String sql = "update artiklzaseriju set kolicina="+azs.getKolicina() + " where idartikla="+azs.getArtikl().getIDArtikla()+" and idplanaproizvodnjeserije="+pps.getIDPlanaSerije();
//							state = conn.prepareStatement(sql);
//							System.out.println("Izvrseni upit: " + sql);
//							state.executeUpdate();
//						}
//						if(azs.getStatus()==Status.OBRISANO) {
//							String sql = "delete from artiklzaseriju where idartikla="+azs.getArtikl().getIDArtikla()+" and idplanaproizvodnjeserije="+pps.getIDPlanaSerije();
//							state = conn.prepareStatement(sql);
//							System.out.println("Izvrseni upit: " + sql);
//							state.executeUpdate();
//						}
//					}
//					
//					System.out.println("Uspesno izvrseno");
//					return true;
//					}catch(Exception e) {
//						e.printStackTrace();
//						System.out.println("Neuspesno izvrseno");
//					return false;
//					}
//				
//			}
			
			try {
			session.save(pps);
			tr.commit();
			return true;
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return false;
		}

		public Nalepnica pronadjiNalepnicu(int iDNalepnice) {
			
//			try {
//				
//				Statement state = conn.createStatement();
//				String sql = "select * from nalepnica where idnalepnice=" + iDNalepnice;
//				ResultSet rs = state.executeQuery(sql);
//				Nalepnica n = new Nalepnica();
//				while(rs.next()) {
//					n.setOpis(rs.getString("opisnalepnice"));
//					n.setIDNalepnice(rs.getInt("IDNalepnice"));
//				}
//				conn.close();
//				return n;
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return null;
			
			
			Nalepnica nalepnica = (Nalepnica) session.get(Nalepnica.class, iDNalepnice);
			
			return nalepnica;
		}
		
		public List<PlanProizvodnjeSerije> vratiListuPlanovaProizvodnjeSerije() {
			
//			try {
//				
//				Statement state = conn.createStatement();
//				String sql = "select * from planproizvodnjeserije";
//				ResultSet rs = state.executeQuery(sql);
//				return rs;
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return null;
			//Criteria criteria = session.createCriteria(PlanProizvodnjeSerije.class);
			//List<PlanProizvodnjeSerije> planovi = criteria.list();
			//
			List<PlanProizvodnjeSerije> planovi = new ArrayList<PlanProizvodnjeSerije>();
			Query upit = session.createQuery("SELECT IDPlanaSerije from PlanProizvodnjeSerije");
			for(Iterator it = upit.iterate(); it.hasNext();) {
				PlanProizvodnjeSerije plan = new PlanProizvodnjeSerije((Integer) it.next());
				planovi.add(plan);
			}
			
			return planovi;
			
		}

		public List<Sastojak> vratiListuSastavnica(){
			Criteria criteria = session.createCriteria(Sastojak.class);
			List<Sastojak> sastojci = criteria.list();
			return sastojci;
		}
		
		public PlanProizvodnjeSerije pronadjiPlanProizvodnjeSerije(int iDPlanaProizvodnjeSerije) {
			
//			
//			
//			try {
//				String sql = "select * from planproizvodnjeserije where idplanaserije=" + iDPlanaProizvodnjeSerije;
//				Statement state = conn.createStatement();
//				ResultSet rs = state.executeQuery(sql);
//				PlanProizvodnjeSerije ps = new PlanProizvodnjeSerije(iDPlanaProizvodnjeSerije);
//				while(rs.next()) {
//					ps.setIDPlanaSerije(rs.getInt("idplanaserije"));	
//				}
//				
//				sql = "select * from planiraniproizvodserije join artikl on planiraniproizvodserije.idartikla=artikl.idartikla "
//						+ "join sopstveniproizvod on artikl.idartikla=sopstveniproizvod.idartikla join "
//						+ "nalepnica on planiraniproizvodserije.idnalepnice=nalepnica.idnalepnice where idplanaproizvodnjeserije=" + ps.getIDPlanaSerije();
//				rs = state.executeQuery(sql);
//				while(rs.next()) {
//					PlaniraniProizvodSerije plp = new PlaniraniProizvodSerije();
//					plp.setKolicina(rs.getInt("planiraniproizvodserije.kolicina"));
//					SopstveniProizvod sp = new SopstveniProizvod(rs.getInt("planiraniproizvodserije.idartikla"));
//					sp.setNazivArtikla(rs.getString("artikl.nazivartikla"));
//					sp.setKolicinaMleka(rs.getInt("sopstveniproizvod.kolicinamleka"));
//					sp.setZapreminaUMl(rs.getInt("sopstveniproizvod.zapreminauml"));
//					sp.setOpis(rs.getString("sopstveniproizvod.opis"));
//					plp.setSp(sp);
//					Nalepnica n = new Nalepnica();
//					n.setIDNalepnice(rs.getInt("nalepnica.idnalepnice"));
//					n.setOpis(rs.getString("nalepnica.opisnalepnice"));
//					plp.setN(n);
//					plp.setStatus(Status.NEIZMENJENO);
//					ps.getPlaniraniProizvodiSerije().add(plp);
//				}
//				
//				for(PlaniraniProizvodSerije plp : ps.getPlaniraniProizvodiSerije()) {
//					sql = "select * from sastojak join artikl on sastojak.idartiklasastojka=artikl.idartikla where sastojak.idartiklaproizvoda="+plp.getSp().getIDArtikla();
//					 rs = state.executeQuery(sql);
//					while(rs.next()) {
//						Sastojak s = new Sastojak();
//						Artikl a = new Artikl(rs.getInt("artikl.idartikla"));
//						a.setNazivArtikla(rs.getString("artikl.nazivartikla"));
//						s.setArtikl(a);
//						s.setKolicina(rs.getInt("sastojak.kolicina"));
//						plp.getSp().getSastojci().add(s);
//					}
//				}
//				
//				sql = "select * from artiklzaseriju join artikl on artiklzaseriju.idartikla=artikl.idartikla where idplanaproizvodnjeserije=" + ps.getIDPlanaSerije();
//				rs = state.executeQuery(sql);
//				
//				while(rs.next()) {
//					ArtiklZaSeriju azs = new ArtiklZaSeriju();
//					azs.setKolicina(rs.getInt("artiklzaseriju.kolicina"));
//					Artikl a = new Artikl(rs.getInt("artikl.idartikla"));
//					a.setNazivArtikla(rs.getString("artikl.nazivArtikla"));
//					azs.setArtikl(a);
//					ps.getArtikliZaSeriju().add(azs);
//				}
//				ps.setPlaniraniProizvodiSerije(ps.getPlaniraniProizvodiSerije());
//				ps.setStatus(Status.NEIZMENJENO);
//				return ps;
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return null;
			if(!session.isOpen() || !tr.isActive()) {
				session = sf.openSession();
				tr = session.beginTransaction();
			}
			
			PlanProizvodnjeSerije plan = (PlanProizvodnjeSerije) session.get(PlanProizvodnjeSerije.class, iDPlanaProizvodnjeSerije);
			
			return plan;
			
		}

		public List<Cisterna> vratiListuCisterni() throws SQLException {
			
//			String sql = "select * from cisterna";
//			Statement state = conn.createStatement();
//			return state.executeQuery(sql);
				
			Criteria criteria = session.createCriteria(Cisterna.class);
			List<Cisterna> cisterne = criteria.list();
			
			return cisterne;
			
		}

		public Cisterna pronadjiCisternu(int idCisterne) throws SQLException {
			
//			
//			String sql = "select * from cisterna where idcisterne="+idCisterne + " and ukupnoOtkupljenoMleko>0";
//			Statement state = conn.createStatement();
//			ResultSet rs = state.executeQuery(sql);
//			Cisterna cis = new Cisterna();
//			if(rs.next()) {
//				cis.setIDCisterne(rs.getInt("idcisterne"));
//				cis.setDatum(rs.getDate("datum"));
//				cis.setIspravna(rs.getBoolean("ispravna"));
//				cis.setUkupnoOtkupljenoMleko(rs.getInt("ukupnootkupljenomleko"));
//				return cis;
//			}
//			return null;
			
			
			Cisterna cis = (Cisterna) session.get(Cisterna.class, idCisterne);
			
			return cis;
		}

		public boolean zapamtiSeriju(Serija s) {
//			try {
//				if(s.getStatus()==Status.NOVO) {
//				System.out.println("LOT je: " + s.getLOT());
//			String sql = "insert into serija values(?)";
//			PreparedStatement state = conn.prepareStatement(sql);
//			state.setString(1, s.getLOT());
//			System.out.println("Izvrsen upit: " + state.toString());
//			state.executeUpdate();
//			
//			
//			for(ArtiklSerije as : s.vratiListuArtikalaSerije()) {
//				sql = "insert into artiklserije values(?,?,?)";
//				state = conn.prepareStatement(sql);
//				state.setString(1, s.getLOT());
//				state.setInt(2, as.getArtikl().getIDArtikla());
//				state.setInt(3, as.getKolicina());
//				System.out.println("Izvrsen upit: " + state.toString());
//				state.executeUpdate();
//				
//			}
//			
//			for(ProizvodSerije ps : s.vratiListuProizvoda()) {
//				sql = "insert into proizvodserije values(?,?,?,?)";
//				state = conn.prepareStatement(sql);
//				state.setString(1, s.getLOT());
//				state.setInt(2, ps.getSp().getIDArtikla());
//				state.setInt(3, ps.getN().getIDNalepnice());
//				state.setInt(4, ps.getKolicina());
//				System.out.println("Izvrsen upit: " + state.toString());
//				state.executeUpdate();
//			}
//			
//			for(CisternaSerije cs : s.vratiListuCisterniSerije()) {
//				sql = "insert into cisternaserije values(?,?,?)";
//				state=conn.prepareStatement(sql);
//				state.setString(1, s.getLOT());
//				state.setInt(2, cs.getCisterna().getIDCisterne());
//				state.setInt(3, cs.getKolicina());
//				System.out.println("Izvrsen upit: " + state.toString());
//				state.executeUpdate();
//				sql = "update cisterna set ukupnoOtkupljenoMleko="+(cs.getCisterna().getUkupnoOtkupljenoMleko()-cs.getKolicina()) + " where idcisterne="+cs.getCisterna().getIDCisterne();
//				state=conn.prepareStatement(sql);
//				System.out.println("Izvrsen upit: " + state.toString());
//				state.executeUpdate();
//			}
//				}else {
//					
//					System.out.println("LOT je: " + s.getLOT());
//					String sql;
//					PreparedStatement state;
//					
//					for(ArtiklSerije as : s.vratiListuArtikalaSerije()) {
//						switch(as.getStatus()) {
//						case IZMENJENO:
//							sql = "update artiklserije set kolicina=" + as.getKolicina() + " where LOT='" + s.getLOT() +"' and IDArtikla="+as.getArtikl().getIDArtikla();
//							state=conn.prepareStatement(sql);
//							state.executeUpdate();
//							break;
//						case OBRISANO:
//							sql = "delete from artiklserije where LOT='" + s.getLOT() +"' and IDArtikla="+as.getArtikl().getIDArtikla();
//							System.out.println("Brisanje: " + sql);
//							state = conn.prepareStatement(sql);
//							state.executeUpdate();
//							break;
//						}
//						
//					}
//					
//					for(ProizvodSerije ps : s.vratiListuProizvoda()) {
//						
//						switch(ps.getStatus()) {
//						case IZMENJENO:
//							sql = "update proizvodserije set kolicina=" + ps.getKolicina() + " where LOT='" + s.getLOT() +"' and IDArtikla="+ps.getSp().getIDArtikla()+" and IDNalepnice=" + ps.getN().getIDNalepnice();
//							state=conn.prepareStatement(sql);
//							state.executeUpdate();
//							break;
//						case OBRISANO:
//							sql = "delete from proizvodserije where LOT='" + s.getLOT() +"' and IDArtikla="+ps.getSp().getIDArtikla()+" and IDNalepnice=" + ps.getN().getIDNalepnice();
//							state = conn.prepareStatement(sql);
//							state.executeUpdate();
//							break;
//						}
//						
//					}
//					
//					for(CisternaSerije cs : s.vratiListuCisterniSerije()) {
//						boolean izmenjeno = false;
//						switch(cs.getStatus()) {
//						case NEIZMENJENO:
//							break;
//						case IZMENJENO:
//							sql = "select * from cisternaserije where LOT='" + s.getLOT() +"' and IDCisterne="+cs.getCisterna().getIDCisterne();
//							Statement st = conn.createStatement();
//							ResultSet result = st.executeQuery(sql);
//							int staroMleko = 0;
//							while(result.next()) {
//							staroMleko = result.getInt("kolicina");
//							}
//							int novoMleko = cs.getKolicina();
//							sql = "update cisterna set ukupnoOtkupljenoMleko="+(cs.getCisterna().getUkupnoOtkupljenoMleko()+(staroMleko-novoMleko)) + " where idcisterne="+cs.getCisterna().getIDCisterne();							
//							state=conn.prepareStatement(sql);
//							state.executeUpdate();
//							sql = "update cisternaserije set kolicina=" + cs.getKolicina() + " where LOT='" + s.getLOT() +"' and IDCisterne="+cs.getCisterna().getIDCisterne();
//							state=conn.prepareStatement(sql);
//							state.executeUpdate();
//							break;
//						case OBRISANO:
//							sql = "delete from cisternaserije where LOT='" + s.getLOT() +"' and IDCisterne="+cs.getCisterna().getIDCisterne();
//							state = conn.prepareStatement(sql);
//							state.executeUpdate();
//							sql = "update cisterna set ukupnoOtkupljenoMleko="+(cs.getCisterna().getUkupnoOtkupljenoMleko()+ cs.getKolicina()) + " where idcisterne="+cs.getCisterna().getIDCisterne();
//							state=conn.prepareStatement(sql);
//							state.executeUpdate();
//							break;
//						case NOVO:
//							sql = "insert into cisternaserije values(?,?,?)";
//							state=conn.prepareStatement(sql);
//							state.setString(1, s.getLOT());
//							state.setInt(2, cs.getCisterna().getIDCisterne());
//							state.setInt(3, cs.getKolicina());
//							System.out.println("Izvrsen upit: " + state.toString());
//							state.executeUpdate();
//							sql = "update cisterna set ukupnoOtkupljenoMleko="+(cs.getCisterna().getUkupnoOtkupljenoMleko()-cs.getKolicina()) + " where idcisterne="+cs.getCisterna().getIDCisterne();
//							state=conn.prepareStatement(sql);
//							state.executeUpdate();
//							break;
//							
//						}
//					}
//					
//				}
//			return true;
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
			
			try {
				
//			List<Nalepnica> nalepnice = new ArrayList<Nalepnica>();
			
//			for(ProizvodSerije ps : s.getListaProizvodaSerije()) {
//				if(!nalepnice.contains(ps.getN())) {
//					nalepnice.add(ps.getN());
//				}
//			}
				
					
			
					List<CisternaSerije> zaBrisanje = new ArrayList<CisternaSerije>();
					
				for(CisternaSerije cis: s.getListaCisterniSerije()) {
					System.out.println("Status cisterne: " + cis.getStatus());
					if(cis.getStatus()==Status.OBRISANO) {
						zaBrisanje.add(cis);
					}
				}
				
				for(CisternaSerije  c : zaBrisanje) {
					c.getSerija().getListaCisterniSerije().remove(c);
				}
				
				session.save(s);
				tr.commit();
				//s.getListaCisterniSerije().remove(0);
				
			return true;
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return false;
		}

		public List<Serija> vratiListuSerija() {
			
//			
//			String sql = "select * from serija";
//			Statement state;
//			try {
//				state = conn.createStatement();
//				ResultSet rs = state.executeQuery(sql);
//				return rs;
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return null;	
			
			
			Criteria criteria = session.createCriteria(Serija.class);
			List<Serija> serije = criteria.list();
			//
			return serije;
			
		}

		public Serija pronadjiSeriju(String lot) {

//			try {
//			
//			Serija s = new Serija();
//			String sql = "select * from serija where LOT=?";
//			PreparedStatement prep = conn.prepareStatement(sql);
//			prep.setString(1, lot);
//			Statement state = conn.createStatement();
//			System.out.println("Izvrseni upit: " + prep.toString());
//			ResultSet rs = prep.executeQuery();
//			
//			if(!rs.next()) {
//				System.out.println("Nema serije");
//				return null;
//			}else {
//				s.setLOT(lot);
//				
//				
//						sql = "select * from proizvodserije join artikl on proizvodserije.idartikla=artikl.idartikla "
//								+ "join sopstveniproizvod on artikl.idartikla=sopstveniproizvod.idartikla join "
//								+ "nalepnica on proizvodserije.idnalepnice=nalepnica.idnalepnice where proizvodserije.lot='" + lot +"'";
//						rs = state.executeQuery(sql);
//						
//						while(rs.next()) {
//							ProizvodSerije ps = new ProizvodSerije();
//							ps.setKolicina(rs.getInt("proizvodserije.kolicina"));
//							SopstveniProizvod sp = new SopstveniProizvod(rs.getInt("proizvodserije.idartikla"));
//							sp.setNazivArtikla(rs.getString("artikl.nazivartikla"));
//							sp.setKolicinaMleka(rs.getInt("sopstveniproizvod.kolicinamleka"));
//							sp.setZapreminaUMl(rs.getInt("sopstveniproizvod.zapreminauml"));
//							sp.setOpis(rs.getString("sopstveniproizvod.opis"));
//							ps.setSp(sp);
//							Nalepnica n = new Nalepnica();
//							n.setIDNalepnice(rs.getInt("nalepnica.idnalepnice"));
//							n.setOpis(rs.getString("nalepnica.opisnalepnice"));
//							ps.setN(n);
//							ps.setStatus(Status.NEIZMENJENO);
//							s.dodajUKolekciju(ps);
//						}
//						
////						for(PlaniraniProizvodSerije plp : ps.getPlaniraniProizvodiSerije()) {
////							sql = "select * from sastojak join artikl on sastojak.idartiklasastojka=artikl.idartikla where sastojak.idartiklaproizvoda="+plp.getSp().getIDArtikla();
////							 rs = state.executeQuery(sql);
////							while(rs.next()) {
////								Sastojak s = new Sastojak();
////								Artikl a = new Artikl(rs.getInt("artikl.idartikla"));
////								a.setNazivArtikla(rs.getString("artikl.nazivartikla"));
////								s.setArtikl(a);
////								s.setKolicina(rs.getInt("sastojak.kolicina"));
////								plp.getSp().getSastojci().add(s);
////							}
////						}
//						
//						sql = "select * from artiklserije join artikl on artiklserije.idartikla=artikl.idartikla where artiklserije.lot='" + lot +"'";
//						rs = state.executeQuery(sql);
//						
//						while(rs.next()) {
//							ArtiklSerije as = new ArtiklSerije();
//							as.setKolicina(rs.getInt("artiklserije.kolicina"));
//							Artikl a = new Artikl(rs.getInt("artikl.idartikla"));
//							a.setNazivArtikla(rs.getString("artikl.nazivArtikla"));
//							as.setArtikl(a);
//							as.setStatus(Status.NEIZMENJENO);
//							s.dodajUKolekciju(as);
//						}
//						
//						sql = "select * from cisternaserije join serija on cisternaserije.lot=serija.lot join cisterna on cisternaserije.idcisterne=cisterna.idcisterne where cisternaserije.lot='" + lot+"'";
//						rs = state.executeQuery(sql);
//						while(rs.next()) {
//							Cisterna cis = new Cisterna();
//							cis.setDatum(rs.getDate("datum"));
//							cis.setIDCisterne(rs.getInt("idCisterne"));
//							cis.setIspravna(rs.getBoolean("ispravna"));
//							cis.setUkupnoOtkupljenoMleko(rs.getInt("ukupnoOtkupljenoMleko"));
//							CisternaSerije cs = new CisternaSerije();
//							cs.setCisterna(cis);
//							cs.setKolicina(rs.getInt("kolicina"));
//							cs.setStatus(Status.NEIZMENJENO);
//							s.getListaCisterniSerije().add(cs);
//						}
//						s.setStatus(Status.NEIZMENJENO);
//						
//						return s;
//						
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//			
//		}
//			return null;
			
			
			Serija s = (Serija) session.get(Serija.class, lot);
	//		
			return s;
			
		}

		public Artikl pronadjiArtikl(int idArtikla) {
			Artikl a = (Artikl) session.get(Artikl.class, idArtikla);
			return a;
		}
	
}
