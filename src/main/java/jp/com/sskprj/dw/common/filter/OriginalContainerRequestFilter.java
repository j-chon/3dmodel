package jp.com.sskprj.dw.common.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import java.io.IOException;

@Slf4j
public class OriginalContainerRequestFilter implements ContainerRequestFilter {

    @Context
    private HttpServletRequest webRequest;

    public void filter(ContainerRequestContext requestContext) throws IOException {
        log.info("OriginalContainerRequestFilter called - {}", webRequest.getSession().getId());
    }

}
