package jp.com.sskprj.dw.common.session;

import io.dropwizard.auth.AuthFilter;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Cookie;
import java.io.IOException;

@Priority(Priorities.AUTHENTICATION)
public class UserSessionAuthFilter extends AuthFilter<SessionTokenCredentials, UserSessionBean> {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println("UserSessionAuthFilterに到達");
        Cookie cookie = requestContext.getCookies().get("ssk_session_id");
        if (cookie == null) {
            //            requestContext.getCookies().put("ssk_session_id",new Cookie("ckName","ckValue"));
        }
    }

    /**
     * Builder for {@link BasicCredentialAuthFilter}.
     * <p>An {@link Authenticator} must be provided during the building process.</p>
     *
     * @param
     */
    public static class Builder
            extends AuthFilterBuilder<SessionTokenCredentials, UserSessionBean, UserSessionAuthFilter> {

        @Override
        protected UserSessionAuthFilter newInstance() {
            return new UserSessionAuthFilter();
        }
    }
}
