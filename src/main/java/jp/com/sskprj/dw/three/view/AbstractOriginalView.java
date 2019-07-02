package jp.com.sskprj.dw.three.view;

import io.dropwizard.views.View;

import java.nio.charset.StandardCharsets;

public abstract class AbstractOriginalView extends View {

    public AbstractOriginalView(String templateName){
        super(templateName, StandardCharsets.UTF_8);

    }

}
