package jp.com.sskprj.dw.three.entity.db;

import jp.com.sskprj.dw.common.enums.OAuthServiceType;
import lombok.Data;

import java.util.Date;

@Data
public class OAuthStatus {

    private String httpSessionId;

    private String loginSessionId;

    private OAuthServiceType oAuthServiceType;

    private String serviceToken;

    private Date publishedTime;

}
