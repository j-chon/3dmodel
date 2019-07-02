package jp.com.sskprj.dw.three.dao;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWork;
import jp.com.sskprj.dw.three.config.ThreeConfiguration;
import jp.com.sskprj.dw.three.entity.db.ReserveSchedule;
import org.assertj.core.util.Lists;

import java.util.List;

public class ReserveCalendarDAO extends AbstractDAO<ReserveSchedule> {

    /**
     * Creates a new DAO with a given session provider.
     *
     * @param hibernate a session provider
     */
    public ReserveCalendarDAO(HibernateBundle<ThreeConfiguration> hibernate) {
        super(hibernate.getSessionFactory());
    }

    public ReserveSchedule find(long id) {
        ReserveSchedule reserveSchedule = new ReserveSchedule();
        reserveSchedule.setId(id);
        return reserveSchedule;
    }
    @Timed
    @UnitOfWork
    public List<ReserveSchedule> findList(){
        List<ReserveSchedule> result = Lists.newArrayList(new ReserveSchedule(),new ReserveSchedule());
        return result;
    }
}
