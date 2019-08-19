package jp.com.sskprj.dw.three.setup.config;

import io.dropwizard.views.ViewRenderException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.glassfish.jersey.spi.ExtendedExceptionMapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Slf4j
public class ViewConfiguration {

    private ViewConfiguration() {
    }

    public static ExtendedExceptionMapper<WebApplicationException> createViewExceptionComponent() {
        return new ExtendedExceptionMapper<>() {
            @Override
            public Response toResponse(WebApplicationException exception) {
                log.info("toResponse - {}");
                return exception.getResponse();
            }

            @Override
            public boolean isMappable(WebApplicationException exception) {
                boolean isMappable = ExceptionUtils.indexOfThrowable(exception, ViewRenderException.class) != -1;
                log.info("isMappable - {}", isMappable);
                return isMappable;
            }
        };
    }
}
