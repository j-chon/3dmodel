package jp.com.sskprj.dw.three;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import jp.com.sskprj.dw.three.config.ThreeConfiguration;
import jp.com.sskprj.dw.three.config.ViewConfiguration;
import jp.com.sskprj.dw.three.health.TemplateHealthCheck;
import jp.com.sskprj.dw.three.pages.DummyPagesResource;
import jp.com.sskprj.dw.three.pages.ReserveResource;

public class ThreeApplication extends Application<ThreeConfiguration> {

    public static void main(String[] args) throws Exception {
        new ThreeApplication().run(args);
    }

    @Override
    public void run(ThreeConfiguration configuration, Environment environment) throws Exception {

//        final ReserveCalendarDAO reserveCalendarDAO = new ReserveCalendarDAO(hibernate);
        //        final ReserveResource reserveResource = new ReserveResource(reserveCalendarDAO);

        final ReserveResource reserveResource = new ReserveResource();
        final DummyPagesResource dummyPagesResource = new DummyPagesResource();
        environment.jersey().register(reserveResource);
        environment.jersey().register(dummyPagesResource);
        environment.jersey().register(ViewConfiguration.createViewExceptionComponent());

        // ヘルスチェック機能
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("webapp/template", healthCheck);

    }


    @Override
    public void initialize(Bootstrap<ThreeConfiguration> bootstrap) {

        bootstrap.addBundle(new ViewBundle<>());
        // クライアントテスト用
        //        bootstrap.addBundle(new MultiPartBundle());

        bootstrap.addBundle(new AssetsBundle("/assets/css", "/assets/css", null, "css"));
        bootstrap.addBundle(new AssetsBundle("/assets/js", "/assets/js", null, "js"));
        bootstrap.addBundle(new AssetsBundle("/assets/css", "/assets/image", null, "image"));

        bootstrap.addBundle(new MigrationsBundle<>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ThreeConfiguration configuration) {
                return configuration.getDatasource();
            }
        });
//        bootstrap.addBundle(hibernate);
    }
}
