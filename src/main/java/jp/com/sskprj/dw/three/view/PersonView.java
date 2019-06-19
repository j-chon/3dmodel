package jp.com.sskprj.dw.three.view;

import io.dropwizard.views.View;
import lombok.Getter;

public class PersonView extends View {

    @Getter
    private final Person person;

    public PersonView(Person person) {
        super("person.ftl");
        this.person = person;
    }

}
