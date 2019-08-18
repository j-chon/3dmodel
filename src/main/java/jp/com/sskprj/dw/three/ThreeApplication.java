package jp.com.sskprj.dw.three;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;
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
import jp.com.sskprj.dw.common.service.OAuthStatusService;
import jp.com.sskprj.dw.common.service.UniqueIdService;
import jp.com.sskprj.dw.common.service.UserSessionPoolService;
import jp.com.sskprj.dw.common.session.OAuth2Authenticator;
import jp.com.sskprj.dw.common.session.OAuth2Authorizer;
import jp.com.sskprj.dw.common.session.UserSessionBean;
import jp.com.sskprj.dw.three.api.LoginApiResource;
import jp.com.sskprj.dw.three.config.ThreeConfiguration;
import jp.com.sskprj.dw.three.config.ViewConfiguration;
import jp.com.sskprj.dw.three.entity.db.OAuthStatus;
import jp.com.sskprj.dw.three.health.TemplateHealthCheck;
import jp.com.sskprj.dw.three.pages.DummyPagesResource;
import jp.com.sskprj.dw.three.pages.LoginResource;
import jp.com.sskprj.dw.three.pages.ReserveResource;
import jp.com.sskprj.dw.three.service.FirebaseAuthService;
import jp.com.sskprj.dw.three.service.InterfaceFirebaseAuthService;
import jp.com.sskprj.dw.three.service.ReserveService;
import lombok.Getter;
import org.eclipse.jetty.server.session.SessionHandler;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ThreeApplication extends Application<ThreeConfiguration> {

    private static final String ASSETS_CSS = "/assets/css";
    private static final String ASSETS_JS = "/assets/js";
    private static final String ASSETS_IMAGE = "/assets/image";
    private static final String[] CSRF_TARGET_URL_PATTERNS = { "/reserve/*" };
    public static final String REFRESH_TOKEN_JSON = "C:\\app\\firebase\\refreshToken.json";
    public static final String DATABASE_URL = "https://userauthentication01-4554b.firebaseio.com/";

    private HibernateBundle<ThreeConfiguration> hibernate;

    private HibernateBundle<ThreeConfiguration> createHibernateBundle() {
        return new HibernateBundle<ThreeConfiguration>(OAuthStatus.class) {
            @Override
            public DataSourceFactory getDataSourceFactory(ThreeConfiguration configuration) {
                return configuration.getDatasource();
            }
        };
    }

    public static void main(String[] args) throws Exception {
        new ThreeApplication().run(args);
    }

    @Override
    public void run(ThreeConfiguration configuration, Environment environment) throws Exception {

        initFirebaseOption();
        // リソース登録
        environment.jersey().register(ReserveResource.class);
        environment.jersey().register(DummyPagesResource.class);
        environment.jersey().register(LoginResource.class);
        environment.jersey().register(LoginApiResource.class);

        // DI設定
        AbstractBinder diBinder = createDiBinder();
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

    /**
     * DIの設定
     *
     * @return
     */
    private AbstractBinder createDiBinder() {
        return new AbstractBinder() {
            @Override
            protected void configure() {
                bind(UserSessionPoolService.class).to(UserSessionPoolService.class);
                bind(ReserveService.class).to(ReserveService.class);
                bind(UniqueIdService.class).to(UniqueIdService.class);
                bind(OAuthStatusService.class).to(OAuthStatusService.class);
                bind(FirebaseAuthService.class).to(InterfaceFirebaseAuthService.class);
            }
        };
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
    private void initFirebaseOption() {
        try {
            FileInputStream refreshToken = new FileInputStream(REFRESH_TOKEN_JSON);
            FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(
                    GoogleCredentials.fromStream(refreshToken)).setDatabaseUrl(DATABASE_URL).build();
            FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
