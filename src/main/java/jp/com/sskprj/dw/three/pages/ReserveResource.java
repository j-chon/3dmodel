package jp.com.sskprj.dw.three.pages;

import jp.com.sskprj.dw.common.session.SessionReserveResult;
import jp.com.sskprj.dw.three.service.ReserveService;
import jp.com.sskprj.dw.three.service.entity.ReserveEntity;
import jp.com.sskprj.dw.three.view.ReserveCalenderView;
import jp.com.sskprj.dw.three.view.ReserveCompletedView;
import jp.com.sskprj.dw.three.view.ReserveConfirmView;
import jp.com.sskprj.dw.three.view.ReserveInputView;
import jp.com.sskprj.dw.three.view.parts.ReserveForm;
import jp.com.sskprj.dw.three.view.parts.ViewHeaderData;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/reserve/")
@Produces(MediaType.TEXT_HTML)
public class ReserveResource {

    private ReserveService reserveService;

    public ReserveResource(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    //    @PermitAll
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
        reserveConfirmView.setCsrfToken(form.getToken());
        return reserveConfirmView;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("complete/")
    public Response postComplete(@BeanParam ReserveForm form, @Context HttpServletRequest httpServletRequest) {
        try {

            ReserveEntity reserveEntity = reserveService.register(new ReserveEntity());
            String reserveId = reserveEntity.getReserveId();
            URI uri = new URI("reserve/completed/" + reserveId + "/");
            System.out.println("転送するー");
            SessionReserveResult sessionReserveResult = new SessionReserveResult();
            sessionReserveResult.setReserveId("xxxx011");
            httpServletRequest.getSession().setAttribute("result", sessionReserveResult);
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
    @Path("completed/{reserveId}/")
    public ReserveCompletedView postCompleted(@PathParam("reserveId") String reserveId,
            @Context HttpServletRequest httpServletRequest) {

        SessionReserveResult result = (SessionReserveResult) httpServletRequest.getSession().getAttribute("result");
        if (result == null) {
            throw new WebApplicationException("セッションが切断されました。", Response.Status.BAD_REQUEST);
        }

        ReserveCompletedView reserveCompletedView = new ReserveCompletedView();
        reserveCompletedView.setViewHeaderData(new ViewHeaderData());
        reserveCompletedView.setReserveId(result.getReserveId());
        reserveCompletedView.setStoreName("testStoreName");
        reserveCompletedView.setTotalCharge("\\999,0000");
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
