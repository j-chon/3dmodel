package jp.com.sskprj.dw.common.session;

import lombok.Data;

import java.io.Serializable;

@Data
public class SessionReserveResult implements Serializable {

    private static final long serialVersionUID = 9106808846988807057L;

    private String reserveId;

    private String customerName = "";

    private String customerPhoneNumber = "";

    private String customerPostNumber = "";

    private String customerAddress = "";

    private String customerAddress01 = "";

}
