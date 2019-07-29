package jp.com.sskprj.dw.common.service;

import java.util.UUID;

public class UniqueIdService {

    public String createReserveId() {
        return UUID.randomUUID().toString();
    }

}
