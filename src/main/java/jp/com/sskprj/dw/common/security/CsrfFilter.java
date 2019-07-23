package jp.com.sskprj.dw.common.security;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import jp.com.sskprj.dw.common.service.UserSessionPoolService;
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
    private static final ImmutableList EXCLUDED_TYPES = ImmutableList.of("application/json");

    private UserSessionPoolService userSessionPoolService;

    public CsrfFilter(UserSessionPoolService userSessionPoolService) {
        super();
        // TODO セッション情報をここから取得するように変更する
        this.userSessionPoolService = userSessionPoolService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Do nothing ,this method is not used.
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();


        log.info("フィルター確認 - {}", request.getPathInfo());

        // TODO セッションのみ使う場合
        //        String csrfTokenFromSession = (String) session.getAttribute(CSRF_TOKEN_KEY);

        // TODO ユーザーセッションプールを使う
        String csrfTokenFromSession = userSessionPoolService.selectUserSessionInfo(CSRF_TOKEN_KEY, session.getId(),
                session.getLastAccessedTime());
        //        session.setAttribute("previous_accessed_time",session.getLastAccessedTime());

        // セッションにトークンがあるかどうか
        if (Strings.isNullOrEmpty(csrfTokenFromSession)) {
            log.info("フィルター - {}", "トークンなし");
            csrfTokenFromSession = UUID.randomUUID().toString().replace("-", "");
            session.setAttribute(CSRF_TOKEN_KEY, csrfTokenFromSession);
            userSessionPoolService.put(CSRF_TOKEN_KEY, session.getId(), csrfTokenFromSession, 0L);
        }

        log.info("フィルター - {}{}", request.getMethod(), request.getContentType());
        if (request.getMethod().equalsIgnoreCase("POST") && !EXCLUDED_TYPES.contains(request.getContentType())) {

            String csrfParameterFromRequest = request.getParameter(CSRF_TOKEN_KEY);
            boolean matches = Objects.equals(csrfParameterFromRequest, csrfTokenFromSession);
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
        // Do nothing ,this method is not used.
    }
}
