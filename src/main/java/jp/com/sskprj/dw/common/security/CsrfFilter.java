package jp.com.sskprj.dw.common.security;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class CsrfFilter implements Filter {

    public static final String CSRF_TOKEN_KEY = "csrf_token";
    private final ImmutableList EXCLUDED_TYPES = ImmutableList.of("application/json");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String csrfTokenFromSession = (String) session.getAttribute(CSRF_TOKEN_KEY);
        log.info("フィルター確認 - {}", request.getPathInfo());

        // セッションにトークンがない場合
        if (Strings.isNullOrEmpty(csrfTokenFromSession)) {
            log.info("フィルター - {}", "トークンなし");
            csrfTokenFromSession = UUID.randomUUID().toString().replace("-", "");
            session.setAttribute(CSRF_TOKEN_KEY, csrfTokenFromSession);
        }

        log.info("フィルター - {}{}", request.getMethod(), request.getContentType());
        if (request.getMethod().equalsIgnoreCase("POST") && !EXCLUDED_TYPES.contains(
                request.getContentType())) {

            String csrf_parameter_from_request = request.getParameter(CSRF_TOKEN_KEY);
            boolean matches = Objects.equals(csrf_parameter_from_request, csrfTokenFromSession);
            if (matches) {
                log.info("許可 - {}", request.getPathInfo());
            } else {
                log.info("不許可 - {}", request.getPathInfo());
                response.sendError(403, "Unauthorized");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
