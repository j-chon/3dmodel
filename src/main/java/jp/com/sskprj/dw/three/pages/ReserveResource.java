package jp.com.sskprj.dw.three.pages;

import jp.com.sskprj.dw.three.view.ReserveCalenderView;
import jp.com.sskprj.dw.three.view.ReserveCompletedView;
import jp.com.sskprj.dw.three.view.ReserveConfirmView;
import jp.com.sskprj.dw.three.view.ReserveInputView;
import jp.com.sskprj.dw.three.view.parts.ReserveForm;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/reserve/")
@Produces(MediaType.TEXT_HTML)
public class ReserveResource {

    public ReserveResource() {

    }

    @PermitAll
    @GET
    @Path("input")
    public ReserveInputView getInput(@QueryParam("serviceId") String serviceId, @Context HttpServletRequest request) {

        System.out.println("URLは" + request.getPathInfo());

        ReserveInputView reserveInputView = new ReserveInputView(request);
        reserveInputView.initDummyData();

        return reserveInputView;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("confirm/")
    public ReserveConfirmView postConfirm(@BeanParam ReserveForm form) {

        ReserveConfirmView reserveConfirmView = new ReserveConfirmView();
        reserveConfirmView.initDummyData();
        return reserveConfirmView;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("complete/")
    public Response postComplete(@BeanParam ReserveForm form, @Context UriInfo uriInfo) {
        try {
            // ここにパラメータ hoge を保存する処理を書く

            // TODO 予約ID固定で転送する。
            String reserveId = "xyz01";
            URI uri = new URI("reserve/completed/?reserveId=" + reserveId);
            //            uri = uriInfo.getBaseUriBuilder().path("reserve/completed/?reserveId=" + reserveId).build();
            System.out.println("転送するー");

            Response response = Response.seeOther(uri).build();
            System.out.println("転送準備OK");
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 完了後の状態を表示する画面。
     * TODO セッションチェックを行って切れたら表示しないようにする。
     *
     * @param reserveId
     * @return
     */
    @GET
    @Path("completed/")
    public ReserveCompletedView postCompleted(@QueryParam("reserveId") String reserveId) {
        System.out.println("転送された");
        ReserveCompletedView reserveCompletedView = new ReserveCompletedView();
        reserveCompletedView.initDummyData();
        reserveCompletedView.setReserveId(reserveId);
        System.out.println("reserveId=" + reserveId);
        return reserveCompletedView;
    }

    @GET
    @Path("calendar/{targetMonth}/")
    public ReserveCalenderView getServiceCalendar(@PathParam("targetMonth") String targetMonth) {

        long lngTargetMonth = Long.parseLong(targetMonth);

        ReserveCalenderView reserveCalenderView = new ReserveCalenderView();
        // TODO initDummy
        reserveCalenderView.initDummyData();
        return reserveCalenderView;
    }

}
