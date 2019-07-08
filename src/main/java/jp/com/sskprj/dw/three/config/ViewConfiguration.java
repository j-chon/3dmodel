package jp.com.sskprj.dw.three.config;

import io.dropwizard.views.ViewRenderException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.glassfish.jersey.spi.ExtendedExceptionMapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ViewConfiguration {


    public static ExtendedExceptionMapper<WebApplicationException> createViewExceptionComponent() {
        return new ExtendedExceptionMapper<>() {
            @Override
            public Response toResponse(WebApplicationException exception) {
                exception.printStackTrace();
                return exception.getResponse();
            }

            @Override
            public boolean isMappable(WebApplicationException exception) {
                return ExceptionUtils.indexOfThrowable(exception, ViewRenderException.class) != -1;
            }
        };
    }
}
