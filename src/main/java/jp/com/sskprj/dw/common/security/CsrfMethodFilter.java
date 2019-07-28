package jp.com.sskprj.dw.common.security;

import com.google.common.base.Strings;
import jp.com.sskprj.dw.common.service.UserSessionPoolService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;

@Slf4j
public class CsrfMethodFilter {

    private static final String CSRF_TOKEN_KEY = "csrf_token";

    private HttpServletRequest webRequest;

    private UserSessionPoolService userSessionPoolService;

    private String key;

    // 現在利用中のトークンの値
    @Getter
    private String currentToken;

    public CsrfMethodFilter(UserSessionPoolService userSessionPoolService, String key,
            HttpServletRequest httpServletRequest) {
        this.userSessionPoolService = userSessionPoolService;
        this.key = key;
        this.webRequest = httpServletRequest;
    }

    /**
     * チェックに引っ掛かった場合はエラーを返す
     */
    public void start() {
        log.info("リクエスト - {}", webRequest);
        String tokenFullKey = getTokenFullKey();
        String jsessionid = webRequest.getSession().getId();
        String tokenFromSession = userSessionPoolService.selectUserSessionInfo(tokenFullKey, jsessionid);

        log.info("tokenFromSession - {}", tokenFromSession);

        // セッションにトークンがあるかどうか
        // 戻ったときとかはセッションがあるはずなので、何もしない
        if (Strings.isNullOrEmpty(tokenFromSession)) {
            log.info("OK - {}", "トークンなし");
            // トークン新規発行
            String newToken = userSessionPoolService.createCsrfToken();
            //            // リクエストに設定
            //            webRequest.getSession().setAttribute(tokenFullKey, tokenFromSession);
            // セッションプールに設定
            userSessionPoolService.put(tokenFullKey, jsessionid, newToken);
            this.currentToken = newToken;
        } else {
            this.currentToken = tokenFromSession;
        }
    }

    private String getTokenFullKey() {
        return CSRF_TOKEN_KEY + "_" + this.key;
    }

    public void close() {
        String jsessionid = webRequest.getSession().getId();
        boolean isUpdated = userSessionPoolService.removeSessionInfo(jsessionid, getTokenFullKey());
    }

    public void process(String formToken) {
        String tokenFullKey = getTokenFullKey();
        String jsessionid = webRequest.getSession().getId();
        log.info("フィルター確認 - {}", jsessionid);

        String tokenFromSession = userSessionPoolService.selectUserSessionInfo(tokenFullKey, jsessionid);
        if (Strings.isNullOrEmpty(tokenFromSession)) {
            log.info("NG - {}", "セッションなし");
            WebApplicationException webApplicationException = new WebApplicationException();
            throw webApplicationException;
        }
        if (Strings.isNullOrEmpty(formToken)) {
            log.info("NG - {}", "パラメタなし");
            WebApplicationException webApplicationException = new WebApplicationException();
            throw webApplicationException;
        }

        boolean matches = StringUtils.equals(formToken, tokenFromSession);
        if (matches) {
            log.info("OK - {} - {}", formToken, tokenFromSession);
        } else {
            log.info("NG - {} - {}", formToken, tokenFromSession);
            WebApplicationException webApplicationException = new WebApplicationException();
            throw webApplicationException;
        }
        this.currentToken = tokenFromSession;
    }
}
