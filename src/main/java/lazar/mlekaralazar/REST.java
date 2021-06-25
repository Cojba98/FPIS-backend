package lazar.mlekaralazar;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonProperty;

import dbbroker.DBBroker;
import helpClasses.HelpArtiklSerije;
import helpClasses.HelpProizvodSerije;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import kontroler.KontrolerPlanaSerije;
import kontroler.KontrolerSerije;
import model.ArtiklSerije;
import model.ArtiklZaSeriju;
import model.Cisterna;
import model.CisternaSerije;
import model.Nalepnica;
import model.PlanProizvodnjeSerije;
import model.PlaniraniProizvodSerije;
import model.ProizvodSerije;
import model.Serija;
import model.SopstveniProizvod;

@Path("")
public class REST {
	
	private KontrolerSerije kontrolerSerije;
	private KontrolerPlanaSerije kontrolerPlana;
	
	public REST() {
		kontrolerSerije = KontrolerSerije.vratiInstancu();
		kontrolerPlana = KontrolerPlanaSerije.vratiInstancu();
	}
	
	@GET
	@Path("plan/pokreniUnos")
	public void kontrolerPlan() {
		KontrolerPlanaSerije.kreirajNoviKontroler();	
	}
	
	@GET
	@Path("serija/pokreniUnos")
	public void kontrolerSerija() {
		KontrolerSerije.kreirajNoviKontroler();	
	}
	
//	@GET
//	@Path("planoviProizvodnjeSerije")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<PlanProizvodnjeSerije> vratiListuPlanovaProizvodnjeSerije() throws SQLException{
//		return kontroler.vratiListuPlanovaProizvodnjeSerije();
//	}
	
	@GET
	@Path("planoviProizvodnjeSerije")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PlanProizvodnjeSerije> vratiListuPlanovaProizvodnjeSerije() throws SQLException{
		return kontrolerSerije.vratiListuPlanovaProizvodnjeSerije();
		
	}
	
	@GET
	@Path("serije")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Serija> vratiListuSerija() throws SQLException{
		return kontrolerSerije.vratiListuSerija();
		
	}
	
	@GET
	@Path("planoviProizvodnjeSerije/proiz")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PlaniraniProizvodSerije> vratiListuPlaniranihProizvodaSerije() {
		return kontrolerSerije.vratiListuPlaniranihProizvodaSerije();
	}
	
//	@GET
//	@Path("serija/artikliSerije")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<ArtiklZaSeriju> vratiListuArtikalaZaSeriju() {
//		return kontrolerSerije.vratiListuArtikalaZaSeriju();
//	}
	
	@GET
	@Path("serija/proizvodiSerije")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProizvodSerije> vratiListuProizvodaSerije() {
		return kontrolerSerije.vratiListuProizvodaSerije();
	}
	
	@GET
	@Path("serija/artikliSerije")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ArtiklSerije> vratiListuArtikalaSerije() {
		return kontrolerSerije.vratiListuArtikalaSerije();
	}
	
	@GET
	@Path("planoviProizvodnjeSerije/plan/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PlanProizvodnjeSerije izborPlanaProizvodnjeSerije(@PathParam("id") int idPlanaSerije) {
		return kontrolerSerije.izborPlanaProizvodnjeSerije(idPlanaSerije);
	}
	
