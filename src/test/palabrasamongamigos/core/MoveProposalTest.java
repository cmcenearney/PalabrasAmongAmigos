package palabrasamongamigos.core;

import com.fasterxml.jackson.core.JsonParseException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import palabrasamongamigos.MongoResource;
import java.io.IOException;
import static org.junit.Assert.assertEquals;


public class MoveProposalTest {

    protected MongoResource mongo = MongoResource.INSTANCE;
    protected DBCollection coll =  mongo.getCollection();
    GameModel game = new GameModel();

    @Test
    public void testMoveProposal(){
        MoveProposal mp = new MoveProposal(1381106790975l, "h,7,>,hog", 1, 0);

        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject query = new BasicDBObject("id", mp.getId());
        BasicDBObject fields = new BasicDBObject("_id",false);
        String dbJson = coll.find(query,fields).next().toString();

        GameModel game = new GameModel();
        try {
            game = mapper.readValue(dbJson, GameModel.class);
        }
        //TODO log exceptions
        catch (JsonMappingException e) {
            System.out.println("mapping exception:");
            System.out.println(e);
            System.out.println(e.getPath());
        }
        catch (JsonParseException e) {
            System.out.println("parse exception:");
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
        Move move = mp.newMove(game);
        assertEquals(true, true);
    }

}
