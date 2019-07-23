package jp.com.sskprj.dw.three.view;

import jp.com.sskprj.dw.three.view.parts.ViewHeaderData;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class ReserveCompletedView extends AbstractOriginalView implements DummyViewInterface {

    private static final String TEMPLATE_NAME = "reserveCompleted.ftl";

    @Getter
    @Setter
    private String reserveId = "";

    @Getter
    @Setter
    private BigDecimal totalCharge = BigDecimal.ZERO;

    @Getter
    @Setter
    private ViewHeaderData viewHeaderData = new ViewHeaderData();

    public ReserveCompletedView() {
        super(TEMPLATE_NAME);
    }

    @Override
    public void initDummyData() {
        this.viewHeaderData.setTitle("ダミーデータ");
    }
}
