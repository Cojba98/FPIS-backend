
package lazar.mlekaralazar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Artikl;

/** Example resource class hosted at the URI path "/myresource"
 */
@Path("/myresource")
public class MyResource {
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET 
    @Produces(MediaType.APPLICATION_XML)
    public Artikl getIt() {
    	System.out.println("Poziva se...");
        Artikl a = new Artikl(1);
        a.setNazivArtikla("Pavlaka");
        return a;
    }
}
