package jp.com.sskprj.dw.three.pages;

import jp.com.sskprj.dw.common.DummyUtils;
import jp.com.sskprj.dw.three.view.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/dummy/")
@Produces(MediaType.TEXT_HTML)

public class DummyPagesResource {

    public DummyPagesResource() {

    }

    @GET
    @Path("reserveCalender")
    public DummyViewInterface getReserveCalender() {
        return DummyUtils.getDummyView(new ReserveCalenderView());
    }

    @GET
    @Path("reserveInput")
    public DummyViewInterface getReserve01() {
        return DummyUtils.getDummyView(new ReserveInputView());
    }

    @GET
    @Path("reserveConfirm")
    public DummyViewInterface getReserve02() {
        return DummyUtils.getDummyView(new ReserveConfirmView());
    }

    @GET
    @Path("reserveComplete")
    public DummyViewInterface getReserve03() {
        return DummyUtils.getDummyView(new ReserveCompletedView());
    }

}
