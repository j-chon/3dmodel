package jp.com.sskprj.dw.three.api;

import com.google.common.collect.Maps;
import jp.com.sskprj.dw.three.entity.db.RequestLoginInfo;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/loginApi/")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class LoginApiResource {

    @POST
    @Path("register/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postToken(RequestLoginInfo requestLoginInfo) {
        log.info("***********リクエストです。{}",requestLoginInfo.toString());
        Map<String,String> body = Maps.newConcurrentMap();
        body.put("result","OKです。");
        Response response = Response.status(Response.Status.OK).entity(body).build();
        return response;
    }

}
