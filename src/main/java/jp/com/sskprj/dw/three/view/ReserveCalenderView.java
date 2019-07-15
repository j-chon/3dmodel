package jp.com.sskprj.dw.three.view;

import jp.com.sskprj.dw.three.view.parts.CalendarDto;
import jp.com.sskprj.dw.three.view.parts.ViewHeaderData;
import lombok.Getter;
import lombok.Setter;

public class ReserveCalenderView extends AbstractOriginalView implements DummyViewInterface {

    private static final String TEMPLATE_NAME = "reserveCalendar.ftl";

    @Getter
    @Setter
    private CalendarDto calendarDto;

    @Getter
    @Setter
    private ViewHeaderData viewHeaderData;

    public ReserveCalenderView() {
        super(TEMPLATE_NAME);

        this.calendarDto = createCalendarDto();
        this.viewHeaderData = new ViewHeaderData();
        viewHeaderData.setTitle("カレンダー");
    }

    @Override
    public void initDummyData() {
        this.viewHeaderData = new ViewHeaderData();
        this.viewHeaderData.setTitle("ダミータイトル");
        calendarDto = createCalendarDto();
    }

    private static CalendarDto createCalendarDto() {
        CalendarDto calendarDto = new CalendarDto();
        calendarDto.setTitleList(new String[] { "日", "月", "火", "水", "木", "金", "土" });

        String[][] dayList = new String[][] { new String[] { "", "", "", "1", "2", "3", "4" },
                new String[] { "5", "6", "7", "8", "9", "10", "11" },
                new String[] { "12", "13", "14", "15", "16", "17", "18" },
                new String[] { "19", "20", "21", "22", "23", "24", "25" },
                new String[] { "26", "27", "28", "29", "30", "31", "" }, };
        String[][] holidayList = new String[][] { new String[] { "", "", "", "元日", "", "" },
                new String[] { "", "", "", "", "", "" }, new String[] { "", "", "", "", "", "" },
                new String[] { "", "", "", "", "", "" }, new String[] { "", "", "", "", "", "" }, };

        calendarDto.setDayList(dayList);
        calendarDto.setHolidayList(holidayList);
        return calendarDto;
    }
}
