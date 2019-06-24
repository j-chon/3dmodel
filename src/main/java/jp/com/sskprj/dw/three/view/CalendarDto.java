package jp.com.sskprj.dw.three.view;

import lombok.Data;

@Data
public class CalendarDto {

    private String[] titleList;

    private String[][] dayList;

    private String[][] holidayList;

    private String[][] subData1;

}
