package jp.com.sskprj.dw.common.service;

import jp.com.sskprj.dw.common.enums.OAuthServiceType;
import jp.com.sskprj.dw.three.entity.db.OAuthStatus;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OAuthStatusService {

    private List<OAuthStatus> mem = Lists.newArrayList();

    public boolean registerToken(String sessionId, OAuthServiceType oAuthServiceType, String token) {
        OAuthStatus oAuthStatus = new OAuthStatus();
        oAuthStatus.setOAuthServiceType(oAuthServiceType);
        oAuthStatus.setHttpSessionId(sessionId);
        oAuthStatus.setServiceToken(token);
        oAuthStatus.setPublishedTime(new Date());
        return true;
    }

    public OAuthStatus select(String sessionId) {
        Optional<OAuthStatus> optional = mem.stream()
                .filter(x -> StringUtils.equals(x.getHttpSessionId(), sessionId))
                .findFirst();
        return optional.get();
    }

}
