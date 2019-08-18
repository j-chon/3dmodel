package jp.com.sskprj.dw.common.security;

import com.google.common.base.Strings;
import jp.com.sskprj.dw.common.service.UserSessionPoolService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;

/**
 * CSRF対策のトークン管理クラス
 * 開始～維持～破棄のメソッドで呼び出す。
 */
@Slf4j
public class CsrfMethodFilter {

    private static final String CSRF_TOKEN_KEY = "csrf_token";

    private UserSessionPoolService userSessionPoolService;

    private String key;

    // 現在利用中のトークンの値
    @Getter
    private String currentToken;

    public CsrfMethodFilter(UserSessionPoolService userSessionPoolService, String key) {
        this.userSessionPoolService = userSessionPoolService;
        this.key = key;
    }

    /**
     * チェックに引っ掛かった場合はエラーを返す
     */
    public void start(HttpServletRequest httpServletRequest) {
        log.info("リクエスト - {}", httpServletRequest);
        String tokenFullKey = getTokenFullKey();
        String jsessionid = httpServletRequest.getSession().getId();
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

    /**
     * トークンを破棄するメソッドで呼び出す。
     *
     * @return
     */
    public boolean close(HttpServletRequest httpServletRequest) {
        String jsessionid = httpServletRequest.getSession().getId();
        return userSessionPoolService.removeSessionInfo(jsessionid, getTokenFullKey());
    }

    /**
     * トークンを維持するメソッドで呼び出す。
     *
     * @return
     */
    public void process(HttpServletRequest httpServletRequest,String formToken) {
        String tokenFullKey = getTokenFullKey();
        String jsessionid = httpServletRequest.getSession().getId();
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
