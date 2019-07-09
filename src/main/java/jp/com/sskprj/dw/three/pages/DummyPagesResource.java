package jp.com.sskprj.dw.three.pages;

import jp.com.sskprj.dw.three.view.AbstractOriginalView;
import jp.com.sskprj.dw.three.view.ReserveCalenderView;
import jp.com.sskprj.dw.three.view.ReserveConfirmView;
import jp.com.sskprj.dw.three.view.ReserveInputView;

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
    public AbstractOriginalView getReserveCalender() {
        return new ReserveCalenderView().getDummyView();
    }

    @GET
    @Path("reserveInput")
    public AbstractOriginalView getReserve01() {
        return new ReserveInputView().getDummyView();
    }

    @GET
    @Path("reserveConfirm")
    public AbstractOriginalView getReserve02() {
        return new ReserveConfirmView().getDummyView();
    }

}