	@GET
	@Path("cisterne")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cisterna> vratiListuCisterni(){
		try {
			return kontrolerSerije.vratiListuCisterni();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	@Path("serija/proizvod")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void unosKolicineProizvodaSerije(HelpProizvodSerije hps) {
		System.out.println(hps.IDArtikla + " " + hps.IDNalepnice + " " + hps.kolicina); 
		kontrolerSerije.unosKolicineProizvodaSerije(hps.IDArtikla, hps.IDNalepnice, hps.kolicina);
		
	}
	
	@DELETE
	@Path("serija/proizvod")
	@Produces(MediaType.APPLICATION_JSON)
	public void ukloniProizvodSerije(@QueryParam("IDArtikla") int IDArtikla, @QueryParam("IDNalepnice") int IDNalepnice) {
		System.out.println(IDArtikla + " " + IDNalepnice + " "); 
		kontrolerSerije.ukloniProizvodSerije(IDArtikla, IDNalepnice);
		
	}
	
	@POST
	@Path("serija/artikl")
	public void unosKolicineArtiklaSerije(HelpArtiklSerije has) {
		System.out.println("Serija artikli"); 
		kontrolerSerije.unosKolicineArtiklaSerije(has.IDArtikla, has.kolicina);
	}
	
	@DELETE
	@Path("serija/artikl")
	@Produces(MediaType.APPLICATION_JSON)
	public void ukloniProizvodSerije(@QueryParam("IDArtikla") int IDArtikla) {
		System.out.println(IDArtikla); 
		kontrolerSerije.ukloniArtiklSerije(IDArtikla);
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("serija/cisterne")
	public List<CisternaSerije> vratiListuCisterniSerije(){
		return kontrolerSerije.vratiListuCisterniSerije();
	}
	
	@GET
	@Path("serija/dodajCisternu")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CisternaSerije> dodajCisternu() {
		return kontrolerSerije.dodajNovuCisternu();
	}
	
	@GET
	@Path("serija/postaviCisternu/{id}")
	public void postaviCisternu(@PathParam("id") int idCisterne) throws SQLException {
		kontrolerSerije.postaviCisternu(idCisterne);
	}
	
	@GET
	@Path("postaviKolicinuZaCisternu/{kolicina}")
	public void postaviKolicinuZaCisternuCisternu(@PathParam("kolicina") int kolicina) {
		kontrolerSerije.postaviKolicinuZaCisternu(kolicina);
	}
	
	
	@GET
	@Path("serija/ubaciCisternuUKolekciju")
	public void ubaciCisternuUKolekciju() {
		kontrolerSerije.ubaciCisternu();
	}
	
	@DELETE
	@Path("serija/izbrisiCisternu/{id}")
	public void obrisiCisternuSerije(@PathParam("id") int idCisterne) {
		kontrolerSerije.obrisiCisternuSerije(idCisterne);
	}
	
	@GET
	@Path("sacuvajSeriju")
	@Produces(MediaType.TEXT_PLAIN)
	public String sacuvajSeriju(@QueryParam("LOT") String LOT) {
		return kontrolerSerije.sacuvajSeriju(LOT)+"";
	}
	
	@GET
	@Path("serija/izborSerije/{lot}")
	@Produces(MediaType.APPLICATION_JSON)
	public Serija izborSerije(@PathParam("lot") String lot) {
		return kontrolerSerije.postaviSeriju(lot);
	}
	
	@GET
	@Path("plan/noviID")
	@Produces(MediaType.TEXT_PLAIN)
	public String vratiNoviIDPlana() {
		return kontrolerPlana.vratiNoviIDPlana()+"";
	}
	
	@GET
	@Path("plan/noviProizvod")
	public void kreirajNoviProizvod() {
		kontrolerPlana.kreirajNoviPlaniraniProizvod();
	}
	
	@GET
	@Path("plan/listaProizvoda")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SopstveniProizvod> vratiListuProizvoda() {
		return kontrolerPlana.vratiListuProizvoda();
	}
	
	@GET
	@Path("plan/listaNalepnica")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Nalepnica> vratiListuNalepnica() {
		return kontrolerPlana.vratiListuNalepnica();
	}
	
	@GET
	@Path("plan/postaviProizvod/{IDArtikla}")
	public void postaviProizvod(@PathParam("IDArtikla") int idArtikla) {
		kontrolerPlana.postaviProizvod(idArtikla);
	}
	
	@GET
	@Path("plan/postaviNalepnicu/{IDNalepnice}")
	public void postaviNalepnicu(@PathParam("IDNalepnice") int idNalepnice) {
		System.out.println("Postavljena nalenica: " + idNalepnice);
		kontrolerPlana.postaviNalepnicu(idNalepnice);
	}
	
	@GET
	@Path("plan/postaviKolicinu/{kolicina}")
	public void postaviKolicinu(@PathParam("kolicina") int kolicina) {
		System.out.println("Postavljena kolicina: " + kolicina);
		kontrolerPlana.postaviKolicinu(kolicina);
	}
	
	@GET
	@Path("plan/dodajPlaniraniProizvod")
	public void dodajPlaniraniProizvodSerije() {
		kontrolerPlana.dodajPlaniraniProizvodSerije();
	}
	
	@GET
	@Path("plan/ukloniPlaniraniProizvod")
	public void ukloniPlaniraniProizvodSerije(@QueryParam("IDArtikla") int idArtikla, @QueryParam("IDNalepnice") int idNalepnice) {
		kontrolerPlana.ukloniPlaniraniProizvodSerije(idArtikla, idNalepnice);
	}
	
	@GET
	@Path("plan/listaPlaniranihProizvoda")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PlaniraniProizvodSerije> listaPlaniranihProizvodaSerije(){
		return kontrolerPlana.vratiListuPlaniranihProizvoda();
	}
	
	@GET
	@Path("plan/listaArtikalaZaSeriju")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ArtiklZaSeriju> listaArtikalaZaSeriju(){
		return kontrolerPlana.vratiListuArtikalaZaSeriju();
	}
	
	@GET
	@Path("plan/potrebnoMleko")
	@Produces(MediaType.TEXT_PLAIN)
	public String vratiPotrebnoMlekoZaSeriju() {
		return kontrolerPlana.izracunajPotrebnuKolicinuMleka() +"";
	}
	
	@GET
	@Path("plan/zapamtiPlan")
	@Produces(MediaType.TEXT_PLAIN)
	public String zapamtiPlan() {
		return kontrolerPlana.zapamtiPlan();
	}
	
	@GET
	@Path("plan/izborPlana/{id}")
	public void izborPlana(@PathParam("id") int id) {
		kontrolerPlana.izborPlana(id);
	}
	
	@POST
	@Path("plan/proizvod")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void unosKolicineProizvodaSerijePlaniranogProizvoda(HelpProizvodSerije hps) {
		System.out.println(hps.IDArtikla + " " + hps.IDNalepnice + " " + hps.kolicina); 
		kontrolerPlana.unosKolicineProizvodaSerije(hps.IDArtikla, hps.IDNalepnice, hps.kolicina);
		
	}
	
	@GET
	@Path("plan/izracunajPotrebnoMleko")
	@Produces(MediaType.TEXT_PLAIN)
	public String izracunajPotrebnuKolicinuMleka() {
		return kontrolerPlana.izracunajPotrebnuKolicinuMleka() + "";
	}
	
	@GET
	@Path("plan/pripremiZaIzmenu")
	public void pripremiProizvodZaIzmenu(@QueryParam("IDArtikla") int idArtikla, @QueryParam("IDNalepnice") int idNalepnice) {
		
		kontrolerPlana.pripremiProizvodZaIzmenu(idArtikla, idNalepnice);
	}
	
//	@GET
//	@Path("plan/sacuvajIzmene")
//	public void sacuvajIzmene() {
//		kontrolerPlana.sacuvajIzmene();
//	}
}
