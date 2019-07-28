package jp.com.sskprj.dw.common.session;

import lombok.Getter;

import java.security.Principal;
import java.util.Set;

public class UserSessionBean implements Principal {

    @Getter
    private final String name;

    @Getter
    private final Set<String> roles;

    public UserSessionBean(String name) {
        this.name = name;
        this.roles = null;
    }

    public UserSessionBean(String name, Set<String> roles) {
        this.name = name;
        this.roles = roles;
    }

}
