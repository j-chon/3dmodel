package jp.com.sskprj.dw.three.setup.config;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import jp.com.sskprj.dw.three.entity.db.ReserveSchedule;

public class HibernateConfiguration {

    private HibernateConfiguration() {
    }

    /**
     * Entity追加時にここに追記する。
     * @return
     */
    public static HibernateBundle<ApplicationConfiguration> createHibernateBundle() {
        return new HibernateBundle<>(ReserveSchedule.class
        ) {
            @Override
            public DataSourceFactory getDataSourceFactory(ApplicationConfiguration configuration) {
                return configuration.getDatasource();
            }
        };
    }

}
