package jp.com.sskprj.dw.three;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import jp.com.sskprj.dw.common.security.CsrfFilter;
import jp.com.sskprj.dw.common.service.UserSessionPoolService;
import jp.com.sskprj.dw.common.session.ExampleAuthenticator;
import jp.com.sskprj.dw.common.session.ExampleAuthorizer;
import jp.com.sskprj.dw.common.session.UserSessionBean;
import jp.com.sskprj.dw.three.config.ThreeConfiguration;
import jp.com.sskprj.dw.three.config.ViewConfiguration;
import jp.com.sskprj.dw.three.health.TemplateHealthCheck;
import jp.com.sskprj.dw.three.pages.DummyPagesResource;
import jp.com.sskprj.dw.three.pages.ReserveResource;
import jp.com.sskprj.dw.three.service.ReserveService;
import org.eclipse.jetty.server.session.SessionHandler;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class ThreeApplication extends Application<ThreeConfiguration> {

    public static final String ASSETS_CSS = "/assets/css";
    public static final String ASSETS_JS = "/assets/js";
    public static final String ASSETS_IMAGE = "/assets/image";

    public static void main(String[] args) throws Exception {
        new ThreeApplication().run(args);
    }

    @Override
    public void run(ThreeConfiguration configuration, Environment environment) throws Exception {

        final ReserveResource reserveResource = new ReserveResource(new ReserveService());
        final DummyPagesResource dummyPagesResource = new DummyPagesResource();

        environment.jersey().register(reserveResource);
        environment.jersey().register(dummyPagesResource);
        environment.jersey().register(ViewConfiguration.createViewExceptionComponent());
        environment.servlets().setSessionHandler(new SessionHandler());
        environment.servlets().addFilter("csrfFilter", new CsrfFilter(new UserSessionPoolService()))
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/reserve/*");
        //        addBasicCredential(environment);
        // ヘルスチェック機能
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("webapp/template", healthCheck);

    }

    private void addBasicCredential(Environment environment) {
        BasicCredentialAuthFilter basicCredentialAuthFilter = new BasicCredentialAuthFilter.Builder<UserSessionBean>().setAuthenticator(
                new ExampleAuthenticator())
                .setAuthorizer(new ExampleAuthorizer())
                .setRealm("AAAAZZZ")
                .buildAuthFilter();
        environment.jersey().register(new AuthDynamicFeature(basicCredentialAuthFilter));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(UserSessionBean.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
    }

    @Override
    public void initialize(Bootstrap<ThreeConfiguration> bootstrap) {

        bootstrap.addBundle(new ViewBundle<>());
        // クライアントテスト用
        bootstrap.addBundle(new AssetsBundle(ASSETS_CSS, ASSETS_CSS, null, "css"));
        bootstrap.addBundle(new AssetsBundle(ASSETS_JS, ASSETS_JS, null, "js"));
        bootstrap.addBundle(new AssetsBundle(ASSETS_IMAGE, ASSETS_IMAGE, null, "image"));

        bootstrap.addBundle(new MigrationsBundle<>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ThreeConfiguration configuration) {
                return configuration.getDatasource();
            }
        });
        //        bootstrap.addBundle(hibernate);
    }
}
