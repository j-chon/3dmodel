package jp.com.sskprj.dw.common.provider;

import jp.com.sskprj.dw.common.filter.OriginalContainerRequestFilter;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

@Provider
@Slf4j
public class OriginalProvider implements DynamicFeature {

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        OriginalAnnotation annotation = resourceInfo.getResourceMethod().getAnnotation(OriginalAnnotation.class);
        if (annotation == null) {
            return;
        }
        log.info("OriginalProvider called - key = {}", annotation.key());
        context.register(OriginalContainerRequestFilter.class);
    }
}
