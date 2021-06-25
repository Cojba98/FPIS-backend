package lazar.mlekaralazar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Artikl;

@Path("resurs")
public class Resurs {

	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Artikl vrati() {
		System.out.println("Radi...");
		Artikl a = new Artikl();
		a.setIDArtikla(1);
		a.setNazivArtikla("Pavlaka");
		return a;
	}
	
	
}
