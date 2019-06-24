package jp.com.sskprj.dw.three.view;

import io.dropwizard.views.View;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class PersonView extends View {

    @Getter
    private final Person person;

    @Setter
    @Getter
    private CalendarDto calendar;

    public PersonView(Person person) {
        super("person.ftl", StandardCharsets.UTF_8);
        this.person = person;
    }


}
