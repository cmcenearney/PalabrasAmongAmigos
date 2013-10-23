package palabrasamongamigos;

import com.bazaarvoice.dropwizard.assets.ConfiguredAssetsBundle;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import palabrasamongamigos.health.MongoHealthCheck;
import palabrasamongamigos.resources.*;

public class PalabrasService extends Service<PalabrasConfiguration> {

    private final MongoResource mongoResource = MongoResource.INSTANCE;

    public static void main(String[] args) throws Exception {
        new PalabrasService().run(args);
    }

    @Override
    public void initialize(Bootstrap<PalabrasConfiguration> bootstrap) {
        bootstrap.setName("palabras-among-amigos");
        // ConfiguredAssetsBundles are not compiled - use for dev
        // bootstrap.addBundle(new AssetsBundle("/web/backbone/", "/"));
        bootstrap.addBundle(new ConfiguredAssetsBundle("/web/backbone/", "/"));
    }

    @Override
    public void run(PalabrasConfiguration configuration, Environment environment) {
        final String template = configuration.getTemplate();
        final String defaultName = configuration.getDefaultName();
        environment.addResource(new GameResource());
        environment.addResource(new NewGameResource());
        environment.addResource(new MovesResource());
        environment.addHealthCheck(new MongoHealthCheck(mongoResource));
    }

}