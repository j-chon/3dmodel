package jp.com.sskprj.dw.common.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * Csrf対策のフィルター
 */
@Slf4j
public class OriginalServletFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Do nothing ,this method is not used.
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        log.info("ServletFilter called");
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        // Do nothing ,this method is not used.
    }
}
