package jp.com.sskprj.dw.three.view;

import io.dropwizard.views.View;

import java.nio.charset.StandardCharsets;

public abstract class AbstractOriginalView extends View {

    public AbstractOriginalView(String templateName){
        super(templateName, StandardCharsets.UTF_8);
    }

    protected abstract void initDummyData();

    /**
     * 自身のダミーデータ入りViewを返す。
     * @return
     */
    public AbstractOriginalView getDummyView(){
        this.initDummyData();
        return this;
    }

}
