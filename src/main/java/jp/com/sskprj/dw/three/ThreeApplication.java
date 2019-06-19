package jp.com.sskprj.dw.three;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import jp.com.sskprj.dw.three.api.PersonResource;
import jp.com.sskprj.dw.three.api.ThreeMainResource;
import jp.com.sskprj.dw.three.dao.PersonDAO;
import jp.com.sskprj.dw.three.health.TemplateHealthCheck;
import jp.com.sskprj.dw.three.view.Person;

public class ThreeApplication extends Application<ThreeConfiguration> {

    public static void main(String[] args) throws Exception {
        new ThreeApplication().run(args);
    }

    private final HibernateBundle<ThreeConfiguration> hibernate = new HibernateBundle<>(Person.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(ThreeConfiguration configuration) {
            return configuration.getDatasource();
        }
    };

    @Override
    public void run(ThreeConfiguration configuration, Environment environment) throws Exception {

        final PersonDAO personDAO = new PersonDAO(hibernate.getSessionFactory());

        final ThreeMainResource threeMainResource = new ThreeMainResource(configuration.getTemplate(),
                configuration.getDefaultName());
        final PersonResource personResource = new PersonResource(personDAO);

        environment.jersey().register(threeMainResource);
        environment.jersey().register(personResource);

        // ヘルスチェック機能
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("webapp/template", healthCheck);
        environment.jersey().register(threeMainResource);
    }

    @Override
    public void initialize(Bootstrap<ThreeConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new MigrationsBundle<>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ThreeConfiguration configuration) {
                return configuration.getDatasource();
            }
        });
        bootstrap.addBundle(hibernate);
    }
}
