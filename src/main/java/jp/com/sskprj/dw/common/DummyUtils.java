package jp.com.sskprj.dw.common;

import jp.com.sskprj.dw.three.view.DummyViewInterface;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

public class DummyUtils {

    public static DummyViewInterface getDummyView(DummyViewInterface dummyView) {
        dummyView.initDummyData();
        return dummyView;
    }

    /**
     * 表示テスト用のデフォルトデータを設定する。
     * TODO 表示データ確認用に開発する。
     *
     * @param data
     */
    public static void setDefaultData(Object data) {

        final Method[] allMethodList = data.getClass().getMethods();

        List<String> setterMethodList = Lists.newArrayList();

        for (Method method : allMethodList) {
            Class<?> type = method.getReturnType();
            String methodName = method.getName();
            if (type == String.class || type == BigDecimal.class) {
                if (StringUtils.indexOf(methodName, "get") == 0) {
                    String methodNameTypeSet = methodName.replaceAll("get", "set");
                    setterMethodList.add(methodNameTypeSet);
                }
            }
        }
        for (Method method : allMethodList) {
            String methodName = method.getName();

            for (String setter : setterMethodList) {
                if (StringUtils.equals(methodName, setter)) {
                    String methodNameParts = methodName.replaceAll("set", "");
                    try {
                        if (method.getParameterTypes()[0] == String.class) {
                            method.invoke(data, methodNameParts);
                        }
                        if (method.getParameterTypes()[0] == BigDecimal.class) {
                            method.invoke(data, new BigDecimal("99999999"));
                        }

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

