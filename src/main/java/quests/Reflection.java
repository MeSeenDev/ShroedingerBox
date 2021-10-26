package quests;

import java.lang.reflect.Method;

public class Reflection {

    static void callMethodByName(Object object, String methodName, Object... params) throws Exception {
        Class<?> tClass = object.getClass();
        Method method;
        System.out.println(params.length);
        try {
            method = tClass.getDeclaredMethod(methodName);
            System.out.println(method.getName());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
