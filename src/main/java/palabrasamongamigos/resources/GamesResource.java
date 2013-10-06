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

@Path("/games/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class GamesResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public GamesResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @POST
    public GameModel newGame(@QueryParam("name") Optional<String> name) {
        MongoResource mongo = MongoResource.INSTANCE;
        DBCollection coll =  mongo.getCollection();
        GameModel newGame = new GameModel(counter.incrementAndGet());
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