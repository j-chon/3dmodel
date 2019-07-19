package jp.com.sskprj.dw.common.session;

import lombok.Getter;

public class SessionTokenCredentials {

    public SessionTokenCredentials(String tokenId) {
        this.tokenId = tokenId;
    }

    @Getter
    private String tokenId;

}
