package jp.com.sskprj.dw.three;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import jp.com.sskprj.dw.common.provider.OriginalProvider;
import jp.com.sskprj.dw.common.service.UserSessionPoolService;
import jp.com.sskprj.dw.common.session.OAuth2Authenticator;
import jp.com.sskprj.dw.common.session.OAuth2Authorizer;
import jp.com.sskprj.dw.common.session.UserSessionBean;
import jp.com.sskprj.dw.three.config.ThreeConfiguration;
import jp.com.sskprj.dw.three.config.ViewConfiguration;
import jp.com.sskprj.dw.three.health.TemplateHealthCheck;
import jp.com.sskprj.dw.three.pages.DummyPagesResource;
import jp.com.sskprj.dw.three.pages.ReserveResource;
import jp.com.sskprj.dw.three.service.ReserveService;
import org.eclipse.jetty.server.session.SessionHandler;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class ThreeApplication extends Application<ThreeConfiguration> {

    private static final String ASSETS_CSS = "/assets/css";
    private static final String ASSETS_JS = "/assets/js";
    private static final String ASSETS_IMAGE = "/assets/image";
    private static final String[] CSRF_TARGET_URL_PATTERNS = { "/reserve/*" };

    public static void main(String[] args) throws Exception {
        new ThreeApplication().run(args);
    }

    @Override
    public void run(ThreeConfiguration configuration, Environment environment) throws Exception {

        final ReserveResource reserveResource = new ReserveResource();
        final DummyPagesResource dummyPagesResource = new DummyPagesResource();

        // リソース登録
        environment.jersey().register(ReserveResource.class);
        environment.jersey().register(DummyPagesResource.class);
        environment.jersey().register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(UserSessionPoolService.class).to(UserSessionPoolService.class);
                bind(ReserveService.class).to(ReserveService.class);
            }
        });

        // view関連
        environment.jersey().register(ViewConfiguration.createViewExceptionComponent());

        // セッション管理
        environment.servlets().setSessionHandler(new SessionHandler());
        //        environment.servlets().addFilter("csrfFilter", CsrfFilter.class)
        //                        .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, CSRF_TARGET_URL_PATTERNS);
        // ヘルスチェック機能
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("webapp/template", healthCheck);

    }

    private void addCsrfEnv(Environment environment) {
        environment.jersey().register(new OriginalProvider());
    }

    private void addOAuth2Env(Environment environment) {
        environment.jersey()
                .register(new AuthDynamicFeature(
                        new OAuthCredentialAuthFilter.Builder<UserSessionBean>().setAuthenticator(
                                new OAuth2Authenticator())
                                .setAuthorizer(new OAuth2Authorizer())
                                .setPrefix("Bearer")
                                .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        //If you want to use @Auth to inject a custom Principal type into your resource
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(UserSessionBean.class));
    }

    @Override
    public void initialize(Bootstrap<ThreeConfiguration> bootstrap) {

        bootstrap.addBundle(new ViewBundle<>());

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
