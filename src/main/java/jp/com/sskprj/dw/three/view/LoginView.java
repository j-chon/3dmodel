package jp.com.sskprj.dw.three.view;

import io.dropwizard.views.View;
import jp.com.sskprj.dw.three.view.parts.ViewHeaderData;
import lombok.Getter;
import lombok.Setter;

public class LoginView extends AbstractOriginalView implements DummyViewInterface {

    private static final String templateName = "login.ftl";

    @Getter
    @Setter
    private ViewHeaderData viewHeaderData = new ViewHeaderData();

    public LoginView() {
        super(templateName);
    }

    @Override
    public void initDummyData() {

    }
}
