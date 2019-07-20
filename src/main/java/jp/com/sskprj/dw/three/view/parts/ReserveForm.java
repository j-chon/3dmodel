package jp.com.sskprj.dw.three.view.parts;

import lombok.Data;

import javax.ws.rs.FormParam;
import java.math.BigDecimal;

@Data
public class ReserveForm {

    @FormParam("csrf_token")
    private String token = "";

    @FormParam("cust_name")
    private String customerName = "";

    @FormParam("cust_phone_num")
    private String customerPhoneNumber = "";

    @FormParam("cust_post_num")
    private String customerPostNumber = "";

    @FormParam("cust_address")
    private String customerAddress = "";

    @FormParam("cust_address_01")
    private String customerAddress01 = "";

    private String targetDate = "";

    private String startTime = "";

    private String endTime = "";

    @FormParam("cast_request")
    private String customerRequest = "";

    @FormParam("cast_request_detail")
    private String customerRequestDetail = "";

    @FormParam("is_mail_magazine_rcv")
    private int isMailMagazineReceive = 0;

    private String textHairImage = "";

    @FormParam("total_charge")
    private BigDecimal totalCharge = BigDecimal.ZERO;

}
