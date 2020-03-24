import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class TestResource {

    @GET
    public String get_name(){
        System.out.println("-============================================");
        return "Hello Dropwizard!!";
    }
}
