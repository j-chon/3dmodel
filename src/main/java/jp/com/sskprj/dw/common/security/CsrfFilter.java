package jp.com.sskprj.dw.common.security;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * Csrf対策のフィルター
 */
public class CsrfFilter implements Filter {

    public static final String CSRF_TOKEN_KEY = "csrf_token";
    private final ImmutableList EXCLUDED_TYPES = ImmutableList.of("application/json");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();

        String csrf_token_from_session = (String) session.getAttribute(CSRF_TOKEN_KEY);

        if (Strings.isNullOrEmpty(csrf_token_from_session)) {
            csrf_token_from_session = UUID.randomUUID().toString().replace("-", "");
            session.setAttribute(CSRF_TOKEN_KEY, csrf_token_from_session);
        }

        if (httpServletRequest.getMethod().equalsIgnoreCase("POST") && !EXCLUDED_TYPES.contains(
                request.getContentType())) {

            String csrf_parameter_from_request = httpServletRequest.getParameter(CSRF_TOKEN_KEY);
            boolean matches = Objects.equals(csrf_parameter_from_request, csrf_token_from_session);
            if (matches) {
                System.out.println("認可");
            } else {
                httpServletResponse.sendError(403, "Unauthorized");
                return;
            }
        }

        chain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
