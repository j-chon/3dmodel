package jp.com.sskprj.dw.three.config;

import com.google.inject.Singleton;
import io.dropwizard.lifecycle.Managed;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class GuiceyManager implements Managed {

    @Override
    public void start() throws Exception {
        log.info("==================Starting some resource");
    }

    @Override
    public void stop() throws Exception {
        log.info("==================Shutting down some resource");

    }
}
