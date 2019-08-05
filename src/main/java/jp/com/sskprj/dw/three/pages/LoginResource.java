package jp.com.sskprj.dw.three.pages;

import jp.com.sskprj.dw.three.view.LoginView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/login/")
@Produces(MediaType.TEXT_HTML)
public class LoginResource {

    public LoginResource() {
        // Do nothing ,this method is not used.
    }

    @GET
    @Path("")
    public LoginView getIndex() {
        LoginView loginView = new LoginView();
        return loginView;
    }

}
