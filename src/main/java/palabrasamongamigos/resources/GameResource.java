package palabrasamongamigos.resources;

import com.yammer.dropwizard.jersey.params.LongParam;
import palabrasamongamigos.DatabaseAccessor;
import palabrasamongamigos.core.GameModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/game/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class GameResource {

    private final DatabaseAccessor db = DatabaseAccessor.INSTANCE;

    public GameResource() {}

    @GET
    public GameModel getGame(@PathParam("id") LongParam idParam) {
        final long id = idParam.get();
        return db.getById(id);
    }
}

