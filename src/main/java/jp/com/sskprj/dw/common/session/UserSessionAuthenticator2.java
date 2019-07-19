package jp.com.sskprj.dw.common.session;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import java.util.Optional;

public class UserSessionAuthenticator2 implements Authenticator<String, UserSessionBean> {

    @Override
    public Optional<UserSessionBean> authenticate(String credentials) throws AuthenticationException {
        UserSessionBean userSessionBean = new UserSessionBean("abcdefg");
        System.out.println("UserSessionAuthenticator2に到達");
        return Optional.of(userSessionBean);
    }
}
