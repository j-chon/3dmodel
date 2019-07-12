package jp.com.sskprj.dw.common;

import jp.com.sskprj.dw.three.view.parts.ReserveForm;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class DummyUtilsTest {


    @Test
    public void setDefaultData_string埋める() {

        ReserveForm inputForm = new ReserveForm();

        DummyUtils.setDefaultData(inputForm);

        assertEquals("", "CustomerName", inputForm.getCustomerName());
        assertEquals("", "CustomerAddress", inputForm.getCustomerAddress());
        assertEquals("", new BigDecimal("99999999"), inputForm.getTotalCharge());

    }
}
