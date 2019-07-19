package jp.com.sskprj.dw.three.view;

import jp.com.sskprj.dw.common.security.CsrfFilter;
import jp.com.sskprj.dw.three.view.parts.ReserveForm;
import jp.com.sskprj.dw.three.view.parts.ViewHeaderData;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;

public class ReserveInputView extends AbstractOriginalView implements DummyViewInterface {

    static final String TEMPLATE_NAME = "reserveInput.ftl";

    @Getter
    @Setter
    protected String csrfToken;


    @Getter
    @Setter
    private ViewHeaderData viewHeaderData;

    @Getter
    @Setter
    private String storeName="";

    @Getter
    @Setter
    private String storeId="";


    @Getter
    @Setter
    private ReserveForm reserveForm;

    public ReserveInputView() {
        super(TEMPLATE_NAME);
    }

    public ReserveInputView(HttpServletRequest request) {
        super(TEMPLATE_NAME);
        this.csrfToken = (String) request.getSession().getAttribute(CsrfFilter.CSRF_TOKEN_KEY);
    }

    @Override
    public void initDummyData() {
        this.reserveForm = new ReserveForm();
        this.reserveForm.setCustomerName("");
        this.reserveForm.setCustomerPhoneNumber("");

    }
}
