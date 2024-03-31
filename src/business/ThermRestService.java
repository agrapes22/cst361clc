package business;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import data.ThermDataService;
import beans.Thermometer;

@Path("/")
public class ThermRestService {

	@GET()
	@Produces("text/plain")
	public String sayHello() {
	    return "Hello World!";
	}
	
	@GET
	@Path("/getTherm/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Thermometer getTherm(@PathParam("id") int id) {
		ThermDataService td = new ThermDataService();
		Thermometer t = new Thermometer();
		t.setId(id);
		t = td.get(t);
		return t;
	}
}
