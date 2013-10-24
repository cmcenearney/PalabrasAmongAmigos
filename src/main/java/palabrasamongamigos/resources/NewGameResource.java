package palabrasamongamigos.resources;

import palabrasamongamigos.DatabaseAccessor;
import palabrasamongamigos.core.Email;
import palabrasamongamigos.core.GameModel;
import palabrasamongamigos.core.Player;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/game")
@Produces(MediaType.APPLICATION_JSON)
public class NewGameResource {

    private final DatabaseAccessor db = DatabaseAccessor.INSTANCE;

    public NewGameResource() {}

    @POST
    public GameModel newGame(@FormParam("opponentEmail") String opponentEmail, @FormParam("userEmail") String userEmail) {
        Player opponent = new Player(opponentEmail);
        Player user = new Player(userEmail);
        GameModel newGame = new GameModel();
        newGame.addPlayer(user);
        newGame.addPlayer(opponent);
        String messageToOpponent = "";
        Email emailer = new Email(opponentEmail, messageToOpponent);
        emailer.sendEmail();
        db.saveGame(newGame);
        return newGame;
    }

}
