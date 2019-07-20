package jp.com.sskprj.dw.three.service;

import jp.com.sskprj.dw.three.service.entity.ReserveEntity;

public class ReserveService {

    public ReserveEntity register(ReserveEntity reserveEntity) {
        String temp = reserveEntity.getReserveId();
        reserveEntity.setReserveId(temp + Math.random());
        return reserveEntity;
    }

}
