package palabrasamongamigos.resources;

import com.yammer.dropwizard.jersey.params.LongParam;
import palabrasamongamigos.DatabaseAccessor;
import palabrasamongamigos.core.GameModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/game/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class GameResource {

    private final DatabaseAccessor db = DatabaseAccessor.INSTANCE;

    public GameResource() {}

    @GET
    public GameModel getGame(@PathParam("id") LongParam idParam) {
        final long id = idParam.get();
        return db.getGameById(id);
    }

    @POST
    public GameModel getPlayerViewGame(@PathParam("id") LongParam idParam, @FormParam("userEmail") String userEmail) {
        final long id = idParam.get();
        GameModel game = db.getGameById(id);
        //TODO: filter out tiles of all players but the requesting player
        return game;
    }
}

