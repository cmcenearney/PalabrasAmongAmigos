package palabrasamongamigos;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.util.JSON;
import palabrasamongamigos.core.GameModel;

import java.io.IOException;
import java.net.UnknownHostException;

public enum DatabaseAccessor {

    INSTANCE;
    private MongoClient mongoClient;
    private DB db;
    DBCollection collection;

    private DatabaseAccessor() {
        //MongoClientURI mongoUri  = new MongoClientURI ( (System.getenv("MONGOLAB_URI") != null) ? System.getenv("MONGOLAB_URI") : "mongodb://localhost:27017");
        //MongoClient mongoClient;
        try {
            if (System.getenv("MONGOLAB_URI") != null)  {

                MongoClientURI mongoUri  = new MongoClientURI (System.getenv("MONGOLAB_URI"));
                mongoClient = new MongoClient(mongoUri);
                String dbName = mongoUri.getDatabase();
                db = mongoClient.getDB( dbName );

            }  else {
                mongoClient = new MongoClient( "localhost" , 27017 );
                db = mongoClient.getDB( "palabras" );
            }
            //db = mongoClient.getDB( "palabras" );
            collection = db.getCollection("games");
        } catch (UnknownHostException e) {
            System.out.println(e);
        }
    }

    public DBCollection getCollection(){
        return collection;
    }

    public GameModel getById(long id){
        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject query = new BasicDBObject("id", id);
        BasicDBObject fields = new BasicDBObject("_id",false);
        String dbJson = collection.findOne(query,fields).toString();
        GameModel game = new GameModel();
        System.out.println(id);
        System.out.println(game.getId());
        try {
            game = mapper.readValue(dbJson, GameModel.class);
            System.out.println(game.getId());
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
        return game;
    }

    public void saveGame(GameModel game){
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(game);
        } catch (Exception e){
            System.out.println(e);
        }
        DBObject gameDoc = (DBObject) JSON.parse(json);
        collection.insert(gameDoc);
    }
}
