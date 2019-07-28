package jp.com.sskprj.dw.common.security;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Cookie;
import java.io.Serializable;

@Slf4j
public class RequestUtils {

    public static String extractJsessionid(ContainerRequestContext requestContext) {
        Cookie jsessionid = requestContext.getCookies().get("JSESSIONID");
        log.info("{} - {}", jsessionid.getName(), jsessionid.getValue());
        return jsessionid.getValue();
    }

    public static void setSessionAttribute(HttpServletRequest httpServletRequest, String key,
            Serializable sessionReserveResult) {
        httpServletRequest.getSession().setAttribute(key, sessionReserveResult);
        log.info("セッションに設定{}", sessionReserveResult);
    }
}
