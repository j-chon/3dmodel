package jp.com.sskprj.dw.three.pages;

import jp.com.sskprj.dw.common.DummyUtils;
import jp.com.sskprj.dw.common.service.UserSessionPoolService;
import jp.com.sskprj.dw.three.view.*;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/dummy/")
@Produces(MediaType.TEXT_HTML)

public class DummyPagesResource {

    @Inject
    UserSessionPoolService userSessionPoolService;

    public DummyPagesResource() {
        // Do nothing ,this method is not used.
    }

    @GET
    @Path("reserveCalender")
    public DummyViewInterface getReserveCalender() {
        return DummyUtils.getDummyView(new ReserveCalenderView());
    }

    @GET
    @Path("reserveInput")
    public DummyViewInterface getReserve01() {
        userSessionPoolService.createCsrfToken();
        return DummyUtils.getDummyView(new ReserveInputView());
    }

    @GET
    @Path("reserveConfirm")
    public DummyViewInterface getReserve02() {
        return DummyUtils.getDummyView(new ReserveConfirmView());
    }

    @GET
    @Path("reserveCompleted")
    public DummyViewInterface getReserve03() {
        return DummyUtils.getDummyView(new ReserveCompletedView());
    }

}
