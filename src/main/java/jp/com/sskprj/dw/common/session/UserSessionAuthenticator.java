package jp.com.sskprj.dw.common.session;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import java.util.Optional;

public class UserSessionAuthenticator implements Authenticator<SessionTokenCredentials, UserSessionBean> {

    @Override
    public Optional<UserSessionBean> authenticate(SessionTokenCredentials credentials) throws AuthenticationException {
        UserSessionBean userSessionBean = new UserSessionBean("abcdefg");
        System.out.println("UserSessionAuthenticatorに到達");
        return Optional.of(userSessionBean);
    }
}
