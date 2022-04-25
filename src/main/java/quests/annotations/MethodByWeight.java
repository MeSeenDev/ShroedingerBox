package quests.annotations;

import java.util.Arrays;
import java.util.Comparator;

public class MethodByWeight {

    public static void callMethodByWeight(Object object) throws Exception {
        Arrays.stream(object.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Weight.class))
                .sorted(Comparator.comparingInt(o -> o.getAnnotation(Weight.class).value()))
                .forEach(method -> {
                    try {
                        method.setAccessible(true);
                        method.invoke(object, (Object[]) null);
                    } catch (Throwable throwable) {
                        /*noop*/
                    }
                });

    }

}
