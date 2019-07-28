package jp.com.sskprj.dw.common.session;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.util.Sets;

import java.util.*;

public class OAuth2Authenticator implements Authenticator<String, UserSessionBean> {



    /**
     * Valid users with mapping user -> roles
     */
    private static final Map<String, Set<String>> VALID_USERS;

    static {
        final Map<String, Set<String>> validUsers = new HashMap<>();
        validUsers.put("guest", Collections.emptySet());
        validUsers.put("good-guy", Collections.singleton("BASIC_GUY"));
        validUsers.put("chief-wizard", Sets.of("ADMIN", "BASIC_GUY"));
        VALID_USERS = Collections.unmodifiableMap(validUsers);
    }

    @Override
    public Optional<UserSessionBean> authenticate(String credentials) throws AuthenticationException {

        UserSessionBean userSessionBean = new UserSessionBean(credentials);
        return Optional.of(userSessionBean);
    }

}
