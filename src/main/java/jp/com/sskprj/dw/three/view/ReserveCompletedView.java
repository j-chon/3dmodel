package jp.com.sskprj.dw.three.view;

import jp.com.sskprj.dw.three.view.parts.ViewHeaderData;
import lombok.Getter;
import lombok.Setter;

public class ReserveCompletedView extends AbstractOriginalView implements DummyViewInterface {

    private static final String TEMPLATE_NAME = "reserveCompleted.ftl";

    @Getter
    @Setter
    private String storeName = "";

    @Getter
    @Setter
    private String storeId = "";

    @Getter
    @Setter
    private String reserveId = "";

    @Getter
    @Setter
    private String totalCharge = "";

    @Getter
    @Setter
    private ViewHeaderData viewHeaderData;

    public ReserveCompletedView() {
        super(TEMPLATE_NAME);
    }

    @Override
    public void initDummyData() {
        this.viewHeaderData = new ViewHeaderData();

    }
}
