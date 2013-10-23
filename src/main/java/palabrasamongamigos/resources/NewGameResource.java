package palabrasamongamigos.resources;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.mongodb.*;
import com.mongodb.util.JSON;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import palabrasamongamigos.DatabaseAccessor;
import palabrasamongamigos.MongoResource;
import palabrasamongamigos.core.Email;
import palabrasamongamigos.core.Game;
import palabrasamongamigos.core.GameModel;
import palabrasamongamigos.core.Player;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicLong;

@Path("/game")
@Produces(MediaType.APPLICATION_JSON)
public class NewGameResource {

    private final DatabaseAccessor db = DatabaseAccessor.INSTANCE;

    public NewGameResource() {}

    @POST
    public GameModel newGame(@FormParam("email") String emailAddress) {
        Player opponent = new Player(emailAddress);
        GameModel newGame = new GameModel(opponent);
        String messageToOpponent = "";
        Email emailer = new Email(emailAddress, messageToOpponent);
        emailer.sendEmail();
        db.saveGame(newGame);
        return newGame;
    }

}
