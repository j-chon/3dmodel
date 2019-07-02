package jp.com.sskprj.dw.three.api;

import com.google.inject.Inject;
import jp.com.sskprj.dw.three.dao.PersonDAO;
import jp.com.sskprj.dw.three.view.CalendarDto;
import jp.com.sskprj.dw.three.view.PersonView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/people/{id}")
@Produces(MediaType.TEXT_HTML)
public class PersonResource {


    private PersonDAO dao;

    @Inject
    public PersonResource( PersonDAO dao) {
        this.dao = dao;
    }

    @GET
    public PersonView getPerson(@PathParam("id") String id) {

//        CalendarDto calendarDto = createCalendarDto();

        PersonView personView = new PersonView(dao.find(id));
//        personView.setCalendar(calendarDto);
        return personView;
    }


}
