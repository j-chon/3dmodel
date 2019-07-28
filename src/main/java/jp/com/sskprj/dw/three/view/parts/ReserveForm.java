package jp.com.sskprj.dw.three.view.parts;

import lombok.Data;

import javax.ws.rs.FormParam;
import java.math.BigDecimal;

@Data
public class ReserveForm {

    @FormParam("csrf_token_reserve")
    private String token = "";

    @FormParam("cst_name")
    private String customerName = "";

    @FormParam("cst_phone_num")
    private String customerPhoneNumber = "";

    @FormParam("cst_post_num")
    private String customerPostNumber = "";

    @FormParam("cst_address")
    private String customerAddress = "";

    @FormParam("cst_address_01")
    private String customerAddress01 = "";

    @FormParam("is_mail_magazine_rcv")
    private int isMailMagazineReceive = 0;

    @FormParam("total_charge")
    private BigDecimal totalCharge = BigDecimal.ZERO;

}
