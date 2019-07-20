package jp.com.sskprj.dw.common.session;

import lombok.Data;

@Data
public class SessionReserveResult {

    private String reserveId;

    private String customerName = "";

    private String customerPhoneNumber = "";

    private String customerPostNumber = "";

    private String customerAddress = "";

    private String customerAddress01 = "";

}
