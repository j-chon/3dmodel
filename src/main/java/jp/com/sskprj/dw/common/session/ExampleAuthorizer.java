package jp.com.sskprj.dw.common.session;

import io.dropwizard.auth.Authorizer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleAuthorizer implements Authorizer<UserSessionBean> {

    @Override
    public boolean authorize(UserSessionBean user, String role) {
        log.info("ExampleAuthorizer");
        return true;
    }
}
