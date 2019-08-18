package jp.com.sskprj.dw.three.api;

import com.google.common.collect.Maps;
import com.google.firebase.auth.UserRecord;
import jp.com.sskprj.dw.common.enums.OAuthServiceType;
import jp.com.sskprj.dw.common.service.OAuthStatusService;
import jp.com.sskprj.dw.three.entity.db.OAuthStatus;
import jp.com.sskprj.dw.three.entity.db.RequestLoginInfo;
import jp.com.sskprj.dw.three.service.InterfaceFirebaseAuthService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/loginApi/")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class LoginApiResource {

    @Inject
    OAuthStatusService oAuthStatusService;

    @Inject
    InterfaceFirebaseAuthService firebaseAuthService;

    @POST
    @Path("register/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postRegisterToken(RequestLoginInfo requestLoginInfo, @Context HttpServletRequest request) {
        log.info("***********リクエストです。{}", requestLoginInfo.toString());
        oAuthStatusService.registerToken(request.getSession().getId(), OAuthServiceType.GOOGLE,
                requestLoginInfo.getToken());
        log.info("登録しました");

        UserRecord userRecord = firebaseAuthService.selectUserRecord(requestLoginInfo.getUserId());

        Map<String, String> body = Maps.newConcurrentMap();
        body.put("result", userRecord.getDisplayName() + "です。");

        return Response.status(Response.Status.OK).entity(body).build();
    }

}
