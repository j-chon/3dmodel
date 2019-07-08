package jp.com.sskprj.dw.three.view;

import jp.com.sskprj.dw.common.DummyUtils;
import jp.com.sskprj.dw.three.view.parts.ReserveForm;
import lombok.Getter;
import lombok.Setter;

public class ReserveInputView extends AbstractOriginalView{

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
        super("reserveInput.ftl");
    }

    @Override
    protected void initDummyData() {
        this.reserveForm = new ReserveForm();
        this.reserveForm.setName("");
        this.reserveForm.setName01("");
        this.reserveForm.setPhoneNumber("");

    }
}
