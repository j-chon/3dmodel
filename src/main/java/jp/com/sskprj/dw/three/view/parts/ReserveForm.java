package jp.com.sskprj.dw.three.view.parts;

import lombok.Data;

import javax.ws.rs.FormParam;

@Data
public class ReserveForm {

    @FormParam("customerName")
    private String name = "";

    @FormParam("phoneNumber")
    private String phoneNumber = "";

    private String targetDate = "";

    private String startTime = "";

    private String endTime = "";

    private int isMailMagazineReceive = 0;

    private String textHairImage = "";

}
