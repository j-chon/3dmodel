package jp.com.sskprj.dw.common;

import jp.com.sskprj.dw.three.view.DummyViewInterface;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class DummyUtils {

    private DummyUtils() {
    }

    public static DummyViewInterface getDummyView(DummyViewInterface dummyView) {
        dummyView.initDummyData();
        return dummyView;
    }

    /**
     * 表示テスト用のデフォルトデータを設定する。
     *
     * @param data
     */
    public static void setDefaultData(Object data) {

        final Method[] allMethodList = data.getClass().getMethods();

        List<Method> stringMethodList = extractTargetTypeMethodList(allMethodList, String.class);

        List<Method> bigDecimalMethodList = extractTargetTypeMethodList(allMethodList, BigDecimal.class);

        try {

            for (Method method : stringMethodList) {
                if (isNotSetter(method)) {
                    continue;
                }
                String methodNameParts = method.getName().replaceAll("set", "");
                method.invoke(data, methodNameParts);
            }
            for (Method method : bigDecimalMethodList) {
                if (isNotSetter(method)) {
                    continue;
                }
                method.invoke(data, new BigDecimal("99999999"));
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("", e);
        }

    }

    private static List<Method> extractTargetTypeMethodList(Method[] allMethodList, Class targetClass) {
        List<Method> stringMethodList = Lists.newArrayList();
        for (Method method : allMethodList) {
            if (method.getParameterTypes().length == 0) {
                continue;
            }
            if (method.getParameterTypes()[0] == targetClass) {
                stringMethodList.add(method);
            }
        }
        return stringMethodList;
    }

    private static boolean isNotSetter(Method method) {
        return StringUtils.indexOf(method.getName(), "set") != 0;
    }
}

