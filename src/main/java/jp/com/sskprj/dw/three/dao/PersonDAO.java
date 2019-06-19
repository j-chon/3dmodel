package jp.com.sskprj.dw.three.dao;

import io.dropwizard.hibernate.AbstractDAO;
import jp.com.sskprj.dw.three.view.Person;
import org.hibernate.SessionFactory;

public class PersonDAO extends AbstractDAO<Person> {

    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public PersonDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Person find(String id) {
        return new Person(id);
    }
}
