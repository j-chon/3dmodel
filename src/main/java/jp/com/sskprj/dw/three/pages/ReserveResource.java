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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;

@Slf4j
@Path("/reserve/")
@Produces(MediaType.TEXT_HTML)
public class ReserveResource {

    private ReserveService reserveService;

    public ReserveResource(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    /**
     * 入力画面
     *
     * @param serviceId 仮
     * @param request   仮
     * @return 画面のview
     */
    @GET
    @Path("input")
    public ReserveInputView getInput(@QueryParam("serviceId") String serviceId, @Context HttpServletRequest request) {

        log.info("URL : {}", request.getPathInfo());

        ReserveInputView reserveInputView = new ReserveInputView(request);
        reserveInputView.initDummyData();

        return reserveInputView;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("confirm/")
    public ReserveConfirmView postConfirm(@BeanParam ReserveForm form, @Context HttpServletRequest httpServletRequest) {

        ReserveConfirmView reserveConfirmView = new ReserveConfirmView();
        reserveConfirmView.setViewHeaderData(new ViewHeaderData());
        reserveConfirmView.getViewHeaderData().setTitle("予約確認画面");
        reserveConfirmView.setReserveForm(form);
        reserveConfirmView.setCsrfToken(form.getToken());

        SessionReserveResult sessionReserveResult = new SessionReserveResult();

        try {
            BeanUtils.copyProperties(sessionReserveResult, form);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        setSessionAttribute(httpServletRequest, sessionReserveResult);

        log.info("入力内容 : {}", form);
        return reserveConfirmView;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("complete/")
    public Response postComplete(@Context HttpServletRequest httpServletRequest) {
        try {

            SessionReserveResult result = (SessionReserveResult) httpServletRequest.getSession().getAttribute("result");
            String newReserveId = calcNewReserveId();

            ReserveEntity reserveEntity = new ReserveEntity(newReserveId);
            BeanUtils.copyProperties(reserveEntity, result);

            log.info("登録する内容{}", reserveEntity);

            reserveService.register(reserveEntity);

            URI uri = new URI(String.format("reserve/completed/"));

            SessionReserveResult sessionReserveResult = new SessionReserveResult();

            // セッションに移し替え
            BeanUtils.copyProperties(sessionReserveResult, reserveEntity);

            setSessionAttribute(httpServletRequest, sessionReserveResult);

            Response response = Response.seeOther(uri).build();
            log.info("転送準備OK");
            return response;
        } catch (Exception e) {
            log.error("登録失敗", e);
            return null;
        }
    }

    private String calcNewReserveId() {
        return "TEST";
    }

    private void setSessionAttribute(HttpServletRequest httpServletRequest,
            Serializable sessionReserveResult) {
        httpServletRequest.getSession().setAttribute("result", sessionReserveResult);
        log.info("セッションに設定{}", sessionReserveResult);
    }

    /**
     * 完了後の状態を表示する画面。
     *
     * @return 予約完了画面View
     */
    @GET
    @Path("completed/")
    public ReserveCompletedView postCompleted(@Context HttpServletRequest httpServletRequest) {

        SessionReserveResult result = (SessionReserveResult) httpServletRequest.getSession().getAttribute("result");
        if (result == null) {
            throw new WebApplicationException("セッションが切断されました。", Response.Status.BAD_REQUEST);
        }
        log.info("予約結果{}", result);

        ReserveCompletedView reserveCompletedView = new ReserveCompletedView();
        reserveCompletedView.setViewHeaderData(new ViewHeaderData());
        reserveCompletedView.getViewHeaderData().setTitle("予約完了画面");
        reserveCompletedView.setReserveId(result.getReserveId());
        reserveCompletedView.setTotalCharge(result.getTotalCharge());
        return reserveCompletedView;
    }

    @GET
    @Path("calendar/{targetMonth}/")
    public ReserveCalenderView getServiceCalendar(@PathParam("targetMonth") String targetMonth) {

        ReserveCalenderView reserveCalenderView = new ReserveCalenderView();
        // TODO initDummy
        reserveCalenderView.initDummyData();
        return reserveCalenderView;
    }

}
