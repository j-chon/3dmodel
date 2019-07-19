package jp.com.sskprj.dw.common.session;

import io.dropwizard.auth.Authorizer;

public class ExampleAuthorizer implements Authorizer<UserSessionBean> {

    @Override
    public boolean authorize(UserSessionBean user, String role) {
        System.out.println("ExampleAuthorizer");
        return true;
    }
}
