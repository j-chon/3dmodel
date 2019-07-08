package jp.com.sskprj.dw.common;

import jp.com.sskprj.dw.three.view.parts.ReserveForm;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DummyUtilsTest {


    @Test
    public void setDefaultData_string埋める() {

        ReserveForm inputForm = new ReserveForm();

        new DummyUtils<ReserveForm>().setDefaultData(inputForm);

        assertEquals("",inputForm.getName(),"Name");
        assertEquals("",inputForm.getName01(),"Name");

    }
}
