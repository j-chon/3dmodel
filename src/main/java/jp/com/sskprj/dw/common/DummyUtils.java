package jp.com.sskprj.dw.common;

import jp.com.sskprj.dw.three.view.parts.ReserveForm;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.assertj.core.util.Lists;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class DummyUtils<T extends Object> {

    public void setDefaultData(T data) {

        final Method[] allMethodList = data.getClass().getMethods();

        List<String> setterMethodList = Lists.newArrayList();
        for (Method method : allMethodList) {
            Class<?> type = method.getReturnType();
            String methodName = method.getName();
            if (type == String.class) {
                if (StringUtils.equals(methodName, "getName01")) {
                    setterMethodList.add("setName01");
                }
                if (StringUtils.equals(methodName, "getName")) {
                    setterMethodList.add("setName");
                }
            }

        }
        for (Method method : allMethodList) {
            String methodName = method.getName();

            for (String setter : setterMethodList) {
                if (StringUtils.equals(methodName, setter)) {
                    try {
                        method.invoke(data, "Name");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }
}

