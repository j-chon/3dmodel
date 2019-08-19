package jp.com.sskprj.dw.three.setup;

import jp.com.sskprj.dw.common.service.OAuthStatusService;
import jp.com.sskprj.dw.common.service.UniqueIdService;
import jp.com.sskprj.dw.common.service.UserSessionPoolService;
import jp.com.sskprj.dw.three.service.FirebaseAuthService;
import jp.com.sskprj.dw.three.service.InterfaceFirebaseAuthService;
import jp.com.sskprj.dw.three.service.ReserveService;
import org.glassfish.jersey.internal.inject.AbstractBinder;

/**
 * Here you need your own definition.
 */
public class ApplicationDefinitions {

    /**
     * DIの定義
     *
     * @return
     */
    public static AbstractBinder createDiBinder() {
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
}
