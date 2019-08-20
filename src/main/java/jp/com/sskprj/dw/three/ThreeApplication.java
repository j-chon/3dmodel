package jp.com.sskprj.dw.three;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import jp.com.sskprj.dw.common.provider.OriginalProvider;
import jp.com.sskprj.dw.common.session.OAuth2Authenticator;
import jp.com.sskprj.dw.common.session.OAuth2Authorizer;
import jp.com.sskprj.dw.common.session.UserSessionBean;
import jp.com.sskprj.dw.three.api.LoginApiResource;
import jp.com.sskprj.dw.three.setup.ApplicationDefinitions;
import jp.com.sskprj.dw.three.setup.ThirdPartySettingUtils;
import jp.com.sskprj.dw.three.setup.config.ApplicationConfiguration;
import jp.com.sskprj.dw.three.setup.config.ViewConfiguration;
import jp.com.sskprj.dw.three.entity.db.OAuthStatus;
import jp.com.sskprj.dw.three.health.TemplateHealthCheck;
import jp.com.sskprj.dw.three.pages.DummyPagesResource;
import jp.com.sskprj.dw.three.pages.LoginResource;
import jp.com.sskprj.dw.three.pages.ReserveResource;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.session.SessionHandler;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
public class ThreeApplication extends Application<ApplicationConfiguration> {

    private static final String ASSETS_CSS = "/assets/css";
    private static final String ASSETS_JS = "/assets/js";
    private static final String ASSETS_IMAGE = "/assets/image";
    private static final String[] CSRF_TARGET_URL_PATTERNS = { "/reserve/*" };
    //    public static final String DATABASE_URL = "https://userauthentication01-4554b.firebaseio.com/";

    private HibernateBundle<ApplicationConfiguration> hibernate;

    private HibernateBundle<ApplicationConfiguration> createHibernateBundle() {
        return new HibernateBundle<ApplicationConfiguration>(OAuthStatus.class) {
            @Override
            public DataSourceFactory getDataSourceFactory(ApplicationConfiguration configuration) {
                return configuration.getDatasource();
            }
        };
    }

    public static void main(String[] args) throws Exception {
        new ThreeApplication().run(args);
    }

    @Override
    public void run(ApplicationConfiguration configuration, Environment environment) throws Exception {

        log.info("***Show Configuration : {}",configuration.toString());

        // 外部サービスの設定
        ThirdPartySettingUtils.initFirebaseOption(configuration);

        // リソース登録
        environment.jersey().register(ReserveResource.class);
        environment.jersey().register(DummyPagesResource.class);
        environment.jersey().register(LoginResource.class);
        environment.jersey().register(LoginApiResource.class);

        // DI設定
        AbstractBinder diBinder = ApplicationDefinitions.createDiBinder();
        environment.jersey().register(diBinder);

        // view関連
        environment.jersey().register(ViewConfiguration.createViewExceptionComponent());

        // セッション管理
        environment.servlets().setSessionHandler(new SessionHandler());
        //        environment.servlets().addFilter("csrfFilter", CsrfFilter.class)
        //                        .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, CSRF_TARGET_URL_PATTERNS);
        // ヘルスチェック機能
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("webapp/template", healthCheck);

        if (configuration.isDbAccess()) {
            this.hibernate = createHibernateBundle();
        } else {
            this.hibernate = null;
        }

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
    public void initialize(Bootstrap<ApplicationConfiguration> bootstrap) {

        bootstrap.addBundle(new ViewBundle<>());

        bootstrap.addBundle(new AssetsBundle(ASSETS_CSS, ASSETS_CSS, null, "css"));
        bootstrap.addBundle(new AssetsBundle(ASSETS_JS, ASSETS_JS, null, "js"));
        bootstrap.addBundle(new AssetsBundle(ASSETS_IMAGE, ASSETS_IMAGE, null, "image"));

        bootstrap.addBundle(new MigrationsBundle<>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ApplicationConfiguration configuration) {
                return configuration.getDatasource();
            }
        });
        //        bootstrap.addBundle(hibernate);
    }

}
