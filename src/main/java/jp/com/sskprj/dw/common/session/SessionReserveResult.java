package jp.com.sskprj.dw.common.session;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SessionReserveResult implements Serializable {

    private static final long serialVersionUID = 9106808846988807057L;

    private String reserveId = "";

    private String customerName = "";

    private String customerPhoneNumber = "";

    private String customerPostNumber = "";

    private String customerAddress = "";

    private String customerAddress01 = "";

    private BigDecimal totalCharge = BigDecimal.ZERO;

}
