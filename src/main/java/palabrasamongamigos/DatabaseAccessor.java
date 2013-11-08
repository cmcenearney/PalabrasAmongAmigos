package palabrasamongamigos;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.util.JSON;
import palabrasamongamigos.core.GameModel;
import palabrasamongamigos.core.PalabrasModel;
import palabrasamongamigos.core.SessionModel;

import java.io.IOException;
import java.net.UnknownHostException;

public enum DatabaseAccessor implements DatabaseAccess{

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

    public GameModel getGameById(long id){
        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject query = new BasicDBObject("id", id);
        BasicDBObject fields = new BasicDBObject("_id",false);
        String dbJson = collection.findOne(query,fields).toString();
        GameModel game = new GameModel();
        //System.out.println(id);
        //System.out.println(game.getId());
        try {
            game = mapper.readValue(dbJson, GameModel.class);
            //System.out.println(game.getId());
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


    public SessionModel getSessionById(long id){
        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject query = new BasicDBObject("id", id);
        BasicDBObject fields = new BasicDBObject("_id",false);
        String dbJson = collection.findOne(query,fields).toString();
        SessionModel session = new SessionModel();
        //System.out.println(id);
        //System.out.println(game.getId());
        try {
            session = mapper.readValue(dbJson, SessionModel.class);
            //System.out.println(game.getId());
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
        return session;
    }

    //TODO - better to setup mongo to enforce uniqueness for 'id' ?
    public void save(PalabrasModel model){
        ObjectMapper mapper = new ObjectMapper();
        if ( isInDb(model) ){
            delete(model);
        }
        String json = "";
        try {
            json = mapper.writeValueAsString(model);
        } catch (Exception e){
            System.out.println(e);
        }
        DBObject modelDoc = (DBObject) JSON.parse(json);
        collection.insert(modelDoc);
    }

    public void delete(PalabrasModel model){
        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject query = new BasicDBObject("id", model.getId());
        BasicDBObject fields = new BasicDBObject("_id",false);
        DBObject target = collection.findOne(query,fields);
        collection.remove(target);

    }

    public boolean isInDb(PalabrasModel model) {
        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject query = new BasicDBObject("id", model.getId());
        BasicDBObject fields = new BasicDBObject("_id",false);
        if ( collection.find(query,fields).size() > 0 ){
            return true;
        }
        return false;
    }
}
