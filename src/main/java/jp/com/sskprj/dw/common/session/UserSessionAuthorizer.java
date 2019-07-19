package jp.com.sskprj.dw.common.session;

import io.dropwizard.auth.Authorizer;

/**
 * This class is a strategy class which,given a principal and a role ,decides if access-authority is granted to principal.
 */
public class UserSessionAuthorizer implements Authorizer<UserSessionBean> {

    @Override
    public boolean authorize(UserSessionBean principal, String role) {
        System.out.println("UserSessionAuthorizer");
        return true;
    }
}
