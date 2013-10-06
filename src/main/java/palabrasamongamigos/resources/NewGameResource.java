package palabrasamongamigos.resources;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.google.common.base.Optional;
import com.mongodb.*;
import com.mongodb.util.JSON;
import com.yammer.metrics.annotation.Timed;
import org.codehaus.jackson.map.ObjectMapper;
import palabrasamongamigos.MongoResource;
import palabrasamongamigos.core.Game;
import palabrasamongamigos.core.GameModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicLong;

@Path("/new")
@Produces(MediaType.APPLICATION_JSON)
public class NewGameResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public NewGameResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @Path("/simple-test")
    @POST
    public Game newGameTest(@QueryParam("name") Optional<String> name) {
        MongoResource mongo = MongoResource.INSTANCE;
        DBCollection coll =  mongo.getCollection();
        Game newGame = new Game(counter.incrementAndGet(),String.format(template, name.or(defaultName)));
        BasicDBObject doc = new BasicDBObject("id", newGame.getId()).
                append("content", newGame.getContent());
        coll.insert(doc);
        return newGame;
    }

    @POST
    public GameModel newGame(@QueryParam("name") Optional<String> name) {
        MongoResource mongo = MongoResource.INSTANCE;
        DBCollection coll =  mongo.getCollection();
        //GameModel newGame = new GameModel(counter.incrementAndGet());
        GameModel newGame = new GameModel();
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(newGame);
        } catch (Exception e){
            System.out.println(e);
        }
        BasicDBObject doc = new BasicDBObject("id", newGame.getId());
        coll.insert(doc);
        DBObject dbObject = (DBObject) JSON.parse(json);
        coll.insert(dbObject);
        return newGame;
    }

}