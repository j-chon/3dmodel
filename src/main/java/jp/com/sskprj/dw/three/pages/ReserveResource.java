package jp.com.sskprj.dw.three.pages;

import jp.com.sskprj.dw.three.view.ReserveCalenderView;
import jp.com.sskprj.dw.three.view.ReserveInputView;
import jp.com.sskprj.dw.three.view.parts.CalendarDto;
import jp.com.sskprj.dw.three.view.parts.ReserveForm;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/reserve/")
@Produces(MediaType.TEXT_HTML)
public class ReserveResource {

    public ReserveResource() {

    }

    @GET
    @Path("input/{serviceId}/")
    public ReserveInputView getInput(@PathParam("serviceId") String serviceId) {
        ReserveInputView reserveInputView = new ReserveInputView();
        reserveInputView.setStoreId(serviceId);
        reserveInputView.setReserveForm(new ReserveForm());
        return reserveInputView;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("confirm/")
    public ReserveInputView postConfirm(@BeanParam ReserveForm form) {
        ReserveInputView reserveInputView = new ReserveInputView();
        reserveInputView.setStoreId("");
        reserveInputView.setReserveForm(form);
        System.out.println("==========================" + form.toString());
        return reserveInputView;
    }

    @GET
    @Path("calendar/{targetMonth}/")
    public ReserveCalenderView getServiceCalendar(@PathParam("targetMonth") String targetMonth) {

        long lngTargetMonth = Long.parseLong(targetMonth);

        CalendarDto calendarDto = createCalendarDto();

        ReserveCalenderView reserveCalenderView = new ReserveCalenderView();
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
