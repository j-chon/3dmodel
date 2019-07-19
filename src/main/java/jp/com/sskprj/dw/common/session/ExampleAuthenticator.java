package jp.com.sskprj.dw.common.session;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.util.Sets;

import java.util.*;

public class ExampleAuthenticator implements Authenticator<BasicCredentials, UserSessionBean> {

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
    public Optional<UserSessionBean> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if (VALID_USERS.containsKey(credentials.getUsername()) && "secret".equals(credentials.getPassword())) {
            return Optional.of(new UserSessionBean("aaa"));
        }
        return Optional.empty();
    }

}
