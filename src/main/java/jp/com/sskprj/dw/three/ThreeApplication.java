package jp.com.sskprj.dw.three;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import io.dropwizard.views.ViewRenderException;
import jp.com.sskprj.dw.three.api.PersonResource;
import jp.com.sskprj.dw.three.api.ThreeMainResource;
import jp.com.sskprj.dw.three.config.ThreeConfiguration;
import jp.com.sskprj.dw.three.dao.PersonDAO;
import jp.com.sskprj.dw.three.dao.ReserveCalendarDAO;
import jp.com.sskprj.dw.three.entity.db.ReserveSchedule;
import jp.com.sskprj.dw.three.health.TemplateHealthCheck;
import jp.com.sskprj.dw.three.pages.ReserveCalendarResource;
import jp.com.sskprj.dw.three.view.Person;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.glassfish.jersey.spi.ExtendedExceptionMapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ThreeApplication extends Application<ThreeConfiguration> {

    public static void main(String[] args) throws Exception {
        new ThreeApplication().run(args);
    }
//    private final HibernateBundle<ThreeConfiguration> hibernate = new HibernateBundle<>(
//            ReserveSchedule.class,Person.class
//    ) {
//        @Override
//        public DataSourceFactory getDataSourceFactory(ThreeConfiguration configuration) {
//            return configuration.getDatasource();
//        }
//    };

    @Override
    public void run(ThreeConfiguration configuration, Environment environment) throws Exception {

//        final ReserveCalendarDAO reserveCalendarDAO = new ReserveCalendarDAO(hibernate);
//        final ReserveCalendarResource reserveCalendarResource = new ReserveCalendarResource(reserveCalendarDAO);

        final ReserveCalendarResource reserveCalendarResource = new ReserveCalendarResource();
        environment.jersey().register(reserveCalendarResource);

        environment.jersey().register(new ExtendedExceptionMapper<WebApplicationException>() {
            @Override
            public Response toResponse(WebApplicationException exception) {
                exception.printStackTrace();
                return exception.getResponse();
            }

            @Override
            public boolean isMappable(WebApplicationException exception) {
                return ExceptionUtils.indexOfThrowable(exception, ViewRenderException.class) != -1;
            }
        });

        // ヘルスチェック機能
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("webapp/template", healthCheck);



    }

    @Override
    public void initialize(Bootstrap<ThreeConfiguration> bootstrap) {

        bootstrap.addBundle(new ViewBundle<>());

        bootstrap.addBundle(new AssetsBundle("/assets/css", "/assets/css", null, "css"));
        bootstrap.addBundle(new AssetsBundle("/assets/js", "/assets/js", null, "js"));
        bootstrap.addBundle(new MigrationsBundle<>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ThreeConfiguration configuration) {
                return configuration.getDatasource();
            }
        });
//        bootstrap.addBundle(hibernate);
    }
}
