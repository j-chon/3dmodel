package jp.com.sskprj.dw.three.view.parts;

import lombok.Data;

@Data
public class ReserveForm {

    private String name;

    private String name01;

    private String phoneNumber;

    private String targetDate;

    private String startTime;

    private String endTime;

    private int isMailMagazineReceive =0;

    private String textHairImage;

}
