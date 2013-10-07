package palabrasamongamigos;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import palabrasamongamigos.resources.*;

import java.net.UnknownHostException;
import java.util.Arrays;

public class PalabrasService extends Service<PalabrasConfiguration> {


    public static void main(String[] args) throws Exception {
        new PalabrasService().run(args);
    }

    @Override
    public void initialize(Bootstrap<PalabrasConfiguration> bootstrap) {
        bootstrap.setName("palabras-among-amigos");
        bootstrap.addBundle(new AssetsBundle("/web/backbone/", "/"));
    }

    @Override
    public void run(PalabrasConfiguration configuration, Environment environment) {
        final String template = configuration.getTemplate();
        final String defaultName = configuration.getDefaultName();
        environment.addResource(new NewGameResource(template, defaultName));
        environment.addResource(new GamesResource());
    }

}