package jp.com.sskprj.dw.three.api;

import jp.com.sskprj.dw.three.dao.PersonDAO;
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
      return new PersonView(dao.find(id));
    }

}
