package jp.com.sskprj.dw.three.view.parts;

import lombok.Data;

@Data
public class CalendarDto {

    private String targetMonth;

    private String[] titleList;

    private String[][] dayList;

    private String[][] holidayList;

    private String[][] subData1;

}
