package jp.com.sskprj.dw.three.view;

import jp.com.sskprj.dw.three.view.parts.ReserveForm;
import jp.com.sskprj.dw.three.view.parts.ViewHeaderData;
import lombok.Getter;
import lombok.Setter;

/**
 * 確認画面用view
 */
public class ReserveConfirmView extends AbstractOriginalView {

    private static final String TEMPLATE_NAME = "reserveConfirm.ftl";

    @Getter
    @Setter
    private String storeName = "";

    @Getter
    @Setter
    private String storeId = "";

    @Getter
    @Setter
    private ViewHeaderData viewHeaderData;

    @Getter
    @Setter
    private ReserveForm reserveForm;

    public ReserveConfirmView() {
        super(TEMPLATE_NAME);
    }

    @Override
    protected void initDummyData() {
        this.reserveForm = new ReserveForm();
        this.viewHeaderData = new ViewHeaderData();
        this.reserveForm.setCustomerName("a");
        this.reserveForm.setCustomerPhoneNumber("b");

    }
}
