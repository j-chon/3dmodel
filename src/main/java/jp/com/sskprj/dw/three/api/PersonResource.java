package jp.com.sskprj.dw.three.api;

import jp.com.sskprj.dw.three.dao.PersonDAO;
import jp.com.sskprj.dw.three.view.CalendarDto;
import jp.com.sskprj.dw.three.view.PersonView;
import lombok.NoArgsConstructor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/people/{id}")
@Produces(MediaType.TEXT_HTML)
public class PersonResource {

    private final PersonDAO dao;

    public PersonResource(PersonDAO dao) {
        this.dao = dao;
    }

    @GET
    public PersonView getPerson(@PathParam("id") String id) {

        CalendarDto calendarDto = createCalendarDto();

        PersonView personView = new PersonView(dao.find(id));
        personView.setCalendar(calendarDto);
        return personView;
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
