package palabrasamongamigos;


import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Properties;

public enum MongoResource {
    INSTANCE;
    private MongoClient mongoClient;
    private DB db;
    DBCollection collection;

    private MongoResource() {
        try {
            mongoClient = new MongoClient( "localhost" , 27017 );
            db = mongoClient.getDB( "palabras" );
            collection = db.getCollection("games");
        } catch (UnknownHostException e) {
            //handle the exception
        }
    }

    public DBCollection getCollection(){
        return collection;
    }

}