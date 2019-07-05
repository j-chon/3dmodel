package jp.com.sskprj.dw.three.pages;

import jp.com.sskprj.dw.three.dao.ReserveCalendarDAO;
import jp.com.sskprj.dw.three.view.CalendarDto;
import jp.com.sskprj.dw.three.view.ReserveCalenderView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/reserve/calendar/")
@Produces(MediaType.TEXT_HTML)
public class ReserveCalendarResource {

    private ReserveCalendarDAO dao;

    public ReserveCalendarResource( ReserveCalendarDAO dao) {
        this.dao = dao;
    }

    public ReserveCalendarResource() {

    }

    @GET
    @Path("{serviceId}")
    public ReserveCalenderView getServiceCalendar(@PathParam("serviceId") String id) {

        long lngId = Long.parseLong(id);

        CalendarDto calendarDto = createCalendarDto();

        ReserveCalenderView reserveCalenderView = new ReserveCalenderView(dao.find(lngId));
//        personView.setCalendar(calendarDto);
        return reserveCalenderView;
    }

    private static CalendarDto createCalendarDto() {
        CalendarDto calendarDto = new CalendarDto();
        calendarDto.setTitleList(new String[] { "日", "月", "火", "水", "木", "金", "土" });

        String[][] dayList = new String[][]{
                new String[]{"","","","1","2","3","4"},
                new String[]{"5","6","7","8","9","10","11"},
                new String[]{"12","13","14","15","16","17","18"},
                new String[]{"19","20","21","22","23","24","25"},
                new String[]{"26","27","28","29","30","31",""},
        };
        String[][] holidayList = new String[][]{
                new String[]{"","","","元日","",""},
                new String[]{"","","","","",""},
                new String[]{"","","","","",""},
                new String[]{"","","","","",""},
                new String[]{"","","","","",""},
        };

        calendarDto.setDayList(dayList);
        calendarDto.setHolidayList(holidayList);
        return calendarDto;
    }

}
