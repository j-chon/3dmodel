package jp.com.sskprj.dw.three.service.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReserveEntity {

    private String reserveId;

    private String customerName = "";

    private String customerPhoneNumber = "";

    private String customerPostNumber = "";

    private String customerAddress = "";

    private String customerAddress01 = "";

    private BigDecimal totalCharge = BigDecimal.ZERO;

    public ReserveEntity(String reserveId) {
        this.reserveId = reserveId;
    }
}
