package jp.com.sskprj.dw.common.session;

import org.apache.commons.lang3.StringUtils;

import java.security.Principal;

public class UserSessionBean implements Principal {

    private String name;

    public UserSessionBean(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object another) {
        return StringUtils.equals(this.toString(), another.toString());
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
